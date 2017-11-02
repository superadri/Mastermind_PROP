// Main.java

// package mastermind;

public class Main {

	public static void main(String[] args) {
		int width = 4;
		int nColors = 6;
		boolean repetition = false;
			// this.computerCM, this.computerCB, this.width, this.nColors, this.repetition
		String[] gameParameters = {"1","1","4","6","0"};
		Game game = new Game(gameParameters);
		Time t = new Time();
		/*
		System.out.println("Printing all colors...");
		cm.printAllColors();
		System.out.println("Printing all combinations...");
		cm.printAllCombs();
		System.out.println("Printing answer matrix...");
		cm.printAnswerMatrix();
		System.out.println("Done.");
		*/
		t.startTime();
		game.start();
		t.stopTime();

		System.out.println("Texe: "+t.getTime()+"s");
	}

}
