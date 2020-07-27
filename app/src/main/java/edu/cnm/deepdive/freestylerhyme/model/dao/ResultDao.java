package edu.cnm.deepdive.freestylerhyme.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.pojo.ResultWithWord;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * The interface Result dao.
 */
@Dao
public interface ResultDao {

  /**
   * Insert single.
   *
   * @param result the result
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Result result);

  /**
   * Insert single.
   *
   * @param result the result
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Result> result);

  /**
   * Update single.
   *
   * @param result the result
   * @return the single
   */
  @Update
  Single<Integer> update(Result... result);

  /**
   * Delete single.
   *
   * @param result the result
   * @return the single
   */
  @Delete
  Single<Integer> delete(Result... result);

  /**
   * Select all live data.
   *
   * @return the live data
   */
  @Transaction
  @Query("SELECT * FROM Result ORDER BY text")
  LiveData<List<ResultWithWord>> selectAll();

  /**
   * Select by word id single.
   *
   * @param wordId the word id
   * @return the single
   */
  @Query("SELECT * FROM Result WHERE word_id = :wordId")
  Single<List<Result>> selectByWordId(long wordId);


  /**
   * Select by id single.
   *
   * @param resultId the result id
   * @return the single
   */
  @Transaction
  @Query("SELECT * FROM Result WHERE result_id = :resultId")
  Single<ResultWithWord> selectById(long resultId);


}
