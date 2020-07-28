package edu.cnm.deepdive.freestylerhyme.controller;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import edu.cnm.deepdive.freestylerhyme.R;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.service.GoogleSignInService;
import viewmodel.MainViewModel;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {

  private GoogleSignInService signInService;
  private AutoCompleteTextView word;
  private ListView rhymesList; //TODO use recycler view instead.
  private FloatingActionButton randomWord;
  private ImageView search;
  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    word = findViewById(R.id.word);
    rhymesList = findViewById(R.id.rhymes_list);
    randomWord = findViewById(R.id.random_word);
    randomWord.setOnClickListener(v -> viewModel.fetchRandomWord());
    search = findViewById(R.id.search);
    search.setOnClickListener((v) -> viewModel.search(word.getText().toString().trim()));
    setupObservers();
  }

  private void setupObservers() {
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    getLifecycle().addObserver(viewModel);
    viewModel.getThrowable().observe(this, (throwable) -> {
      if (throwable != null) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    });
    viewModel.getWords().observe(this, (words) -> {
      ArrayAdapter<Word> adapter = new ArrayAdapter<>(this,
          android.R.layout.simple_dropdown_item_1line, words);
      word.setAdapter(adapter);
    });
    viewModel.getResults().observe(this, (results) -> {
      ArrayAdapter<Result> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
          results);
      rhymesList.setAdapter(adapter);
    });
    viewModel.getRandomWord().observe(this, (word) -> {
      if (word != null) {
        this.word.setText(word);
      }
    });
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
