// Board.java

// package mastermind;

/*

Usage

Board();
  Creates an instance of the class Board with the given parameters.

void setCode(String code);
  Sets the Mastermind code to the combination specified in the parameters.

String getCode();
  Returns the Mastermind code.

void setGuess(String guess);
  Sets the next guess to the combination specified in the parameters.

String getCode(int n);
  Returns the guess combination with number specified in the parameters.

*/

public class Board {

  public String[] guesses, answers;

  private int height, currentAttempt;
  private String code;

  public Board(int height) {
    this.height = height;
    this.guesses = new String[height];
    this.answers = new String[height];
    this.currentAttempt = 0;
  }

  public String[] getAllPairsGA() {
    String[] allPairsGA = new String[2*currentAttempt];
    for (int i = 0; i < currentAttempt; ++i) {
      allPairsGA[2*i] = getGuess(i);
      allPairsGA[2*i+1] = getAnswer(i);
    }
    return allPairsGA;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setGuessAndAnswer(String guess, String answer) {
    setGuess(guess);
    setAnswer(answer);
    ++currentAttempt;
  }

  public String getGuess(int gi) {
    return guesses[gi];
  }

  public String getAnswer(int ai) {
    return answers[ai];
  }

  private void setGuess(String guess) {
    guesses[currentAttempt] = guess;
  }

  private void setAnswer(String answer) {
    answers[currentAttempt] = answer;
  }

}
