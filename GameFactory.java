/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

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
        Scanner teclado = new Scanner(System.in);
        System.out.println("NewGame");
        boolean b = false;
        while(!b){ //por si se equivoca al escoger
            b = true;
            System.out.println("Escoge dificultat: Facil/Medio/Dificil");
            String respuesta = teclado.nextLine();
            if (respuesta.equals("Facil")){}
            else if (respuesta.equals("Medio")){}
            else if (respuesta.equals("Dificil")){}
            else {b = false;}
        }
        Mastermid mastermind = new Mastermind(respuesta);
    }

    private static void continuegame() {
       System.out.println("ContinueGame");
       Mastermind mastermind = new Mastermind();
    }  
}
