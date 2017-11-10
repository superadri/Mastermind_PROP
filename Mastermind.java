
// package mastermind;

public class Mastermind {

		// Attribute
		Time t; // Esta clase, no va aquí, sólo es para probar
		private Game game;
		private String computerCM, computerCB;
		private Integer height, width, nColors, repetition;

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
		Integer numCM = 1;
		Integer numCB = 0;
		if (computerCM.equals("MACHINE")) { numCM = 0; }
		if (computerCB.equals("MACHINE")) { numCB = 1; }
		setDataNextGame(difficulty);
		String[] gameParameters = {numCM.toString(), numCB.toString(), this.width.toString(), this.nColors.toString(), this.repetition.toString(), this.height.toString()};
		game = new Game(gameParameters);

		t = new Time();

		t.startTime();
		game.startNewGame();
		// game.getAttribute();
		t.stopTime();

		System.out.println("Texe: "+t.getTime()+"s");

		// Devolver los resultados

		System.out.println("ArrayList_Data_Return");
	}

		// Getter && Setter
		/*
		public ArrayList getAttributeList() {
			return ArrayList(...)
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

		public Integer getwidth(){
			return this.width;
		}

		public Integer getnColors(){
			return this.nColors;
		}

		public void setDataNextGame(String difficulty) {
			if ( difficulty.equals("EASY") ){
				this.repetition = 0;
				this.nColors = 6;
				this.height = 10;
				this.width = 4;
			} else if ( difficulty.equals("MEDIUM") ) {
				this.repetition = 0;
				this.nColors = 6;
				this.height = 7;
				this.width = 4;
			} else if ( difficulty.equals("HARD") ) {
				this.repetition = 1;
				this.nColors = 6;
				this.height = 7;
				this.width = 4;
			}
		}

		// Method



		// Test Method
	public static void main(String[] args) {
		Mastermind mastermind = new Mastermind("1","1","EASY");
	}
}
