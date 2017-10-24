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
  private String[][] answerMatrix;
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
    this.answerMatrix = new String[size][size];
    fillAnswerMatrix();
  }

  public String play() {
    // We can use "comb" because after the constructor, it's not needed.
    comb = "";
    // check board state (last play answer) calling class board (?)
    // update discard options
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
        answerMatrix[i][j] = answer;
      }
    }
  }

  public void printAnswerMatrix() {
    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        System.out.print(answerMatrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  private String calculateAnswer(String g, String c) {
    char[] guess = g.toCharArray();
    char[] code = c.toCharArray();
    String answer = "";
    String impossible_answer = "";
    for (int i = 0; i < width - 1; ++i) { impossible_answer += 'B'; }
    boolean[] guessChecked = new boolean[width];
    boolean[] codeChecked = new boolean[width];
    for (int i = 0; i < width; ++i) {
      guessChecked[i] = false;
      codeChecked[i] = false;
    }
    // first pass: Black pins -> color & position correct
    for (int i = 0; i < width; ++i) {
      if (guess[i] == code[i]) {
        answer += 'B'; // B for Black pin
        guessChecked[i] = true;
        codeChecked[i] = true;
      }
    }
    // second pass: Red pins: color correct & position incorrect
    for (int i = 0; i < width && !answer.equals(impossible_answer); ++i) {
      for (int j = 0; j < width && !guessChecked[i]; ++j) {
        if (!codeChecked[j] && guess[i] == code[j]) {
          answer += 'R'; // R for Red pin
          guessChecked[i] = true;
          codeChecked[j] = true;
        }
      }
    }
    // third pass: X (no pin): color incorrect & position incorrect
    // fill remaining slots with X
    for (int i = 0; i < width; ++i) {
      if (!guessChecked[i]) {
        answer += "X"; // X for no pin
      }
    }
    System.out.println(answer);
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
