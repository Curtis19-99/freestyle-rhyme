package edu.cnm.deepdive.freestylerhyme.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.freestylerhyme.BuildConfig;
import edu.cnm.deepdive.freestylerhyme.model.dao.ResultDao;
import edu.cnm.deepdive.freestylerhyme.model.dao.WordDao;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordWithResults;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.schedulers.Schedulers;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class WordRepository {

  private final Context context;
  private final FreestyleDatabase database;
  private final WordDao wordDao;
  private final ResultDao resultDao;
  private final WordApiService wordApiService;

  public WordRepository(Context context) {
    this.context = context;
    database = FreestyleDatabase.getInstance();
    wordDao = database.getWordDao();
    resultDao = database.getResultDao();
    wordApiService = WordApiService.getInstance();
  }

  public LiveData<List<Word>> getAll() {
    return wordDao.selectAll();
  }

  public Single<WordWithResults> get(long id) {
    return wordDao.selectById(id)
        .subscribeOn(Schedulers.io());
  }

  public Completable save(Word word) {
    if (word.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(wordDao.delete(word))
          .subscribeOn(Schedulers.io());
    }
  }

  public Completable delete(Word word) {
    if (word.getId() == 0) {
      return Completable.fromAction(() -> {})
          .subscribeOn(Schedulers.io());
    } else {
      return Completable.fromSingle(wordDao.delete(word))
          .subscribeOn(Schedulers.io());
    }
  }

  public Single<List<Result>> search(String word) {
    if (!word.isEmpty()) {
      return wordDao.selectByName(word)
          .subscribeOn(Schedulers.io())
          .switchIfEmpty((SingleSource<? extends WordWithResults>) (observer) -> {
            wordApiService.get(word, BuildConfig.HOST, BuildConfig.API_KEY)
                .subscribeOn(Schedulers.io())
                .flatMap((result) ->{
                  WordWithResults wordWithResults = new WordWithResults();
                  wordWithResults.setName(word);
                  wordWithResults.setResults(
                      result.getRhymes().getAll().stream()
                          .map(Result::new)
                          .collect(Collectors.toList())
                  );
                  return wordDao.insert(wordWithResults)
                      .flatMap((id) -> {
                        wordWithResults.getResults().forEach((r) -> r.setWordId(id));
                        return resultDao.insert(wordWithResults.getResults())
                            .map((w) -> wordWithResults);
                      });
                })
                .subscribe(observer);
          })
          .map(WordWithResults::getResults);
    } else {
      throw new IllegalArgumentException("Search term cannot be empty");
    }
  }
}
