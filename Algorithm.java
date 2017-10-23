// Algorithm.java

// package mastermind;

import java.util.ArrayList;

public class Algorithm {

  private int width;
  private int nColors;
  private int size;
  private boolean repetition;
  private ArrayList<String> allColors;
  private ArrayList<String> allCombs;
  private ArrayList<Boolean> discarded;
  private ArrayList< ArrayList<String> > answerMatrix;
  private String comb;

  public Algorithm(int width, int nColors, boolean repetition) {
    this.comb = "";
    this.width = width;
    this.nColors = nColors;
    this.repetition = repetition;
    this.allColors = new ArrayList<String>(nColors);
    generateColors();
    this.size = 0; // Initialization just in case
    if (repetition) {
      size = (int)(Math.pow((double)(nColors), (double)(width)));
      this.allCombs = new ArrayList<String>(size);
      setAllCombs();
    } else {
      size = binomial(nColors, width);
      this.allCombs = new ArrayList<String>(size);
      setAllCombsNoRep();
    }
    this.discarded = new ArrayList<Boolean>(size);
    for (int i = 0; i < size; ++i) {
      discarded.add(false);
    }
    this.answerMatrix = new ArrayList< ArrayList<String> >(size);
    fillAnswerMatrix();
  }

  public String play() {
    // We can use 'comb' because after the constructor, it's not needed.
    comb = "";
    // check board state (last play answer)
    // discard options
    // get minmax
    return comb;
  }

  private void fillAnswerMatrix() {
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        // answerMatrix[i][j] = "la resposta que donaria si haguessim " +
        // + "proposat la combinacio [i] i el code fos la combinacio [j] ?"
        String guess = allCombs.get(i);
        String code = allCombs.get(j);
        String answer = calculateAnswer(guess, code);
        // answerMatrix[i][j] = answer;
      }
    }
  }

  private String calculateAnswer(String g, String c) {
    char[] guess = g.toCharArray();
    char[] code = c.toCharArray();
    String answer = "";
    ArrayList<Boolean> checked = new ArrayList<Boolean>(width);
    // first pass: Black pins -> color & position correct
    for (int i = 0; i < width; ++i) {
      if (guess[i] == code[i]) {
        answer += 'B';
        // checked[i] = true;
        // checked.set(i, true);
      }
    }
    // second pass: Red pins: color correct & position incorrect
    // <code here>
    // third pass: X (n pin): color incorrect & position incorrect
    // fill remaining slots with X
    // <code here>
    return answer;
  }

  private void generateColors() {
    for (char c = 'A'; c < (char)('A' + nColors); ++c) {
      String color = new String();
      color += c;
      allColors.add(color);
    }
  }

  private void setAllCombs() {
    if (comb.length() < width) {
      for (int i = 0; i < allColors.size(); ++i) {
        String oldComb = comb;
        comb += allColors.get(i);
        setAllCombs();
        comb = oldComb;
      }
    } else {
      allCombs.add(comb);
    }
  }

  private void setAllCombsNoRep() {
    if (comb.length() < width) {
      for (int i = 0; i < allColors.size(); ++i) {
        String oldComb = comb;
        if (!comb.contains(allColors.get(i))) {
          comb += allColors.get(i);
          setAllCombsNoRep();
        }
        comb = oldComb;
      }
    } else {
      allCombs.add(comb);
    }
  }

  private int binomial(int n, int k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }

  private int factorial(int f) {
    if (f < 2) { return f; }
    return f * factorial(f - 1);
  }

  public void printAllCombs() {
    System.out.println("size = " + size + ", allCombs size = " + allCombs.size());
    for (int i = 0; i < allCombs.size(); ++i) {
      System.out.println(allCombs.get(i));
    }
  }

  public void printSingleComb(String combination) {
    System.out.println(combination);
  }

  public void printAllColors() {
    for (int i = 0; i < allColors.size(); ++i) {
      System.out.println(allColors.get(i));
    }
  }

  public void printSingleColor(String color) {
    System.out.println(color);
  }

}
