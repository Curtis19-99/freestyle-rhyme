package edu.cnm.deepdive.freestylerhyme.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.freestylerhyme.model.entity.Result;
import edu.cnm.deepdive.freestylerhyme.model.entity.Word;
import java.util.List;

public class WordWithResults extends Word {

  @Relation(entity = Result.class, entityColumn = "word_id", parentColumn = "word_id")
  private List<Result> results;

  public List<Result> getResults() {
    return results;
  }

  public void setResults(List<Result> results) {
    this.results = results;
  }

}
