/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sony
 */
public class Register {
    ArrayList<Person> users = new ArrayList();
    ArrayList<String> nomUsers = new ArrayList();
    public Register() {
        try {
            File f = new File("src/mastermind/registro.txt");
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String line = s.nextLine();
                add_to_array(line);  
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean user_exists(String username){
        return nomUsers.contains(username);
    }
    @SuppressWarnings("null")
     public boolean game_start_user(String username){
         int i = 0;
         boolean trobat = false;
         Person p = null;
         while(i < users.size() && !trobat){
             p = users.get(i);
             if (p.getUsername().equals(username)) trobat = true;
             ++i;
         }
         char a = p.getGame_start();
        return p.getGame_start()== '1';
    }

    private void add_to_array(String line) {
        int i = 0;
        StringBuilder username = new StringBuilder();
        while(i < line.length() && line.charAt(i) != ',' ){
            char a = line.charAt(i);
            username.append(a);
            ++i;
        }
        String usern = username.toString();
        nomUsers .add(usern);
        char a = line.charAt(i+1);

        Person p = new Person(a,usern);
        users.add(p);   
    }   
}
