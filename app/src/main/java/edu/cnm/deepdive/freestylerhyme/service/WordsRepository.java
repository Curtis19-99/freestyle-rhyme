package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.freestylerhyme.model.Dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import java.util.List;

public class WordsRepository {

  private final Context context;
  private final WordDatabase database;
  private final WordDao wordDao;

  public WordsRepository(Context context) {
    this.context = context;
    database = ResultDatabase.getInstance();
    wordDao = database.getWordDao();
  }

  public LiveData<List<Word>> getAll() {
    return wordDao.selectAll();
  }

}
