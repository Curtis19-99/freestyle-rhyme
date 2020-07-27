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
import edu.cnm.deepdive.freestylerhyme.model.pojo.WordWithResults;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * The interface Word dao.
 */
@Dao
public interface WordDao {

  /**
   * Insert single.
   *
   * @param words the words
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(Word words);

  /**
   * Insert single.
   *
   * @param words the words
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Word... words);

  /**
   * Insert single.
   *
   * @param words the words
   * @return the single
   */
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<List<Long>> insert(Collection<Word> words);

  /**
   * Update single.
   *
   * @param words the words
   * @return the single
   */
  @Update
  Single<Integer> update(Word... words);

  /**
   * Delete single.
   *
   * @param words the words
   * @return the single
   */
  @Delete
  Single<Integer> delete(Word... words);

  /**
   * Select all live data.
   *
   * @return the live data
   */
  @Query("SELECT * FROM Word ORDER BY name")
  LiveData<List<Word>> selectAll();

  /**
   * Select all with results live data.
   *
   * @return the live data
   */
  @Transaction
  @Query("SELECT * FROM Word ORDER BY name")
  LiveData<List<WordWithResults>> selectAllWithResults();

  /**
   * Select by id single.
   *
   * @param sourceId the source id
   * @return the single
   */
  @Transaction
  @Query("SELECT * FROM Word WHERE word_id = :sourceId")
  Single<WordWithResults> selectById(long sourceId);

  /**
   * Select by name maybe.
   *
   * @param name the name
   * @return the maybe
   */
  @Transaction
  @Query("SELECT * FROM Word WHERE name = :name")
  Maybe<WordWithResults> selectByName(String name);

}

