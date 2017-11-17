
// package mastermind;

import java.util.ArrayList;

public class Mastermind {

		// Attribute
	public boolean gameSave;
	public boolean repetition;
	public int height, width, nLetters;
	private String difficulty;
	private String computerCM, computerCB;
	private ArrayList<String> listItems;
	private Game game;

		// Constructor
	// Los parámetros que sean necesarios ADRIAAAA PASAMELOSSS COÑOOOO jajaj XD
	// String computerCM, String computerCB, String difficulty, double time, String Code, String [] respuesta
	// double lastTime, String code, String[] rounds
	public Mastermind(String computerCM, String computerCB, String difficulty, double time, String code, ArrayList<String> respuesta){
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		this.gameSave = false;
		if (difficulty.equals("EASY")) { this.difficulty = "1"; }
		else if (difficulty.equals("MEDIUM")) { this.difficulty = "2"; }
		else if (difficulty.equals("HARD")) { this.difficulty = "3"; }
		setDataNextGame(this.difficulty);
		game = new Game(this);
		String[] respuestaFinal = new String[respuesta.size()];
		for (int i = 0; i < respuesta.size(); ++i) {
			respuestaFinal[i] = respuesta.get(i);
		}
		game.continueGame(time, code, respuestaFinal);
	}

	public Mastermind(String computerCM, String computerCB, String difficulty) {
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		this.gameSave = false;
		this.difficulty = difficulty;
		setDataNextGame(difficulty);
		this.game = new Game(this);
		game.startNewGame();
	}

	public void saveGame(String code, double time, String[] allPairsGA) {
		listItems = new ArrayList<String>();
		this.gameSave = true;
		for (int i = 0; i < allPairsGA.length; ++i) {
			listItems.add(allPairsGA[i]);
		}
		listItems.add(Double.toString(time));
		listItems.add(code);
		String strDifficulty = new String();
		if (this.difficulty.equals("1")) { strDifficulty = "EASY"; }
		else if (this.difficulty.equals("2")) { strDifficulty = "MEDIUM"; }
		else if (this.difficulty.equals("3")) { strDifficulty = "HARD"; }
		listItems.add(strDifficulty);
	}

	public ArrayList<String> saveGametoGameFactory() {
		return listItems;
	}

	public double getTime(){
		return (this.game).getTime();
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
