package domain;

import java.util.List;

import persistence.CtrlPersistence;
import presentation.CtrlPresentacion;

public class CtrlDominio {

    private CtrlPersistence controladorPersistence;

    public GameFactory gameFactory;
    private Register register;
    private Ranking ranking;

    public int width;
    public int nColors;
    public int size;
    public boolean repetition;
    public String[][] answerMatrix;
    public String[] allCombs;
    public String[] allColors;
    public String comb;

	    /** Constructor **/

	public CtrlDominio() {
        this.controladorPersistence = CtrlPersistence.getInstance();
        this.register = new Register(this);
        this.ranking = new Ranking(this);
        this.gameFactory = new GameFactory(this);
        this.comb = "";
        generateColors();
        size = setSize();
        allColors = new String[nColors];
        allCombs = new String[size];
        answerMatrix = new String[size][size];
        if (repetition) {
            setAllCombs(0);
        } else {
            setAllCombsNoRep(0);
        }
	}

        /** Métodos públicos **/

    public Register passRegister() {
        return this.register;
    }

    public Ranking passRanking() {
        return this.ranking;
    }

	public List<String> getNameFileRankings() {
	    return controladorPersistence.getNameFileRankings();
	}

    public List<String> getDataRanking(String nameRank) {
        return controladorPersistence.getDataRanking(nameRank);
    }

    public boolean user_exists(String username){
        return register.user_exists(username);
    }

    public boolean game_start_user(String username) {
        return register.game_start_user(username);
    }

    public void setRoleDificultyNewGame(String username, String role, String difficulty) {
        gameFactory.newgame(username,role,difficulty);
        getMastermindAttributes();
    }

    public void setRoleDificultyContinueGame(String username) {
        gameFactory.continuegame(username);
        getMastermindAttributes();
    }

    private void getMastermindAttributes() {
        this.width = gameFactory.mastermind.width;
        this.nColors = gameFactory.mastermind.nLetters;
        this.repetition = gameFactory.mastermind.repetition;
        this.size = setSize();
        fillAnswerMatrix();
    }

    public String setCode(String code){
        return code;
    }

    public String getGuess(String guesses) {
        return guesses;
    }

    public String getAnswer(String answers) {
        return answers;
    }

    public String setGuess(String guess) {
        return guess;
    }

    public String setAnswer(String answer) {
        return answer;
    }

    private int setSize() {
        if (repetition) {
            return (int)(Math.pow((double)(nColors), (double)(width)));
        }
        return partialPermutations(nColors, width);
    }

    // answerMatrix[i][j] = "la resposta que donaria si haguessim " +
    // + "proposat la combinacio [i] i el code fos la combinacio [j] ?"
    private void fillAnswerMatrix() {
        for (int gi = 0; gi < size; ++gi) {
            for (int ci = 0; ci < size; ++ci) {
                String guess = allCombs[gi];
                String code = allCombs[ci];
                String answer = gameFactory.mastermind.game.calculateAnswer(guess, code);
                answerMatrix[gi][ci] = answer;
            }
        }
    }

    private int partialPermutations(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    private int factorial(int f) {
        if (f == 0) { return 1; }
        return f * factorial(f - 1);
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
}
