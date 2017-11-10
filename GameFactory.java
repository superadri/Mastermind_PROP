// package mastermind;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Sony
 */
public class GameFactory {

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        instructions(); // gives user instructions

        Register r = new Register();
		boolean nolimit = true;
		while(nolimit) {
	        System.out.println("Escribe usuario");
	        Scanner teclado = new Scanner(System.in);
	        String username = teclado.nextLine();

	        if(r.user_exists(username)){
	            System.out.println("Existe usuario");
	            if(r.game_start_user(username)){
	                System.out.println("Quieres continuar la anterior partida? SI/NO");
	                String respuesta = teclado.nextLine();
	                if (respuesta.equals("SI")) continuegame();
	                else newgame(username);
	            }
	            else {
	                newgame(username);
	            }
	        }
	        else  {
	            System.out.println("No existe usuario");
	            newgame(username);
	        }

			// if (Mastermind mastermind == exit) break;
			// Devolución ->
			// ArrayList l = mastermind.getAttributeList();
		}
    }

    private static void instructions() {
       // TODO imprimir normas de mastermind
       System.out.println("Instructiones para el juego Mastermind:\n");
       System.out.println("---------------"); //TODO poner normas mastermind

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

    private static void newgame(String username) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("NewGame");
        boolean b = false;
        String player1 = username;
        String player2 = "MACHINE";

        boolean soyCM = false;
        String rs;
         while(!b){
            b = true;
            System.out.println("Quieres ser CodeBreaker o CodeMaker?\nCodeBreaker->CB\nCodeMaker->CM");
            rs = teclado.nextLine();
            if (rs.equals("CM"))soyCM = true;
            else if (rs.equals("CB")) soyCM = false;
            else {b = false;}
        }

		b = false;
        String respuesta = "";
        while(!b){
            b = true;
            System.out.println("Escoge dificultat: EASY/MEDIUM/HARD");
            respuesta = teclado.nextLine();
            if (!(respuesta.equals("EASY") || respuesta.equals("MEDIUM") || respuesta.equals("HARD"))) b = false;
        }
		Mastermind mastermind;
        //te paso player2 como MACHINE
       if (soyCM) { mastermind = new Mastermind(player1,player2,respuesta); }
       else { mastermind = new Mastermind(player2,player1,respuesta); }
    }

    private static void continuegame() {
       System.out.println("ContinueGame");

       //Mastermind mastermind = new Mastermind();
    }
}
