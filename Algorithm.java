// Algorithm.java

// package mastermind;

import java.util.ArrayList;

public class Algorithm {

  private int width;
  private int nColors;
  private boolean repetition;
  private ArrayList<String> allColors;
  private ArrayList<String> allComb;
  private String comb;

  public Algorithm(int width, int nColors, boolean repetition) {
    this.comb = "";
    this.width = width;
    this.nColors = nColors;
    this.repetition = repetition;
    this.allColors = new ArrayList<String>(nColors);
    generateColors();
    if (repetition) {
      int size = (int)(Math.pow((double)(nColors), (double)(width)));
      this.allComb = new ArrayList<String>(size);
      setAllComb();
    } else {
      int size = binomial(nColors, width);
      this.allComb = new ArrayList<String>(size);
      setAllCombNoRep();
    }
  }

  private void generateColors() {
    for (char c = 'A'; c < (char)('A' + nColors); ++c) {
      String color = new String();
      color += c;
      allColors.add(color);
    }
  }

  private void setAllComb() {
    if (comb.length() < nColors) {
      for (int i = 0; i < allColors.size(); ++i) {
        String oldComb = comb;
        comb += allColors.get(i);
        setAllComb();
        comb = oldComb;
      }
    } else {
      allComb.add(comb);
    }
  }

  private void setAllCombNoRep() {
    if (comb.length() < nColors) {
      for (int i = 0; i < allColors.size(); ++i) {
        String oldComb = comb;
        if (!comb.contains(allColors.get(i))) {
          comb += allColors.get(i);
          setAllCombNoRep();
        }
        comb = oldComb;
      }
    } else {
      allComb.add(comb);
    }
  }

  private int binomial(int n, int k) {
    return factorial(n) / (factorial(k) * factorial(n - k));
  }

  private int factorial(int f) {
    if (f < 2) { return f; }
    return f * factorial(f - 1);
  }

  public void printAllComb() {
    System.out.println("allComb size = " + allComb.size());
    for (int i = 0; i < allComb.size(); ++i) {
      System.out.println(allComb.get(i));
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
