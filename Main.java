// Main.java

// package mastermind;

public class Main {

  public static void main(String[] args) {
    int width = 3;
    int nColors = 3;
    boolean repetition = true;
    System.out.println("Initiazing algorithm...");
    Algorithm alg = new Algorithm(width, nColors, repetition);
    System.out.println("Printing all colors...");
    alg.printAllColors();
    System.out.println("Printing all combinations...");
    alg.printAllCombs();
    System.out.println("Printing answer matrix...");
    alg.printAnswerMatrix();
    System.out.println("Done.");
  }

}
