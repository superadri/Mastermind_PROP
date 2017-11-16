
// package mastermind;

import java.util.ArrayList;

public class Mastermind {

		// Attribute
	public boolean repetition;
	public int height, width, nLetters;
	private String computerCM, computerCB;
	ArrayList<String> listItems;
	String[] allPairsGA;
	private Game game;
	private GameFactory gameFactory;

		// Constructor
		/*
	public Mastermind(String computerCM, String computerCB, String difficulty, double time, String Code, String [] respuesta){
		respuesta = String [] = { CodeA, answersTipsA }
		game = new Game(gameParameters); <- difficulty
		game.continueGame(time, Code, respuesta);
	}
	*/

	public Mastermind(String computerCM, String computerCB, String difficulty, GameFactory gameFactory) {
		this.gameFactory = gameFactory;
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		setDataNextGame(difficulty);
		this.game = new Game(this);
		game.startNewGame();
		gameFactory.saveGame(this.listItems,this.allPairsGA);
	}

	public void saveGame(String code, String[] allPairsGA) {
		// repetition, height, width, nLetters son los mismos, ya los tienes aqui
		listItems = new ArrayList<String>();
		listItems.add(code);
		listItems.add(Integer.toString(height));
		listItems.add(Integer.toString(width));
		listItems.add(Integer.toString(nLetters));
		this.allPairsGA = allPairsGA;
		/*
		System.out.println("code: " + code);
		for (int i = 0; i < allPairsGA.length; ++i) {
			if (i%2 == 0) { System.out.println("Guess number " + i + ": " + allPairsGA[i]); }
			else { System.out.println("Answer: " + allPairsGA[i]); }
		}
		*/
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
