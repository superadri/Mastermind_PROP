package domain;

/*

Usage

CodeBreaker(int width, int nColors, boolean repetition);
  Creates an instance of the class CodeBreaker with the given parameters.

String playCombination();
  Returns a String that represents the combination to be played.
  Gets the answer to the previously proposed combination from the class Game
  and updates its internal tracking of the state of the game.

void printAnswerMatrix();
  Prints which answer would be given for all possible combinations of
  proposed combination / code.

void printAllCombs();
  Prints all the possible combinations.

void printSingleComb(String combination);
  Prints the String it receives as a parameter.
  Intended for printing combinations.

void printAllColors();
  Prints all the possible colors.

void printSingleColor(String color);
  Prints the String it receives as a parameter.
  Intended for printing colors.

void testMinDiscard();
  Prints the list of minimum combinations that a guess would discard.

*/

import java.util.Scanner;

public class CodeBreaker extends Algorithm {

    private CtrlDominio controladorDominio;
    private boolean random;

    public CodeBreaker(CtrlDominio controladorDominio,Game game, int width, int nColors, boolean repetition) {
        this.random = false;
        this.controladorDominio = controladorDominio;
        this.comb = "";
        this.guessIndex = 0; // Initialization just in case
        this.game = game;
        this.width = width;
        this.nColors = nColors;
        this.repetition = repetition;
        this.allColors = new String[nColors];
        generateColors();
        this.size = 0; // Initialization just in case
        size = setSize();
        this.allCombs = new String[size];
        if (repetition) {
            setAllCombs(0);
        } else {
            setAllCombsNoRep(0);
        }
        this.discarded = new boolean[size];
        this.minDiscard = new int[size];
        for (int i = 0; i < size; ++i) { discarded[i] = false; }
        this.answerMatrix = new String[size][size];
        fillAnswerMatrix();
        //printAnswerMatrix();
    }

    public CodeBreaker(CtrlDominio controladorDominio,Game game, int width, int nColors, boolean repetition, String[][] am) {
        this.controladorDominio = controladorDominio;
        this.comb = "";
        this.guessIndex = 0; // Initialization just in case
        this.game = game;
        this.width = width;
        this.nColors = nColors;
        this.repetition = repetition;
        this.allColors = new String[nColors];
        generateColors();
        this.size = 0; // Initialization just in case
        size = setSize();
        this.allCombs = new String[size];
        if (repetition) {
            setAllCombs(0);
        } else {
            setAllCombsNoRep(0);
        }
        this.discarded = new boolean[size];
        this.minDiscard = new int[size];
        for (int i = 0; i < size; ++i) { discarded[i] = false; }
        this.answerMatrix = am;
        //printAnswerMatrix();
    }

    public void setRandomCB() {
        this.random = true;
    }

    public void playCombination() {
        if (random) {
            int rnum = (int)(Math.floor(allCombs.length * Math.random()));
            while (discarded[rnum]) { ++rnum; if (rnum >= allCombs.length) { rnum = 0; } } // cycle through options
            String guess = allCombs[rnum];
            String answer = game.sendGuess(guess);
            updateDiscarded(answer);
        } else {
            getMin();
            String guess = getMaxMin();
            String answer = game.sendGuess(guess);
            updateDiscarded(answer);
        }
    }

    public void testMinDiscard() {
        getMin();
        printMinDiscard();
    }

    public void printAnswerMatrix() {
        for (int i = 0; i < width; ++i) { System.out.print(" "); }
        System.out.print(" ");
        for (int i = 0; i < size; ++i) { System.out.print(allCombs[i] + " "); }
        System.out.println();
        for (int i = 0; i < size; ++i) {
            System.out.print(allCombs[i] + " ");
            for (int j = 0; j < size; ++j) {
                System.out.print(answerMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setAnswerMatrix() {
        controladorDominio.setAnswerMatrix(this.answerMatrix);
    }

    public void printAllCombs() {
        System.out.println("size = " + size + ", allCombs size = " +
                allCombs.length);
        for (int i = 0; i < allCombs.length; ++i) {
            System.out.println(allCombs[i]);
        }
    }

    public void printSingleComb(String combination) {
        System.out.println(combination);
    }

    public void printAllColors() {
        for (int i = 0; i < allColors.length; ++i) {
            System.out.println(allColors[i]);
        }
    }

    public void printSingleColor(String color) {
        System.out.println(color);
    }

    public void printMinDiscard() {
        System.out.println("Printing minDiscard for all combinations...");
        for (int i = 0; i < size; ++i) {
            System.out.println(allCombs[i] + ": " + minDiscard[i]);
        }
    }

    public void updateDiscarded(String guess, String answer) {
        int gi = 0;
        for (int i = 0; i < size; ++i) {
            if (allCombs[i].equals(guess)) {
                gi = i;
                break;
            }
        }
        for (int ci = 0; ci < size; ++ci) {
            if (!answerMatrix[gi][ci].equals(answer)) { discarded[ci] = true; }
        }
    }

    // If it is possible that the combination[ci] is the code, then the answer
    // received to the guess combination[gi] would have been equal to the
    // answer stored in the answerMatrix. Otherwise, combination[ci] is NOT
    // the possible code, so it gets discarded.
    private void updateDiscarded(String answer) {
        //Scanner sc = new Scanner(System.in);
        int gi = guessIndex;
        for (int ci = 0; ci < size; ++ci) {
            if (!answerMatrix[gi][ci].equals(answer)) { discarded[ci] = true; }
            //System.out.println("guess " + allCombs[gi] + " if code was " + allCombs[ci] + " answer would've been " + answerMatrix[gi][ci] + " and it got " + answer + " so" +
            //" discarded[" + ci + "] is " + discarded[ci] + ".");
            //String dontCare = sc.nextLine();
        }
    }

    // TODO improve efficiency: now it's O(n3)
    // Note: discarded guesses will get min=size, but doesn't matter because
    // getMaxMin only checks non-discarded combinations.
    private void getMin() {
        //Scanner sc = new Scanner(System.in);
        // for each guess
        for (int gi = 0; gi < size; ++gi) {
            int min = size;
            // for each code
            for (int ci = 0; !discarded[gi] && ci != gi && ci < size; ++ci) {
                int count = 0;
                // for all answers
                for (int ai = 0; !discarded[ci] && ai < size; ++ai) {
                    if (!discarded[ai] && !answerMatrix[gi][ai].equals(
                            answerMatrix[gi][ci])) { ++count; }
                }
                if (count < min) { min = count; }
                //System.out.println("guess " + allCombs[gi] + " has " + count + " codes with answers different from " + answerMatrix[gi][ci] + ".");
                //String dontCare = sc.nextLine();
            }
            minDiscard[gi] = min;
            //System.out.println("minDiscard for " + allCombs[gi] + " is " + minDiscard[gi] + " or " + min + ".");
            //String dontCare = sc.nextLine();
        }
    }

    // For each non-discarded guess, if it would discard more options than the
    // maximum up until now, then choose that guess.
    private String getMaxMin() {
        //Scanner sc = new Scanner(System.in);
        int max = -1;
        for (int gi = 0; gi < size; ++gi) {
            if (!discarded[gi] && minDiscard[gi] > max) {
                //System.out.println(allCombs[gi] + " is not discarded and has a minDiscard of " + minDiscard[gi] + " which is greater than the current maximum which is " + max + ".");
                //String dontCare = sc.nextLine();
                max = minDiscard[gi];
                guessIndex = gi;
            }
        }
        //System.out.println("max(minDiscard) is " + max + " which corresponds to " + allCombs[guessIndex] + ".");
        //String dontCare = sc.nextLine();
        return allCombs[guessIndex];
    }

    // answerMatrix[i][j] = "la resposta que donaria si haguessim " +
    // + "proposat la combinacio [i] i el code fos la combinacio [j] ?"
    private void fillAnswerMatrix() {
        for (int gi = 0; gi < size; ++gi) {
            for (int ci = 0; ci < size; ++ci) {
                String guess = allCombs[gi];
                String code = allCombs[ci];
                String answer = game.calculateAnswer(guess, code);
                answerMatrix[gi][ci] = answer;
            }
        }
    }

    private int setSize() {
        if (repetition) {
            return (int)(Math.pow((double)(nColors), (double)(width)));
        }
        return partialPermutations(nColors, width);
    }

    private void generateColors() {
        for (char c = 'A'; c < (char)('A' + nColors); ++c) {
            String color = new String();
            color += c;
            int i = (int)(c - 'A');
            allColors[i] = color;
        }
    }

    private int setAllCombs(int n) {
        if (comb.length() < width) {
            for (int i = 0; i < allColors.length; ++i) {
                String oldComb = comb;
                comb += allColors[i];
                n = setAllCombs(n);
                comb = oldComb;
            }
        } else {
            allCombs[n] = comb;
            ++n;
        }
        return n;
    }

    private int setAllCombsNoRep(int n) {
        if (comb.length() < width) {
            for (int i = 0; i < allColors.length; ++i) {
                String oldComb = comb;
                if (!comb.contains(allColors[i])) {
                    comb += allColors[i];
                    n = setAllCombsNoRep(n);
                }
                comb = oldComb;
            }
        } else {
            allCombs[n] = comb;
            ++n;
        }
        return n;
    }

    private int partialPermutations(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    private int factorial(int f) {
        if (f == 0) { return 1; }
        return f * factorial(f - 1);
    }

}