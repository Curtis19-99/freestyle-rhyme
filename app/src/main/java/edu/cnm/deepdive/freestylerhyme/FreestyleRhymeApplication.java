package edu.cnm.deepdive.freestylerhyme;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.freestylerhyme.service.FreestyleDatabase;
import io.reactivex.schedulers.Schedulers;

public class FreestyleRhymeApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    FreestyleDatabase.setContext(this);
    FreestyleDatabase database = FreestyleDatabase.getInstance();
    database.getWordDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    Stetho.initializeWithDefaults(this);
  }

}
