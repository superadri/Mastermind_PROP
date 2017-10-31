// Play.java

// package mastermind;

/*

Usage

Play(int width, int nColors, boolean repetition);
  Creates an instance of the class Play with the given parameters.

String playCombination();
  Returns a String that represents the combination to be played.

void shareAnswer(String answer);
  Lets the algorithm know the answer to the previously proposed combination so
  that it can update its internal tracking of the state of the game.

void printAnswerMatrix();
  Prints which answer would be given for all possible combinations of
  proposed combination / code.

void printAllCombs();
  Prints all the possible combinations.

void printSingleComb(String combination);
  Prints the String it receives as a parameter.
  Intended for printing combinations.

void printAllColors();
  Prints all the possible colors.

void printSingleColor(String color);
  Prints the String it receives as a parameter.
  Intended for printing colors.

*/

public class Play {

  private Algorithm alg;

  public Play(int width, int nColors, boolean repetition) {
    this.alg = new Algorithm(width, nColors, repetition);
  }

  public String playCombination() {
    return alg.playCombination();
  }

  public void shareAnswer(String answer) {
    alg.shareAnswer(answer);
  }

  public void printAnswerMatrix() {
    alg.printAnswerMatrix();
  }

  public void printAllCombs() {
    alg.printAllCombs();
  }

  public void printSingleComb(String combination) {
    alg.printSingleComb(combination);
  }

  public void printAllColors() {
    alg.printAllColors();
  }

  public void printSingleColor(String color) {
    alg.printSingleColor(color);
  }

}
