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

  public String[] guesses;
  public String[] answers;
  private int height;
  private int index;
  private String code;

  public Board(int height) {
    this.height = height;
    this.guesses = new String[height];
    this.answers = new String[height];
    this.index = 0;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setGuessAndAnswer(String guess, String answer) {
    setGuess(guess);
    setAnswer(answer);
    ++index;
  }

  public String getGuess(int g) {
    return guesses[g];
  }

  public String getAnswer(int a) {
    return answers[a];
  }

  private void setGuess(String guess) {
    guesses[index] = guess;
  }

  private void setAnswer(String answer) {
    answers[index] = answer;
  }

}
