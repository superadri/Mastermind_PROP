
// package mastermind;

public class Mastermind {

		// Attribute
		Time t; // Esta clase, no va aquí, sólo es para probar
		private Game game;
		private String computerCM, computerCB;
		private Integer height, width, nColors, repetition;

		// Constructor
	public Mastermind(String computerCM, String computerCB, String respuesta) {
		this.computerCM = computerCM;
		this.computerCB = computerCB;
		setDataNextGame(respuesta);
		String[] gameParameters = {computerCM, computerCB, this.width.toString(), this.nColors.toString(), this.repetition.toString()};
		game = new Game(gameParameters);
		t = new Time();

		t.startTime();
		game.start();
		t.stopTime();

		System.out.println("Texe: "+t.getTime()+"s");
	}

		// Getter && Setter

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
			if (difficulty == "EASY"){
				this.repetition = 1;
				this.nColors = 6;
				this.height = 5;
				this.width = 4;
			} else if (difficulty == "MEDIUM") {
				this.repetition = 1;
				this.nColors = 5;
				this.height = 6;
				this.width = 4;
			} else if (difficulty == "HARD") {
				this.repetition = 0;
				this.nColors = 4;
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
