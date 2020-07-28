package viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
import edu.cnm.deepdive.freestylerhyme.service.ResultRepository;
import edu.cnm.deepdive.freestylerhyme.service.WordRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * The type Main view model.
 */
public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final ResultRepository resultRepository;
  private final WordRepository wordRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<ResultWithWord> result;
  private final MutableLiveData<List<Result>> results;
  private final MutableLiveData<String> randomWord;

  /**
   * Instantiates a new Main view model.
   *
   * @param application the application
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    resultRepository = new ResultRepository(application);
    wordRepository = new WordRepository(application);
    result = new MutableLiveData<>();
    results = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
    randomWord = new MutableLiveData<>();
  }

  /**
   * Gets results.
   *
   * @return the results
   */
  public LiveData<List<Result>> getResults() {
    return results;
  }

  public void search(String word) {
    throwable.setValue(null);
    randomWord.setValue(null);
    pending.add(
        wordRepository.search(word.toLowerCase())
            .subscribe(
                (results) -> this.results.postValue(results),
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  public void fetchRandomWord() {
    throwable.setValue(null);
    pending.add(
        wordRepository.random()
            .subscribe(
                (word) -> randomWord.postValue(word),
                (throwable) -> this.throwable.postValue(throwable)
            )
    );
  }

  public LiveData<String> getRandomWord() {
    return randomWord;
  }

  /**
   * Gets words.
   *
   * @return the words
   */
  public LiveData<List<Word>> getWords() {
    return wordRepository.getAll();
  }

  /**
   * Gets result.
   *
   * @return the result
   */
  public MutableLiveData<ResultWithWord> getResult() {
    return result;
  }

  /**
   * Gets throwable.
   *
   * @return the throwable
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }

}
