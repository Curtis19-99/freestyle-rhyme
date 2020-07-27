package edu.cnm.deepdive.freestylerhyme;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.freestylerhyme.service.FreestyleDatabase;
import edu.cnm.deepdive.freestylerhyme.service.GoogleSignInService;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Freestyle rhyme application.
 */
public class FreestyleRhymeApplication extends Application {


  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    FreestyleDatabase.setContext(this);
    FreestyleDatabase database = FreestyleDatabase.getInstance();
    database.getWordDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    Stetho.initializeWithDefaults(this);
  }

}
