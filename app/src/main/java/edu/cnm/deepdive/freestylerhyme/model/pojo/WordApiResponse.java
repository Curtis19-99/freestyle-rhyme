package edu.cnm.deepdive.freestylerhyme.model.pojo;

import com.google.gson.annotations.Expose;
import java.util.List;

/**
 * The type Word api response.
 */
public class WordApiResponse {
  @Expose
  private String word;

  @Expose
  private RhymeResponse rhymes;

  /**
   * Gets word.
   *
   * @return the word
   */
  public String getWord() {
    return word;
  }

  /**
   * Sets word.
   *
   * @param word the word
   */
  public void setWord(String word) {
    this.word = word;
  }

  /**
   * Gets rhymes.
   *
   * @return the rhymes
   */
  public RhymeResponse getRhymes() {
    return rhymes;
  }

  /**
   * Sets rhymes.
   *
   * @param rhymes the rhymes
   */
  public void setRhymes(RhymeResponse rhymes) {
    this.rhymes = rhymes;
  }

  /**
   * The type Rhyme response.
   */
  public static class RhymeResponse{

    @Expose
    private List<String> all;

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<String> getAll() {
      return all;
    }

    /**
     * Sets all.
     *
     * @param all the all
     */
    public void setAll(List<String> all) {
      this.all = all;
    }
  }

}
