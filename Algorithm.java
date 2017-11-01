// Algorithm.java

// package mastermind;

/*

Usage

Algorithm(int width, int nColors, boolean repetition);
  Creates an instance of the class Algorithm with the given parameters.

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

public class Algorithm {

  public int width;
  public int nColors;
  public int size;
  public boolean repetition;
  public String[] allColors;
  public String[] allCombs;
  public boolean[] discarded;
  public int[] minDiscard;
  public String[][] answerMatrix;
  public String comb;
  public int combIndex;

  // TODO is anything necessary here?

}
