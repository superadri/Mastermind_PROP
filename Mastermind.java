
// package mastermind;

import java.util.ArrayList;

public class Mastermind {

		// Attribute
	public boolean repetition;
	public int height, width, nLetters;
	private String computerCM, computerCB;
	private ArrayList<String> listItems;
	private Game game;
	private GameFactory gameFactory;

		// Constructor
	// Los parámetros que sean necesarios ADRIAAAA PASAMELOSSS COÑOOOO jajaj XD
	// String computerCM, String computerCB, String difficulty, double time, String Code, String [] respuesta
	// double lastTime, String code, String[] rounds
	public Mastermind(){
		game = new Game(this);
		// game.continueGame(lastTime, code,rounds);
	}

	public Mastermind(String computerCM, String computerCB, String difficulty, GameFactory gameFactory) {
		this.gameFactory = gameFactory;
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		setDataNextGame(difficulty);
		this.game = new Game(this);
		game.startNewGame();
	}

	public void saveGame(String code, int time, String[] allPairsGA) {
		listItems = new ArrayList<String>();
		listItems.add(code);
		listItems.add(Integer.toString(time));
		listItems.add(String.valueOf(this.repetition));
		listItems.add(Integer.toString(this.height));
		listItems.add(Integer.toString(this.width));
		listItems.add(Integer.toString(this.nLetters));
		gameFactory.saveGame(this.listItems,allPairsGA);
	}

		// name User or Machine
	public String getWhoisCM(){
		return this.computerCM;
	}
		// name User or Machine
	public String getWhoisCB(){
		return this.computerCB;
	}

	public int getwidth(){
		return this.width;
	}

	public int getheight(){
		return this.height;
	}

	public int getnLetters(){
		return this.nLetters;
	}

	public boolean getrepetition(){
		return this.repetition;
	}

	public void setDataNextGame(String difficulty) {
		if ( difficulty.equals("1") ){ // EASY
			this.repetition = false;
			this.nLetters = 6;
			this.height = 10;
			this.width = 4;
		} else if ( difficulty.equals("2") ) { // MEDIUM
			this.repetition = false;
			this.nLetters = 6;
			this.height = 7;
			this.width = 4;
		} else if ( difficulty.equals("3") ) { // HARD
			this.repetition = true;
			this.nLetters = 6;
			this.height = 7;
			this.width = 4;
		}
	}

	/*
		// Test Method
	public static void main(String[] args) {
		Mastermind mastermind = new Mastermind("1","1","EASY");
	}
	*/
}
