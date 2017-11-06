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

  public String[] rows;
  private String code;
  private int height;
  private int index;

  public Board(int height) {
    this.height = height;
    this.rows = new String[height];
    this.index = 0;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setGuess(String guess) {
    try { rows[index] = guess; }
    catch(Exception e) {
      if (index >= height) { System.out.println("No more guesses available."); }
      else { System.out.println("ERROR"); }
    }
  }

  public String getCode(int n) {
    try { return rows[n]; }
    catch(Exception e) {
      if (n < 0 || n >= height) { System.out.println("Index out of bounds"); }
      else { System.out.println("ERROR"); }
    }
    return "";
  }

}
