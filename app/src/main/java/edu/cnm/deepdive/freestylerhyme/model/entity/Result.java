package edu.cnm.deepdive.freestylerhyme.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * The type Result.
 */
@Entity(
    foreignKeys = @ForeignKey(
        entity = Word.class,
        parentColumns = "word_id",
        childColumns = "word_id",
        onDelete = ForeignKey.SET_NULL
    )
)
public class Result {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "result_id")
  private long id;

  private Date created = new Date();

  @ColumnInfo(name = "word_id", index = true)
  private Long wordId;

  @NonNull
  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String text = "";

  /**
   * Instantiates a new Result.
   */
  public Result() {

  }

  /**
   * Instantiates a new Result.
   *
   * @param text the text
   */
  public Result(String text) {
   this.text = text;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets word id.
   *
   * @return the word id
   */
  public Long getWordId() {
    return wordId;
  }

  /**
   * Sets word id.
   *
   * @param wordId the word id
   */
  public void setWordId(Long wordId) {
    this.wordId = wordId;
  }

  /**
   * Gets created.
   *
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  /**
   * Sets created.
   *
   * @param created the created
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * Gets text.
   *
   * @return the text
   */
  @NonNull
  public String getText() {
    return text;
  }

  /**
   * Sets text.
   *
   * @param text the text
   */
  public void setText(@NonNull String text) {
    this.text = text;
  }

  @NonNull
  @Override
  public String toString() {
    return text;
  }

}
