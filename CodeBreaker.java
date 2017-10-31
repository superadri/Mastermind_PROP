// CodeBreaker.java

// package mastermind;

/*

Usage

CodeBreaker(int width, int nColors, boolean repetition);
  Creates an instance of the class CodeBreaker with the given parameters.

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

public class CodeBreaker extends Play {

  public CodeBreaker(int width, int nColors, boolean repetition) {
    this.alg = new Algorithm(width, nColors, repetition);
  }

  public String playCombination() {
    return alg.playCombination();
  }

  public void shareAnswer(String answer) {
    alg.shareAnswer(answer);
  }

}
