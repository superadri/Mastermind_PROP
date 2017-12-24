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
        else { mastermind = new Mastermind(controladorDominio, username,"MACHINE", difficult); }
    }

    public void newgameMachine(String username, String role, String difficult, int numGames) {
        System.out.println("GameFactory - Creando nueva partida...");
        String roleMachine = "MACHINEC";
        if (role.equals("Machine vs Machine(Random)")) { roleMachine = "MACHINER"; }
        mastermind = new Mastermind(controladorDominio,roleMachine,"MACHINE", difficult);
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

