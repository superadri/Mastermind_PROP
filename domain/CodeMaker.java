package domain;

/*
	Usage

	CodeMaker(int width, int nColors, boolean repetition);
	  Creates an instance of the class CodeMaker with the given parameters.

	TODO createCode(String difficulty);
	  Creates a code depending on the given difficulty.
	  This definition as well as the parameters are subject to change.
*/

public class CodeMaker extends Algorithm {

    private CtrlDominio ctrlDominio;

    public CodeMaker(CtrlDominio controladorDominio, int width, boolean repetition, int nColors) {
        this.ctrlDominio = controladorDominio;
        this.repetition = repetition;
        this.nColors = nColors;
        this.comb = "";
        this.width = width;
        this.allColors = new String[nColors];
        generateColors();
        this.size = 0; // Initialization just in case
        size = setSize();
        this.allCombs = new String[size];
        if (repetition) { setAllCombs(0); }
        else { setAllCombsNoRep(0); }
    }

    public String createCode() {
        String code = randomCode();
        return code;
    }

    private String randomCode() {
        int random = (int)(Math.floor(allCombs.length * Math.random()));
        return allCombs[random];
    }

    private int setSize() {
        if (repetition) { return (int)(Math.pow((double)(nColors), (double)(width))); }
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

    public void printAllCombs() {
        System.out.println("size = " + size + ", allCombs size = " +
                allCombs.length);
        for (int i = 0; i < allCombs.length; ++i) {
            System.out.println(allCombs[i]);
        }
    }

    /*
    public static void main(String[] arg) {
        CtrlDominio cdomain = new CtrlDominio();
        CodeMaker cb = new CodeMaker(cdomain, 4,false,6);
        // cb.printAllCombs();
        System.out.println(cb.randomCode());
        System.out.println(cb.setSize());
    }
    */
}

