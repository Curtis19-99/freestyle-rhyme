package edu.cnm.deepdive.freestylerhyme.controller;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import edu.cnm.deepdive.freestylerhyme.R;
import edu.cnm.deepdive.freestylerhyme.service.GoogleSignInService;
import java.io.IOException;

public class LoginActivity extends AppCompatActivity {


  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    service.refresh()
        .addOnSuccessListener((account) -> switchToMain())
        .addOnFailureListener((throwable) -> {
          setContentView(R.layout.activity_login);
          findViewById(R.id.sign_in).setOnClickListener((V) ->
              service.startSignIn(this, LOGIN_REQUEST_CODE));
        });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener((account) -> switchToMain())
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, R.string.login_failure, Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}
