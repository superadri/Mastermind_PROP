// Main.java

// package mastermind;

public class Main {

  public static void main(String[] args) {
    int width = 4;
    int nColors = 6;
    boolean repetition = true;
    System.out.println("Initiazing algorithm...");
    Algorithm alg = new Algorithm(width, nColors, repetition);
    System.out.println("Printing all colors...");
    alg.printAllColors();
    // System.out.println("Printing all combinations...");
    // alg.printAllComb();
    System.out.println("Done.");
  }

}
