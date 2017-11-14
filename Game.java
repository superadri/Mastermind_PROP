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

import java.util.Scanner;

public class Game {

  public boolean computerCM;
  public boolean computerCB;
  public CodeMaker cm;
  public CodeBreaker cb;
  private String code;
  public String guess;
  public String answer;
  public Time time;
  public Board board;
  //public Player playerCM;
  //public Player playerCB;

  int turn;
  int width;
  int height;
  int nColors;
  boolean repetition;
  double lastTime;
  double currentTime;

	public Game(String[] gameParameters) {
    this.time = new Time();
    this.turn = 0;
		this.code = ""; // Initialization just in case
		this.guess = "";
		this.answer = "";
		this.computerCM = Integer.parseInt(gameParameters[0]) != 0;
		this.computerCB = Integer.parseInt(gameParameters[1]) != 0;
		this.width = Integer.parseInt(gameParameters[2]);
		this.nColors = Integer.parseInt(gameParameters[3]);
		this.repetition = Integer.parseInt(gameParameters[4]) != 0;
    this.height = Integer.parseInt(gameParameters[5]);
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
    this.board = new Board(height);
	}

  public void continueGame(double lastTime, String code, String[] rounds) {
    this.lastTime = lastTime;
    this.code = code;
    board.setCode(code);
    System.out.println("Showing previous attempts:");
    for (int i = 0; i < rounds.length; ++i) {
      String guess = rounds[i];
      String answer = rounds[i + 1];
      board.setGuessAndAnswer(guess, answer);
			System.out.println("CodeBreaker: guess = " + board.getGuess(turn));
			System.out.println("Game: answer = " + board.getAnswer(turn));
      if (computerCB) { cb.updateDiscarded(guess, answer); }
      ++this.turn;
    }
    runGame();
  }

	public void startNewGame() {
		this.lastTime = 0;
		System.out.println("Starting new game...");
		Play cmplay = new Play(this, "CODEMAKER");
		cmplay.makePlay();
		board.setCode(code);
		runGame();
	}

	public void runGame() {
		time.startTime();
		System.out.println("CodeMaker: code = " + code);
    	String continuePlaying = "";
		do {
			Play cbplay = new Play(this, "CODEBREAKER");
			cbplay.makePlay();
			board.setGuessAndAnswer(guess, answer);
			System.out.println("CodeBreaker: guess = " + board.getGuess(turn));
			System.out.println("Game: answer = " + board.getAnswer(turn));
  			++turn;
      if (!guess.equals(code)) { continuePlaying = askContinue(); }
		} while (!continuePlaying.equals("n") && !guess.equals(code));
    time.stopTime();
    double currentTime = time.getTime();
    currentTime += lastTime;
    if (continuePlaying.equals("n")) { askSaveGame(); }
    else { System.out.println("Game: code guessed in " + turn + " turns."); }
	}

  public void sendCode(Play play, String code) {
    if (play.role.equals("CODEMAKER")) { this.code = code; }
  }

  public String sendGuess(String sentGuess) {
    guess = sentGuess;
    answer = calculateAnswer(guess, code);
    return answer;
  }

  public void printAnswerMatrix() {
    cb.printAnswerMatrix();
  }

  public String calculateAnswer(String g) {
    return calculateAnswer(g, this.code);
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

  private String askContinue() {
    System.out.print("Continue playing? (y/n): ");
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  private void askSaveGame() {
      System.out.print("Save game? (y/n): ");
      Scanner sc = new Scanner(System.in);
      String saveGame = sc.nextLine();
      if (saveGame.equals("y")) { saveGame(); }
  }

  private void saveGame() {
    System.out.println("mastermind.saveGame(code, board)");
  }

	  // Test Method
	/*
	public static void main(String[] args) {
		String[] gameParameters = {"1","1","4","6","0"};
		Game game = new Game(gameParameters);
	}
	*/

}
