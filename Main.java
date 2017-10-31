// Main.java

// package mastermind;

public class Main {

  public static void main(String[] args) {
    int width = 3;
    int nColors = 3;
    boolean repetition = true;
    System.out.println("Initiazing algorithm...");
    Play p = new Play(width, nColors, repetition);
    System.out.println("Printing all colors...");
    p.printAllColors();
    System.out.println("Printing all combinations...");
    p.printAllCombs();
    System.out.println("Printing answer matrix...");
    p.printAnswerMatrix();
    System.out.println("Done.");
  }

}
