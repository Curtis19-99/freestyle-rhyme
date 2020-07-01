package edu.cnm.deepdive.freestylerhyme.model.Dao;

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

@Dao
public interface ResultDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Result result);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Result> result);

  @Update
  Single<Integer> update(Result... result);

  @Delete
  Single<Integer> delete(Result... result);

  @Transaction
  @Query("SELECT * FROM Result ORDER BY text")
  LiveData<List<ResultWithWord>> selectAll();

  @Query("SELECT * FROM Result WHERE word_id = :wordId")
  Single<List<Result>> selectBySourceId(Long wordId);

/*
  @Transaction
  @Query("SELECT * FROM Result WHERE result_id = :resultId")
  Single<ResultWithWord> selectById(long resultId);
*/

}
