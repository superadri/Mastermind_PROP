package domain;

import java.io.IOException;
import java.util.ArrayList;

public class GameFactory {

    private CtrlDominio controladorDominio;

    public Mastermind mastermind;

    public GameFactory (CtrlDominio controladorDominio){
        this.controladorDominio = controladorDominio;
    }

    public void newgame(String username, String role, String difficult) {
        System.out.println("GameFactory - Creando nueva partida...");
        if (role.equals("CB")) { mastermind = new Mastermind(controladorDominio, "MACHINE", username, difficult); }
        else {
            ArrayList<String> result = new ArrayList<String>();
            mastermind = new Mastermind(controladorDominio, username,"MACHINE", difficult);
            // Aquí hacer while, coger las respuestas y devolver result
            this.mastermind.game.startNewGame();
            while ( this.mastermind.game.turn < controladorDominio.getHeight() ) {
                if (!this.mastermind.game.board.getAnswer(this.mastermind.game.turn-1).equals("BBBB")) {
                    mastermind.game.runGame();
                } else {
                    controladorDominio.setRounds(this.mastermind.game.board.getAllPairsGA());
                    break;
                }
            }
        }
    }

    public void newgameMachine(String username, String role, String difficult, int numGames) {
        String roleMachine = "MACHINEC";
        if (role.equals("Machine vs Machine(Random)")) { roleMachine = "MACHINER"; }
        controladorDominio.setWhoMachine(roleMachine);
        System.out.println("GameFactory - Creando nueva partida(MACHINE vs "+roleMachine+")...");
        mastermind = new Mastermind(controladorDominio,"MACHINE", "MACHINE", difficult);
        boolean controlMachine = true;
        for (int i = 0; i < numGames; ++i) {
            this.mastermind.game.startNewGame();
            while ( this.mastermind.game.turn < controladorDominio.getHeight() ) {
                this.mastermind.game.runGame();
                if (this.mastermind.game.board.getAnswer(this.mastermind.game.turn-1).equals("BBBB")) {
                    controlMachine = false;
                    System.out.println(this.mastermind.game.turn);
                    int num = controladorDominio.getNumRightGame();
                    controladorDominio.setNumRightGame(++num);
                    if (i+1 < numGames) { mastermind = new Mastermind(controladorDominio,"MACHINE", "MACHINE", difficult); }
                    break;
                }
                System.out.println(this.mastermind.game.turn);
            }
            if (controlMachine && i+1 < numGames) { mastermind = new Mastermind(controladorDominio,"MACHINE", "MACHINE", difficult); }
            controlMachine = false;
        }
    }

    public void continuegame(String username) {
        System.out.println("GameFactory - ContinueGame");
        Player player = controladorDominio.passRegister().getPlayer(username);
        String computerCM, computerCB;
        computerCB = username;
        computerCM = "MACHINE";
        if (player.getRol().equals("CM")) {
            computerCM = username;
            computerCB = "MACHINE";
        }
        controladorDominio.passUpdateDataDifficultyAndRole(player.getDificultat(),player.getRol());
        mastermind = new Mastermind(controladorDominio, computerCM, computerCB, player.getDificultat(), player.getTime(), player.getCodigo(), player.getRespuestas());
    }

    public void set_continueGame(Double strTime, String strCode, boolean gameSave, Register register, Ranking ranking, String username, String role, String strDifficulty, ArrayList<String> respuestas) throws IOException {
        if (gameSave) {
            register.set_continueGame('1',username, respuestas, strTime, strCode, strDifficulty, role);
        } else {
            register.finished_game(username);
            ranking.updateRanking(strDifficulty,strTime ,username, role);
        }
    }

    /*
        public static void main(String[] args) throws IOException {
                    // new VistaInstrucciones().hacerVisible();
                    GameFactory gf = new GameFactory();
                    gf.menu();
        }
    */
}

