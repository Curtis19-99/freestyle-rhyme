package edu.cnm.deepdive.freestylerhyme.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.freestylerhyme.R;
import edu.cnm.deepdive.freestylerhyme.service.GoogleSignInService;
import viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {


  private GoogleSignInService signInService;
  private AutoCompleteTextView word;
  private RecyclerView rhymesList;
  private FloatingActionButton randomWord;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    word = findViewById(R.id.word);
    rhymesList = findViewById(R.id.rhymes_list);
    randomWord = findViewById(R.id.random_word);
    randomWord.setOnClickListener((v) -> { /* TODO select a random word */ });
    setupObservers();
  }

  private void setupObservers() {
    MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
    //TODO Observe the selected list of rhymes.
    // TODO Observe list of words already in database. To populate auto complete textView. work in view model.
    signInService = GoogleSignInService.getInstance();
  }

  private void switchToLogin() {
    Intent intent = new Intent(this, LoginActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    getMenuInflater().inflate(R.menu.options, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    boolean handled = true;
    //noinspection SwitchStatementWithTooFewBranches
    switch (item.getItemId()) {
      case R.id.sign_out:
        signInService.signOut().addOnCompleteListener((ignored) -> switchToLogin());
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }
    return handled;
  }
}
