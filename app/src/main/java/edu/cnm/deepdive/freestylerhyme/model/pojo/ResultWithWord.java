package edu.cnm.deepdive.freestylerhyme.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;

public class ResultWithWord  extends Result {

  @Relation(entity = Word.class, entityColumn = "word_id", parentColumn = "word_id")
  private Word word;

  public Word getWord() {
    return word;
  }

  public void setWord(Word word) {
    this.word = word;
  }

}
