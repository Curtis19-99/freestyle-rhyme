package edu.cnm.deepdive.freestylerhyme;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.stetho.Stetho;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import edu.cnm.deepdive.freestylerhyme.service.FreestyleDatabase;
import edu.cnm.deepdive.freestylerhyme.service.GoogleSignInService;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;

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
