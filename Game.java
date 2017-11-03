// Game.java

// package mastermind;

/*
	Usage

	Game(String[] gameParameters);
	  Creates an instance of the class Game with the given parameters.
	  Used to create a new game.

	TODO Game(String[] gameParameters, boolean continueGame);
	  Creates an instance of the class Game with the given parameters.
	  Used to continue a saved game.

  String sendGuess(String sentGuess);
    Sends a guess to this class (Game).
    Used by CodeBreaker algorithm.

*/

public class Game {

  public boolean computerCM;
  public boolean computerCB;
  public CodeMaker cm;
  public CodeBreaker cb;
  private String code;
  private String guess;
  private String answer;
  // public Player playerOne
  // public Player playerTwo
  // public Time time;
  // public Board board;

  int width;
  int nColors;
  boolean repetition;

	public Game(String[] gameParameters) {
		this.code = ""; // Initialization just in case
		this.guess = "";
		this.answer = "";
		this.computerCM = Integer.parseInt(gameParameters[0]) != 0;
		this.computerCB = Integer.parseInt(gameParameters[1]) != 0;
		this.width = Integer.parseInt(gameParameters[2]);
		this.nColors = Integer.parseInt(gameParameters[3]);
		this.repetition = Integer.parseInt(gameParameters[4]) != 0;
		System.out.println("Initial Configuration>");
		System.out.println("width = " + this.width +
      ", nColors = " + this.nColors +
      ", repetition = " + this.repetition + ".");
		if (computerCM) {
			System.out.println("Initiazing CodeMaker algorithm...");
			this.cm = new CodeMaker(width, nColors, repetition);
		}
		if (computerCB) {
			System.out.println("Initiazing CodeBreaker algorithm...");
			this.cb = new CodeBreaker(this, width, nColors, repetition);
		}
	}

  public Game(String[] gameParameters, boolean continueGame) {
    // TODO continue saved game
    // asuming class Game receives gameParameters read by another class from
    // the register or something.
  }

	public void start() {
    int turn = 0;
		System.out.println("Starting new game...");
		if (computerCM) {
			// code = cm.createCode("EASY");
			// code = cm.createCode("HARDCODER");
      code = "FEDC";
		} else { code = scanCombination(); }
		System.out.println("CodeMaker: code = " + code);
		do {
			if (computerCB) { cb.playCombination(); }
			else {
        guess = scanCombination();
        answer = calculateAnswer(guess, code);
      }
			System.out.println("CodeBreaker: guess = " + guess);
			System.out.println("Game: answer = " + answer);
      ++turn;
		} while (!guess.equals(code));
    System.out.println("Game: code guessed in " + turn + " turns.");
	}

  public String sendGuess(String sentGuess) {
    guess = sentGuess;
    answer = calculateAnswer(guess, code);
    return answer;
  }

  public void printAnswerMatrix() {
    cb.printAnswerMatrix();
  }

  // TODO repeated code from CodeBreaker!!!
  // Also, if this code stays here, it doesn't need the parameters.
  public String calculateAnswer(String g, String c) {
		char[] guess = g.toCharArray();
		char[] code = c.toCharArray();
		String answer = "";
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
		for (int i = 0; i < width; ++i) {
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
		return answer;
	}

  private String scanCombination() {
    // TODO
    return "";
  }

	  // Test Method
	/*
	public static void main(String[] args) {
		String[] gameParameters = {"1","1","4","6","0"};
		Game game = new Game(gameParameters);
	}
	*/

}
