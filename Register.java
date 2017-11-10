package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Register {
    private ArrayList<Player> users = new ArrayList<Player>();
    private ArrayList<String> nomUsers = new ArrayList<String>();
    
    
    
   
    public Register() throws FileNotFoundException {
        File f = new File("src/mastermind/registro.txt");
        Scanner in = new Scanner(f);
        in.useLocale(Locale.ENGLISH);

        while(in.hasNext()){
             
           String username = in.next();
           nomUsers.add(username);
            //pendingGame
            String a =in.next();
            char c = a.charAt(0);
            
            if (a.equals("1")){
                //entonces hay mas lineas
                double time = in.nextDouble();
                
                //respuestas
                int i = in.nextInt() * 2;
                ArrayList<String> respuestas = new ArrayList<>();
                while (i > 0) {
                    respuestas.add(in.next());
                    --i;
                }
      
                //codigo
                String codigo = in.next();
                Player p = new Player(c,username,respuestas,time,codigo);
                users.add(p);
            }
            else {
               Player p = new Player(c,username);
                users.add(p);
            }
            }
    }

    public boolean user_exists(String username){
        return nomUsers.contains(username);
    }

     public boolean game_start_user(String username){
         int i = 0;
         boolean trobat = false;
         Player p = null;
         while(i < users.size() && !trobat){
             p = users.get(i);
             if (p.getUsername().equals(username)) trobat = true;
             ++i;
         }
         char a = p.getGame_start();
        return p.getGame_start()== '1';
    }

	// Nota -> revisar la creación del file, sino existe, y si ya existe, que lo abra

	/*
		// Test Method
	public static void main(String[] args) {
		Register register = new Register();
	}
	*/
}