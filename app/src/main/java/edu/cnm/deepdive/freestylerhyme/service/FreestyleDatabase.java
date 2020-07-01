package edu.cnm.deepdive.freestylerhyme.service;

import android.app.Application;
import android.telecom.Call;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.freestylerhyme.model.Dao.ResultDao;
import edu.cnm.deepdive.freestylerhyme.model.Dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

@Database(
    entities = {Word.class, Result.class},
    version = 1,
    exportSchema = true
)
public class FreestyleDatabase extends RoomDatabase {

  private static final String DB_NAME = "freestyle_db";

  private static Application context;

  public static void setContext(Application context) {
    FreestyleDatabase.context = context;
  }

  public abstract WordDao getWordDao();

  public abstract ResultDao getResultDao();

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
        Map<Word, List<Result>> map = parseFile(R.raw.results);
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
        for (CSVRecord )
      }
    }
  }

}
