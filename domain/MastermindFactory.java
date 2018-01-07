package domain;

import java.io.IOException;
import java.util.ArrayList;

public class MastermindFactory {

    private CtrlDominio controladorDominio;

    public Mastermind mastermind;

    public String[] allCombs, allColors;
    public String comb;
    public int width, size, nColors;
    public boolean repetition;

    public MastermindFactory(CtrlDominio controladorDominio){
        this.controladorDominio = controladorDominio;
    }

    public void newgame(String username, String role, String difficult) {
        System.out.println("MastermindFactory - Creando nueva partida...");
        if (role.equals("CB")) { mastermind = new Mastermind(controladorDominio, "MACHINE", username, difficult); }
        else {
            mastermind = new Mastermind(controladorDominio, username,"MACHINE", difficult);
            try { controladorDominio.getRegister().finished_game_CM_MvM(username); }
            catch (IOException e) { e.printStackTrace(); }
            while ( this.mastermind.game.turn < controladorDominio.getHeight() ) {
                if ( !mastermind.controlFin ) { this.mastermind.game.runGame(); }
                else { break; }
            }
            controladorDominio.setRounds(this.mastermind.game.board.getAllPairsGA());
            System.out.println(this.mastermind.game.turn + " NewGame");
        }
    }

    public void newgameMachine(String username, String role, String difficult, int numGames) {
        String roleMachine = "MACHINEC";
        if (role.equals("Machine vs Machine(Random)")) { roleMachine = "MACHINER"; }
        controladorDominio.setWhoMachine(roleMachine);
        System.out.println("MastermindFactory - Creando nueva partida(MACHINE vs "+roleMachine+")...");
        this.comb = "";
        this.width = 4;
        this.nColors = 6;
        this.repetition = difficult.equals("HARD");
        this.allColors = new String[nColors];
        generateColors();
        this.size = setSize();
        this.allCombs = new String[size];
        if (repetition) { setAllCombs(0); }
        else { setAllCombsNoRep(0); }
        String[][] answerMatrix = new String[size][size];
        fillAnswerMatrix(answerMatrix);
        mastermind = new Mastermind(controladorDominio,"MACHINE", "MACHINE", difficult, answerMatrix);
        try { controladorDominio.getRegister().finished_game_CM_MvM(username); }
        catch (IOException e) { e.printStackTrace(); }
        boolean controlMachine = true;
        for (int i = 0; i < numGames; ++i) {
            System.out.println("Num de Games jugados: "+(i+1));
            while ( this.mastermind.game.turn < controladorDominio.getHeight() ) {
                if ( !mastermind.controlFin ) { this.mastermind.game.runGame(); }
                else {
                    System.out.println(this.mastermind.game.turn + " NewGame Machine Won");
                    int num = controladorDominio.getNumRightGame();
                    controladorDominio.setNumRightGame(++num);
                    controlMachine = false;
                    if (i+1 < numGames) { mastermind = new Mastermind(controladorDominio,"MACHINE", "MACHINE", difficult, answerMatrix); }
                    break;
                }
            }
            if(controlMachine) {
                if (mastermind.controlFin) {
                    System.out.println(this.mastermind.game.turn + " NewGame Machine End");
                    int num = controladorDominio.getNumRightGame();
                    controladorDominio.setNumRightGame(++num);
                }
                if (i + 1 < numGames) {
                    mastermind = new Mastermind(controladorDominio, "MACHINE", "MACHINE", difficult, answerMatrix);
                }
            } else { controlMachine = true; }
        }
    }

    public void continuegame(String username) {
        System.out.println("MastermindFactory - ContinueGame");
        Player_User player = controladorDominio.getRegister().getPlayer(username);
        String computerCM, computerCB;
        computerCB = username;
        computerCM = "MACHINE";
        if (player.getRol().equals("CM")) {
            computerCM = username;
            computerCB = "MACHINE";
        }
        controladorDominio.passUpdateDataDifficultyAndRole(player.getDificultat(), player.getRol());
        mastermind = new Mastermind(controladorDominio, computerCM, computerCB, player.getDificultat(), player.getTime(), player.getCodigo(), player.getRespuestas());
    }

    public void set_continueGameRegister(Double strTime, String strCode, Register register, String username, String role, String strDifficulty, ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers) throws IOException {
        ArrayList<String> respuestas = new ArrayList<String>();
        for (int i = 0; i < codeOutAnswers.size(); ++i) {
            respuestas.add(codeOutGuess.get(i));
            respuestas.add(codeOutAnswers.get(i));
        }
        register.set_continueGame('1',username, respuestas, strTime, strCode, strDifficulty, role);
    }

    public void set_continueGameRanking(Double strTime, Register register, Ranking ranking, String username, String role, String strDifficulty) throws IOException {
        register.finished_game(username);
        ranking.updateRanking(strDifficulty, strTime, username, role);
    }

    private int factorial(int f) {
        if (f == 0) { return 1; }
        return f * factorial(f - 1);
    }

    private int partialPermutations(int n, int k) {
        return factorial(n) / factorial(n - k);
    }

    private int setSize() {
        if (repetition) {
            return (int)(Math.pow((double)(nColors), (double)(width)));
        }
        return partialPermutations(nColors, width);
    }

    public String calculateAnswer(String g, String c) {
        int width = 4;
        char[] guess = g.toCharArray();
        char[] code = c.toCharArray();
        String answer = "";
        boolean[] guessChecked = new boolean[width];
        boolean[] codeChecked = new boolean[width];
        for (int i = 0; i < width; ++i) {
            guessChecked[i] = false;
            codeChecked[i] = false;
        }
        // first pass: Black pins -> color & position correct
        for (int i = 0; i < width; ++i) {
            if (guess[i] == code[i]) {
                answer += 'B'; // B for Black pin
                guessChecked[i] = true;
                codeChecked[i] = true;
            }
        }
        // second pass: Red pins: color correct & position incorrect
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width && !guessChecked[i]; ++j) {
                if (!codeChecked[j] && guess[i] == code[j]) {
                    answer += 'R'; // R for Red pin
                    guessChecked[i] = true;
                    codeChecked[j] = true;
                }
            }
        }
        // third pass: X (no pin): color incorrect & position incorrect
        // fill remaining slots with X
        for (int i = 0; i < width; ++i) {
            if (!guessChecked[i]) {
                answer += "X"; // X for no pin
            }
        }
        return answer;
    }

    private void fillAnswerMatrix(String[][] answerMatrix) {
        for (int gi = 0; gi < size; ++gi) {
            for (int ci = 0; ci < size; ++ci) {
                String guess = allCombs[gi];
                String code = allCombs[ci];
                String answer = calculateAnswer(guess, code);
                answerMatrix[gi][ci] = answer;
            }
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

    private void generateColors() {
        for (char c = 'A'; c < (char)('A' + nColors); ++c) {
            String color = new String();
            color += c;
            int i = (int)(c - 'A');
            allColors[i] = color;
        }
    }

    /*
        public static void main(String[] args) throws IOException {
                    // new VistaInstrucciones().hacerVisible();
                    MastermindFactory gf = new MastermindFactory();
                    gf.menu();
        }
    */
}

