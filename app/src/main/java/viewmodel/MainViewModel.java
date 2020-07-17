package viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
import edu.cnm.deepdive.freestylerhyme.service.ResultRepository;
import edu.cnm.deepdive.freestylerhyme.service.WordRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

  private final ResultRepository resultRepository;
  private final WordRepository wordRepository;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;
  private final MutableLiveData<ResultWithWord> result;

  public MainViewModel(@NonNull Application application) {
    super(application);
    resultRepository = new ResultRepository(application);
    wordRepository = new WordRepository(application);
    result = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  public LiveData<List<ResultWithWord>> getResults() {
    return resultRepository.getAll();
  }

  public LiveData<List<Word>> getWords() {
    return wordRepository.getAll();
  }

  public MutableLiveData<ResultWithWord> getResult() {
    return result;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }

}
