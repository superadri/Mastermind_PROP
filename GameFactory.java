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

	//private ArrayList<String> listItems;
	//private String[] allPairsGA;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
				// new VistaInstrucciones().hacerVisible();
				GameFactory gf = new GameFactory();
				gf.menu();
    }

	public void menu() throws IOException{
		this.respuesta = "";
		instructions(); // gives user instructions
	 	this.r = new Register();
		this.rank = new Ranking();
		boolean nolimit = true;
		while(nolimit) {
			String username = "";
			Scanner teclado = new Scanner(System.in);
			while (username.equals("")) {
				System.out.print("Escribe un usuario: ");
				username = teclado.nextLine();
			}

			if( r.user_exists(username) ){
				// System.out.println("Existe usuario");
				System.out.println("Cargando Datos...");
				if(r.game_start_user(username)){
					System.out.println(username+" - Quieres continuar la partida anterior? Si(s)/No(n)");
					String respuesta = teclado.nextLine();
					if ( respuesta.equals("s") ) { continuegame(username); }
					else { newgame(username); }
				} else {
        			System.out.println(username+" - Usuario existe!");
					newgame(username);
				}
			} else {
				System.out.println(username+" - Usuario nuevo!");

				newgame(username);
			}
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
      System.out.print("Quieres jugar otra partida? (s/n): ");
			String exitControl = teclado.nextLine();
			if ( exitControl.equals("n") ) { nolimit = false; }
		}
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

    private void newgame(String username) {
        System.out.println("Creando nueva partida...");
        Scanner teclado = new Scanner(System.in);
        // System.out.println("NewGame");
        String player1 = username;
        String player2 = "MACHINE";

        boolean b = false;
        soyCM = false;
				soyCB = false;
        boolean maquina = false;
        String rs;
        while(!b){
            b = true;
            System.out.print("Quieres ser [ CodeMaker(1) / CodeBreaker(2) / MaquinavsMaquina(3) ]: ");
            rs = teclado.nextLine();
            if ( rs.equals("1") ) { soyCM = true; }
            else if ( rs.equals("2") ) { soyCB = true; }
            else if ( rs.equals("3") ) { maquina = true; }
            else { b = false; }
        }

		b = false;
        while(!b){
            b = true;
            System.out.print("Escoge dificultat[ EASY(1) / MEDIUM(2) / HARD(3) ]: ");
            respuesta = teclado.nextLine();
            if (!(respuesta.equals("1") || respuesta.equals("2") || respuesta.equals("3"))) b = false;
        }
        //te paso player2 como MACHINE
		if ( respuesta.equals("1") ) { respuesta = "EASY"; }
		else if ( respuesta.equals("2") ) { respuesta = "MEDIUM"; }
		else if ( respuesta.equals("3") ) { respuesta = "HARD"; }

		if (soyCM) { mastermind = new Mastermind(player1,player2,respuesta); }
		else if (maquina) {mastermind = new Mastermind(player2,player2,respuesta);}
		else { mastermind = new Mastermind(player2,player1,respuesta); }
    }

    private void continuegame(String username) {
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
    }
}
