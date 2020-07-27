package edu.cnm.deepdive.freestylerhyme.service;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.freestylerhyme.R;
import edu.cnm.deepdive.freestylerhyme.model.dao.ResultDao;
import edu.cnm.deepdive.freestylerhyme.model.dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * The type Freestyle database.
 */
@Database(
    entities = {Word.class, Result.class},
    version = 1,
    exportSchema = true
)
@TypeConverters({FreestyleDatabase.Converters.class})
public abstract class FreestyleDatabase extends RoomDatabase {

  private static final String DB_NAME = "freestyle_db";

  private static Application context;

  /**
   * Sets context.
   *
   * @param context the context
   */
  public static void setContext(Application context) {
    FreestyleDatabase.context = context;
  }

  /**
   * Gets word dao.
   *
   * @return the word dao
   */
  public abstract WordDao getWordDao();

  /**
   * Gets result dao.
   *
   * @return the result dao
   */
  public abstract ResultDao getResultDao();

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static FreestyleDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private static final FreestyleDatabase INSTANCE =
        Room.databaseBuilder(context, FreestyleDatabase.class, DB_NAME)
            .addCallback(new FreestyleCallback())
            .build();

  }

  private static class FreestyleCallback extends Callback {

    @Override
    public void onCreate(@NonNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      try {
        Map<Word, List<Result>> map = parseFile(R.raw.review);
        persist(map);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    private Map<Word, List<Result>> parseFile(int resourceId) throws IOException {
      try (
          InputStream input = FreestyleDatabase.context.getResources().openRawResource(resourceId);
          Reader reader = new InputStreamReader(input);
          CSVParser parser = CSVParser.parse(
              reader, CSVFormat.EXCEL.withIgnoreSurroundingSpaces().withIgnoreEmptyLines());
      ) {
        Map<Word, List<Result>> map = new HashMap<>();
        for (CSVRecord record : parser) {
          Word word = null;
          String wordName = record.get(0).trim();
          if (!wordName.isEmpty()) {
            word = new Word();
            word.setWord(wordName);
          }
          List<Result> results = map.computeIfAbsent(word, (s) -> new LinkedList<>());
          Result result = new Result();
          result.setText(record.get(1).trim());
          results.add(result);
        }
        Log.d(getClass().getName(), map.toString());
        return map;
      }
    }

    @SuppressLint("CheckResult")
    private void persist(Map<Word, List<Result>> map) {
      FreestyleDatabase database = FreestyleDatabase.getInstance();
      WordDao wordDao = database.getWordDao();
      ResultDao resultDao = database.getResultDao();
      List<Word> words = new LinkedList<>(map.keySet());
      List<Result> unattributed = map.getOrDefault(null, Collections.emptyList());
      words.remove(null);

      wordDao.insert(words)
          .subscribeOn(Schedulers.io())
          .flatMap((wordIds) -> {
            List<Result> results = new LinkedList<>();
            Iterator<Long> idIterator = wordIds.iterator();
            Iterator<Word> wordIterator = words.iterator();
            while (idIterator.hasNext()) {
              long wordId = idIterator.next();
              for (Result result : map.getOrDefault(wordIterator.next(), Collections.emptyList())) {
                result.setWordId(wordId);
                results.add(result);
              }
            }
            results.addAll(unattributed);
            return resultDao.insert(results);
          })
          .subscribe(
              (resultIds) -> {
              },
              (throwable) -> {
                throw new RuntimeException(throwable);
              }
          );
    }

  }

  /**
   * The type Converters.
   */
  public static class Converters {

    /**
     * Date to long long.
     *
     * @param value the value
     * @return the long
     */
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Long to date date.
     *
     * @param value the value
     * @return the date
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }

  }
}
