package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.freestylerhyme.model.dao.ResultDao;
import edu.cnm.deepdive.freestylerhyme.model.dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class ResultRepository {

  private final Context context;
  private final FreestyleDatabase database;
  private final WordDao wordDao;
  private final ResultDao resultDao;

  public ResultRepository(Context context) {
    this.context = context;
    database = FreestyleDatabase.getInstance();
    wordDao = database.getWordDao();
    resultDao = database.getResultDao();
  }

  public LiveData<List<ResultWithWord>> getAll() {
    return resultDao.selectAll();
  }

  public Single<ResultWithWord> get(long id) {
    return resultDao.selectById(id)
        .subscribeOn(Schedulers.io());
  }

  public Completable save(Result result) {
    if (result.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(resultDao.delete(result))
          .subscribeOn(Schedulers.io());
    }
  }

  public Completable delete(Result result) {
    if (result.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(resultDao.delete(result))
          .subscribeOn(Schedulers.io());
    }
  }
}
