// Main.java

// package mastermind;

public class Main {

  public static void main(String[] args) {
    int width = 3;
    int nColors = 3;
    boolean repetition = true;
    System.out.println("Initiazing algorithm...");
    CodeMaker cm = new CodeMaker(width, nColors, repetition);
    CodeBreaker cb = new CodeBreaker(width, nColors, repetition);
    System.out.println("Printing all colors...");
    cm.printAllColors();
    System.out.println("Printing all combinations...");
    cm.printAllCombs();
    System.out.println("Printing answer matrix...");
    cm.printAnswerMatrix();
    System.out.println("Done.");
  }

}
