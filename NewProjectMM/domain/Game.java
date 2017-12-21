package domain;

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

    private CtrlDominio controladorDominio;

    public boolean computerCM, computerCB, repetition;
  public CodeMaker cm;
  public CodeBreaker cb;
  public String guess, answer;
  public Time time;
  public Board board;
  public int turn, width, height, nLetters;
  public double lastTime, currentTime;
  //public Player playerCM, playerCB;

  private Mastermind mastermind;
  private String code;

	public Game(CtrlDominio controladorDominio, Mastermind mastermind) {
	    this.controladorDominio = controladorDominio;
	    this.mastermind = mastermind;
	    this.time = new Time();
	    this.turn = 0;
		this.code = this.guess = this.answer = ""; // Initialization just in case
		this.width = mastermind.getwidth();
		this.nLetters = mastermind.getnLetters();
		this.repetition = mastermind.getrepetition();
    	this.height = mastermind.getheight();
		System.out.println("Initial Configuration>");
		System.out.println("width = " + this.width + ", nLetters = " + this.nLetters + ", repetition = " + this.repetition + ".");
        if ((mastermind.getWhoisCM()).equals("MACHINE")) {
            computerCM = true;
            System.out.println("Initiazing CodeMaker algorithm...");
            this.cm = new CodeMaker(controladorDominio);
        } else { computerCM = false; }
        if ((mastermind.getWhoisCB()).equals("MACHINE")) {
            computerCB = true;
            System.out.println("Initiazing CodeBreaker algorithm...");
            this.cb = new CodeBreaker(controladorDominio, this);
        } else { computerCB = false; }
        this.board = new Board(controladorDominio, height);
	}

  public void continueGame(double lastTime, String code, String[] rounds) {
    this.lastTime = lastTime;
    this.code = code;
    board.setCode(code);
    System.out.println("Showing previous attempts:");
    for (int i = 0; i < rounds.length; i+=2) {
        this.guess = rounds[i];
        this.answer = rounds[i + 1];
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
    	if (!repetition) { System.out.print("NO REPETITIONS ALLOWED -> "); }
		Play cmplay = new Play(controladorDominio, this, "CODEMAKER");
		cmplay.makePlay();
		board.setCode(code);
		runGame();
	}

	public void runGame() {
		time.startTime();
		System.out.println("CodeMaker: code = " + code);
		String continuePlaying = "";
		do {
			Play cbplay = new Play(controladorDominio, this, "CODEBREAKER");
			cbplay.makePlay();
			board.setGuessAndAnswer(guess, answer);
			System.out.println("CodeBreaker: guess = " + board.getGuess(turn));
			System.out.println("Game: answer = " + board.getAnswer(turn));
			++turn;
			if (!guess.equals(code)) { continuePlaying = askContinue(); }
		} while (turn < height && !continuePlaying.equals("n") && !guess.equals(code));
		time.stopTime();
		currentTime = time.getTime();
		currentTime += lastTime;
		time.printTime(currentTime);
		if (continuePlaying.equals("n")) { askSaveGame(); }
		else if (guess.equals(code)) { System.out.println("Game: code guessed in " + turn + " turns."); }
		else { System.out.println("You didn't guess the code."); }
	}

	public double getTime(){
		return this.currentTime;
	}

	public boolean getIsComputerCM(){
		return this.computerCM;
	}

	public boolean getIsComputerCB(){
		return this.computerCB;
	}

	public Integer getwidth(){
		return this.width;
	}

	public Integer getheight(){
		return this.height;
	}

	public Integer getnLetters(){
		return this.nLetters;
	}

	public boolean getrepetition(){
		return this.repetition;
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
	    mastermind.saveGame(this.code, this.currentTime, board.getAllPairsGA());
    }

      // Test Method
    /*
    public static void main(String[] args) {
        String[] gameParameters = {"1","1","4","6","0"};
        Game game = new Game(gameParameters);
    }
    */

}
