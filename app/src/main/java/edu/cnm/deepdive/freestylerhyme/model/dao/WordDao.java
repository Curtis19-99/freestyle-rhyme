package edu.cnm.deepdive.freestylerhyme.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordWithResult;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface WordDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Word words);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Word... words);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Word> words);

  @Update
  Single<Integer> update(Word... words);

  @Delete
  Single<Integer> delete(Word... words);

  @Query("SELECT * FROM Word ORDER BY word")
  LiveData<List<Word>> selectAll();

  @Transaction
  @Query("SELECT * FROM Word ORDER BY word")
  LiveData<List<WordWithResult>> selectAllWithQuotes();

  @Transaction
  @Query("SELECT * FROM Word WHERE word_id = :sourceId")
  Single<WordWithResult> selectById(long sourceId);

}

