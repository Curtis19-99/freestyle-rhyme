package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.freestylerhyme.model.Dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
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

}
