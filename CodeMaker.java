// CodeMaker.java

// package mastermind;

/*

Usage

CodeMaker(int width, int nColors, boolean repetition);
  Creates an instance of the class CodeMaker with the given parameters.

TODO createCode(String difficulty);
  Creates a code depending on the given difficulty.
  This definition as well as the parameters are subject to change.

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

public class CodeMaker extends Play {

  public CodeMaker(int width, int nColors, boolean repetition) {
    this.alg = new Algorithm(width, nColors, repetition);
  }

}
