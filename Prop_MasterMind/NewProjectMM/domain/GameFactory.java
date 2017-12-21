package domain;

import java.io.IOException;
import java.util.ArrayList;

public class GameFactory {

    private CtrlDominio controladorDominio;

    private Mastermind mastermind;

    public GameFactory (CtrlDominio controladorDominio){
        this.controladorDominio = controladorDominio;
    }

    public void newgame(String username, String role, String difficult) {
        System.out.println("GameFactory - Creando nueva partida...");
		if (role.equals("CM")) { mastermind = new Mastermind(controladorDominio, username,"MACHINE", difficult); }
		else if (role.equals("CB")) { mastermind = new Mastermind(controladorDominio, "MACHINE", username, difficult);}
		else { mastermind = new Mastermind(controladorDominio,"MACHINE","MACHINE", difficult); }
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
}

    /*
        public static void main(String[] args) throws IOException {
                    // new VistaInstrucciones().hacerVisible();
                    GameFactory gf = new GameFactory();
                    gf.menu();
        }
    */


    /*
	public void menu() throws IOException{

			if (mastermind.gameSave) {
				ArrayList<String> listItems = new ArrayList<String>();
				listItems = mastermind.saveGametoGameFactory();
				ArrayList<String> respuestas = new ArrayList<String>();
				int limitList = listItems.size()-3;
				for (int i = 0; i < limitList; ++i) {
					respuestas.add(listItems.get(i));
				}
				turns = limitList/2;
				strTime = Double.parseDouble(listItems.get(limitList));
				strCode = listItems.get(limitList+1);
				strDifficulty = listItems.get(limitList+2);
				// hasPausedGame username respuestas time code difficulty
				register.set_continueGame('1',username, respuestas, strTime, strCode, strDifficulty, role);
				System.out.print("1 "+username+" ");
				for (int i = 0; i < limitList; ++i) { System.out.print(listItems.get(i)+" "); }
				for (int i = limitList; i < limitList+3; ++i) { System.out.print(listItems.get(i)+" "); }
				System.out.println(role);
			} else {
				strTime = mastermind.getTime();
				register.finished_game(username);
				ranking.updateRanking(strDifficulty, turns, strTime, username, role);
			}
			ranking.showRanking(strDifficulty,role);
      		System.out.print("Quieres jugar otra partida? (s/n): ");
			String exitControl = teclado.nextLine();
			if ( exitControl.equals("n") ) { nolimit = false; }
		}
	}
	*/
