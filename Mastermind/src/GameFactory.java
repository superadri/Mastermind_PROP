//package mastermind;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Sony
 */
public class GameFactory {

	private Mastermind mastermind;
	private Register r;
	private Ranking rank;
	private boolean soyCM,soyCB;
	public String respuesta;
	public CtrlPresentacion ctrlP;
	public String username;

	//private ArrayList<String> listItems;
	//private String[] allPairsGA;



	public void menu(CtrlPresentacion ctrlP) throws IOException{
    	this.ctrlP = ctrlP;
		this.respuesta = "";
		instructions(); // gives user instructions
	 	this.r = new Register();
		this.rank = new Ranking();
	}


	public void setUsername(String username) throws IOException{
		this.username = username;
		Scanner teclado = new Scanner(System.in);
		boolean exists = r.user_exists(username);
		if( exists ){
			if(r.game_start_user(username))ctrlP.userExits();
			else ctrlP.newgameView();

		} else {
			ctrlP.newgameView();
		}
	}

	private void endFunction() throws IOException {
		Double strTime;
		String strCode = "";
		String strDifficulty = respuesta;
		int turns = 0;
		String role;
		if (soyCM) { role = "CM"; }
		else if (soyCB) { role = "CB"; }
		else { role = "CM";}

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
			r.set_continueGame('1',username,respuestas,strTime,strCode,strDifficulty,role);
			System.out.print("1 "+username+" ");
			for (int i = 0; i < limitList; ++i) { System.out.print(listItems.get(i)+" "); }
			for (int i = limitList; i < limitList+3; ++i) { System.out.print(listItems.get(i)+" "); }
			System.out.println(role);
		} else {
			strTime = mastermind.getTime();
			r.finished_game(username);
			rank.updateRanking(strDifficulty, turns, strTime, username,role);
		}
		rank.showRanking(strDifficulty,role);
		ctrlP.initGame();
	}

	private void instructions() {
       // TODO imprimir normas de mastermind
        System.out.println("Instructiones para el juego Mastermind:\n");
		System.out.println("	Mastermind - Rules of the game\n");
		System.out.println("		B -> Correct Pos & Letter");
		System.out.println("		R -> Correct Letter\n");
        System.out.println("---------------\n");

	   	//TODO poner normas mastermind

	   /*
			Mastermind - Rules of the game.
        - The computer picks a sequence of colors. The number of colors is the code length.
       The default code length is 4 but it can be changed when starting a new game.
        - The objective of the game is to guess the exact positions of the colors in the computer's sequence.
        - By default, a color can be used only once in a code sequence. If you start a new game with
       the 'Allow duplicates' checked, then any color can be used any number of times in the code sequence.
        - After filling a line with your guesses and clicking on the 'Check' button, the computer
       responses with the result of your guess.
        - For each color in your guess that is in the correct color and correct position in the code sequence,
       the computer display a small red color on the right side of the current guess.
        - For each color in your guess that is in the correct color but is NOT in the correct position in the code sequence, the computer display a small white color on the right side of the current guess.
        - You win the game when you manage to guess all the colors in the code sequence and when they all in the right position.
        - You lose the game if you use all attempts without guessing the computer code sequence.
        How to play this game:
        - Start a new game by clicking on the 'Start New Game' button. If you wish to change the default game parameters, you may change the 'Code length' and/or the 'Allow duplicates' fields before clicking on the 'Start new game' button.
        - To start filling a line, you must first select a color at the bottom of the table by clicking on it. After selecting a color you can just put it in the current guess line above by clicking on the desired position.
        - To change the color selection, you have two ways. One is clicking on a new color on one of the colors at the bottom and the other way is just by scrolling the mouse wheel.
        - After filling a whole line, you can still change your selection before asking the computer to respond to your guess. When you're satisfied with your guess, just click on the 'Check' button and get the computer response.
        - Good Luck!!
	   */

    }

    public void newgame(String role, String difficulty) throws IOException {
        System.out.println("Creando nueva partida...");
        // System.out.println("NewGame");
        String player1 = username;
        String player2 = "MACHINE";

        boolean b = false;
        soyCM = false;
        soyCB = false;
        boolean maquina = false;
        respuesta = difficulty;

		if ( role.equals("CM") ) { soyCM = true; }
		else if ( role.equals("CB") ) { soyCB = true; }
		else if ( role.equals("MACHINE") ) { maquina = true; }
		else { b = false; }


		if (soyCM) { mastermind = new Mastermind(player1,player2,respuesta); }
		else if (maquina) {mastermind = new Mastermind(player2,player2,respuesta);}
		else { mastermind = new Mastermind(player2,player1,respuesta); }
		endFunction();
    }

    public void continuegame() throws IOException {
       System.out.println("ContinueGame");
	   	// TODO: Tienes que pasarme todos estos parámetros, para que pueda restablecer una partida
		// Si son, estos los parámetros necesarios
        Player p = r.getPlayer(username);
        String rol = p.getRol();
				String computerCM, computerCB;
        if (rol.equals("CM")) {
            computerCM = username;
            computerCB = "MACHINE";
        }
        else {
            computerCB = username;
            computerCM = "MACHINE";
        }
        String difficulty = p.getDificultat();
				respuesta = difficulty;
				System.out.println("Player"+respuesta);
        double time = p.getTime();
        String Code = p.getCodigo();
        ArrayList<String>respuesta = p.getRespuestas();
       	mastermind = new Mastermind(computerCM, computerCB, difficulty,time, Code,respuesta);
       	endFunction();
    }
}
