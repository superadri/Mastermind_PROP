// Main.java

// package mastermind;

public class Main {

  public static void main(String[] args) {
    int width = 3;
    int nColors = 3;
    boolean repetition = true;
    //String[] gameParameters = {"1","1","3","3","1"};
    String[] gameParameters = {"1","1","4","6","1"};
    Game game = new Game(gameParameters);
    /*
    System.out.println("Printing all colors...");
    cm.printAllColors();
    System.out.println("Printing all combinations...");
    cm.printAllCombs();
    System.out.println("Printing answer matrix...");
    cm.printAnswerMatrix();
    System.out.println("Done.");
    */
    game.start();
  }

}
