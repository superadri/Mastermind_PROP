
// package mastermind;

import java.util.ArrayList;

public class Mastermind {

		// Attribute
	private Game game;
	private String computerCM, computerCB;
	private Integer height, width, nColors, repetition;
	private ArrayList<String> listSaveGame;

		// Constructor
		/*
	public Mastermind(String computerCM, String computerCB, String difficulty, double time, String Code, String [] respuesta){
		respuesta = String [] = { CodeA, answersTipsA }
		game = new Game(gameParameters); <- difficulty
		game.continueGame(time, Code, respuesta);
	}
	*/

	public Mastermind(String computerCM, String computerCB, String difficulty) {
		boolean controlExe = true;
		while (controlExe) {
			this.computerCM = computerCM;
			this.computerCB = computerCB;
			Integer numCM = 1;
			Integer numCB = 0;
			if (computerCM.equals("MACHINE")) { numCM = 0; }
			if (computerCB.equals("MACHINE")) { numCB = 1; }
			setDataNextGame(difficulty);
			String[] gameParameters = {numCM.toString(), numCB.toString(), this.width.toString(), this.nColors.toString(), this.repetition.toString(), this.height.toString()};
			game = new Game(gameParameters);
			game.startNewGame();
			// TODO: Coger los datos necesarios, para salvar la partida -> game.getAttribute();

			// Devolver los resultados
				// Sólo si hay que guardarlos
				// System.out.println("ArrayList_Data_Return");
		}
	}

		// Getter && Setter

	/*
		public ArrayList<String> getAttributeList() {
			listSaveGame = new ArrayList<String>();

			return listSaveGame
		}
	*/

	public Mastermind objMastermind(){
		return this;
	}

		// name User or Machine
	public String getWhoisCM(){
		return this.computerCM;
	}
		// name User or Machine
	public String getWhoisCB(){
		return this.computerCB;
	}

	public Integer getwidth(){
		return this.width;
	}

	public Integer getnColors(){
		return this.nColors;
	}

	public void setDataNextGame(String difficulty) {
		if ( difficulty.equals("1") ){ // EASY
			this.repetition = 0;
			this.nColors = 6;
			this.height = 10;
			this.width = 4;
		} else if ( difficulty.equals("2") ) { // MEDIUM
			this.repetition = 0;
			this.nColors = 6;
			this.height = 7;
			this.width = 4;
		} else if ( difficulty.equals("3") ) { // HARD
			this.repetition = 1;
			this.nColors = 6;
			this.height = 7;
			this.width = 4;
		}
	}

		// Method
	// mastermind.saveGame(code, board)

		// Test Method
	public static void main(String[] args) {
		Mastermind mastermind = new Mastermind("1","1","EASY");
	}
}
