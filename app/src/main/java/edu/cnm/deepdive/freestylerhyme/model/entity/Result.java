package edu.cnm.deepdive.freestylerhyme.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.ColumnInfo.Collate;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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

  @ColumnInfo(name = "word_id", index = true)
  private Long wordId;

  @NonNull
  @ColumnInfo(collate = ColumnInfo.NOCASE)
  private String text = "";

  @ColumnInfo(name = "syllable_count")
  private int syllableCount;

  public Result() {

  }
  public Result(String text) {
   this.text = text;
  }

  public int getSyllableCount() {
    return syllableCount;
  }

  public void setSyllableCount(int syllableCount) {
    this.syllableCount = syllableCount;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getWordId() {
    return wordId;
  }

  public void setWordId(Long wordId) {
    this.wordId = wordId;
  }

  @NonNull
  public String getText() {
    return text;
  }

  public void setText(@NonNull String text) {
    this.text = text;
  }

  @NonNull
  @Override
  public String toString() {
    return text;
  }
  //TODO add syllable count
}
