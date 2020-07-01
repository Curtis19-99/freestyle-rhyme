package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import edu.cnm.deepdive.freestylerhyme.model.Dao.ResultDao;
import edu.cnm.deepdive.freestylerhyme.model.Dao.WordDao;

public class ResultRepository {

  private final Context context;
  private final ResultDatabase database;
  private final WordDao wordDao;
  private final ResultDao resultDao;

  public ResultRepository(Context context) {
    this.context = context;
    database = ResultDatabase.getInstance();
  }

}
