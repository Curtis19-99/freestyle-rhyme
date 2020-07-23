package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.freestylerhyme.model.dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordWithResult;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class WordRepository {

  private final Context context;
  private final FreestyleDatabase database;
  private final WordDao wordDao;

  public WordRepository(Context context) {
    this.context = context;
    database = FreestyleDatabase.getInstance();
    wordDao = database.getWordDao();
  }

  public LiveData<List<Word>> getAll() {
    return wordDao.selectAll();
  }

  public Single<WordWithResult> get(long id) {
    return wordDao.selectById(id)
        .subscribeOn(Schedulers.io());
  }

  public Completable save(Word word) {
    if (word.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(wordDao.delete(word))
          .subscribeOn(Schedulers.io());
    }
  }

  public Completable delete(Word word) {
    if (word.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(wordDao.delete(word))
          .subscribeOn(Schedulers.io());
    }
  }

}
