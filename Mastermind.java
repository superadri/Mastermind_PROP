
// package mastermind;

import java.util.ArrayList;

public class Mastermind {

		// Attribute
	public boolean repetition;
	public int height, width, nColors;

	private String computerCM, computerCB;
	private ArrayList<String> listSaveGame;
	private Game game;

		// Constructor
		/*
	public Mastermind(String computerCM, String computerCB, String difficulty, double time, String Code, String [] respuesta){
		respuesta = String [] = { CodeA, answersTipsA }
		game = new Game(gameParameters); <- difficulty
		game.continueGame(time, Code, respuesta);
	}
	*/

	public Mastermind(String computerCM, String computerCB, String difficulty) {
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		setDataNextGame(difficulty);
		this.game = new Game(this);
		game.startNewGame();
		// TODO: Coger los datos necesarios, para salvar la partida -> game.getAttribute();
		// -> Se lo envio de Game a Mastermind por la funcion saveGame

		// Devolver los resultados
			// Sólo si hay que guardarlos
			// System.out.println("ArrayList_Data_Return");
	}

	public void saveGame(String code, String[] allPairsGA) {
		// repetition, height, width, nColors son los mismos, ya los tienes aqui
		System.out.println("code: " + code);
		for (int i = 0; i < allPairsGA.length; ++i) {
			if (i%2 == 0) { System.out.println("Guess number " + i + ": " + allPairsGA[i]); }
			else { System.out.println("Answer: " + allPairsGA[i]); }
		}
	}

		// Getter && Setter

	/*
		public ArrayList<String> getAttributeList() {
			listSaveGame = new ArrayList<String>();

			return listSaveGame
		}
	*/

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

	public int getnColors(){
		return this.nColors;
	}

	public boolean getrepetition(){
		return this.repetition;
	}

	public void setDataNextGame(String difficulty) {
		if ( difficulty.equals("1") ){ // EASY
			this.repetition = false;
			this.nColors = 6;
			this.height = 10;
			this.width = 4;
		} else if ( difficulty.equals("2") ) { // MEDIUM
			this.repetition = false;
			this.nColors = 6;
			this.height = 7;
			this.width = 4;
		} else if ( difficulty.equals("3") ) { // HARD
			this.repetition = true;
			this.nColors = 6;
			this.height = 7;
			this.width = 4;
		}
	}

		// Test Method
	public static void main(String[] args) {
		Mastermind mastermind = new Mastermind("1","1","EASY");
	}
}
