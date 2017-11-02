
// package mastermind;

import java.util.Scanner;

public class GameFactory {

    public static void main(String[] args) {
        // TODO code application logic here
        instructions(); // gives user instructions

        System.out.println("Escribe usuario");
        Scanner teclado = new Scanner(System.in);
        String username = teclado.nextLine();
        Register r = new Register();

        if(r.user_exists(username)){
            System.out.println("Existe usuario");
            if(r.game_start_user(username)){
                System.out.println("Quieres continuar la anterior partida? SI/NO");
                String respuesta = teclado.nextLine();
                if (respuesta.equals("SI")) continuegame();
                else newgame();
            }
            else {
                newgame();
            }
        }
        else  {
            System.out.println("No existe usuario");

            newgame();
        }

    }

    private static void instructions() {
       // TODO imprimir normas de mastermind
       System.out.println("Instructiones para el juego Mastermind:\n");
       System.out.println("---------------"); //TODO poner normas mastermind


    }

    private static void newgame() {
       System.out.println("NewGame");

    }

    private static void continuegame() {
       System.out.println("ContinueGame");
    }

	// Hay que definir, que clase contien el Main, en este caso, si seguimos el hilo de MainClass,
	// Habría que crear un constructor aquí, e invocarlo Main, o donde sea necesario

	/*
	  // Test Method
	public static void main(String[] args) {
		GameFactory gamefactory = new GameFactory();
	}
	*/
}
