package edu.cnm.deepdive.freestylerhyme.model.pojo;

import com.google.gson.annotations.Expose;
import java.util.List;

public class WordApiResponse {
  @Expose
  private String word;

  @Expose
  private RhymeResponse rhymes;

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public RhymeResponse getRhymes() {
    return rhymes;
  }

  public void setRhymes(RhymeResponse rhymes) {
    this.rhymes = rhymes;
  }

  public static class RhymeResponse{

    @Expose
    private List<String> all;

    public List<String> getAll() {
      return all;
    }

    public void setAll(List<String> all) {
      this.all = all;
    }
  }

}
