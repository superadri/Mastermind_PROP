package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

public class Register {
    private ArrayList<Player> users = new ArrayList<Player>();
    private ArrayList<String> nomUsers = new ArrayList<String>();

    public Register() {
  		try {
  		//File f = new File("persistency/registro.txt");
            File f = new File("persistency/registro.txt");
  	        Scanner in = new Scanner(f);
  	        in.useLocale(Locale.ENGLISH);
  	        while(in.hasNext()){
  	           String username = in.next();
  	           nomUsers.add(username);
  	            //pendingGame
  	            String a = in.next();
  	            char c = a.charAt(0);
  	            if (a.equals("1")){
  	                //entonces hay mas lineas
  	                double time = in.nextDouble();
  	                //respuestas
  	                int i = in.nextInt() * 2;
  	                ArrayList<String> respuestas = new ArrayList<String>();
  	                while (i > 0) {
  	                    respuestas.add(in.next());
  	                    --i;
  	                }
  	                //codigo
  	                String codigo = in.next();
                    String dificultat = in.next();
                    String rol = in.next();
  	                Player p = new Player(c,username,respuestas,time,codigo,dificultat,rol);
  	                users.add(p);
  	            }
  	            else {
  	               Player p = new Player(c,username);
  	               users.add(p);
  	            }
  	        }
  		} catch (FileNotFoundException ex) {
  			ex.printStackTrace();
  		}
    }

    public boolean user_exists(String username){
        return nomUsers.contains(username);
    }
     public Player getPlayer(String username){
        int i = pos_player (username);
        Player p = users.get(i);
        return p;
    }

    public boolean game_start_user(String username){
        int i = 0;
        boolean trobat = false;
        Player p = null;
        while(i < users.size() && !trobat){
            p = users.get(i);
            if (p.getUsername().equals(username)) trobat = true;
            else ++i;
        }
        char a = p.getGame_start();
        return p.getGame_start()== '1';
   }

    public int pos_player(String username){
        int i = 0;
        boolean trobat = false;
        Player p = null;
        while(i < users.size() && !trobat){
            p = users.get(i);
            if (p.getUsername().equals(username)) trobat = true;
            else ++i;
        }
        return i;
   }

    public void finished_game (String newuser) throws IOException{
        //File f = new File("persistency/registro.txt");
        String ruta = "persistency/registro.txt";
        File filename = new File(ruta);

        if (!user_exists(newuser)){
            nomUsers.add(newuser);
            Player p = new Player('0',newuser);
	    	    users.add(p);
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(newuser+" 0\n");
            fw.write(stringBuilder.toString());//appends the string to the file
            fw.close();
        }
        else {
            Player p = users.get(pos_player(newuser));
            if (p.getGame_start() == '1') p.delete_all();
            FileWriter fw = new FileWriter(filename);
            Iterator<Player> itr = users.iterator();
            while (itr.hasNext()) {
               StringBuilder stringBuilder = new StringBuilder();
                p = itr.next();
                stringBuilder.append(p.getUsername()+" "+p.getGame_start());
                if (p.getGame_start() == '1') {
                    stringBuilder.append(" "+p.getTime()+" ");
                    ArrayList<String> respuestas = p.getRespuestas();
                    stringBuilder.append(respuestas.size()/2+" ");
                    Iterator<String> itr1 = respuestas.iterator();
                    while (itr1.hasNext()) stringBuilder.append(itr1.next()+" ");
                    stringBuilder.append(p.getCodigo()+" "+p.getDificultat()+" "+p.getRol());
                }
                stringBuilder.append("\n");
                fw.write(stringBuilder.toString());
            }
             fw.close();
        }
    }
     public void set_continueGame (char game_start, String newuser, ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) throws IOException{
         //File f = new File("persistency/registro.txt");
        String ruta = "persistency/registro.txt";
        File filename = new File(ruta);
        if (!user_exists(newuser)){
            nomUsers.add(newuser);
            Player p = new Player('1',newuser,respuestas,time,codigo,dificultat,rol);
            users.add(p);
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(newuser+" 1 ");
            String cadena = String.valueOf(time);
            stringBuilder.append(cadena+" ");
            String size = String.valueOf(respuestas.size()/2);
            stringBuilder.append(size+" ");
            Iterator<String> itr = respuestas.iterator();
            while (itr.hasNext()) stringBuilder.append(itr.next()+" ");
            stringBuilder.append(codigo+" "+dificultat+" "+rol+"\n");
            fw.write(stringBuilder.toString());//appends the string to the file
            fw.close();
        }
        else {
           //hay que modifcar player
           //hay que modificar fichero
            Player p = users.get(pos_player(newuser));
            p.setSaveGame(respuestas,time,codigo,dificultat,rol);

            //escribimos
            FileWriter fw = new FileWriter(filename);

            Iterator<Player> itr = users.iterator();
            while (itr.hasNext()) {
                StringBuilder stringBuilder = new StringBuilder();
                p = itr.next();
                stringBuilder.append(p.getUsername()+" "+p.getGame_start());
                if (p.getGame_start() == '1') {
                    stringBuilder.append(" "+ p.getTime()+" ");
                    ArrayList<String> respuestas1 = p.getRespuestas();
                    stringBuilder.append(respuestas1.size()/2+" ");
                    Iterator<String> itr1 = respuestas1.iterator();
                    while (itr1.hasNext()) stringBuilder.append(itr1.next()+" ");
                    stringBuilder.append(p.getCodigo()+" "+p.getDificultat()+" "+p.getRol());
                }
                stringBuilder.append("\n");
                fw.write(stringBuilder.toString());
            }
             fw.close();
        }

    }

	// Nota -> revisar la creación del file, sino existe, y si ya existe, que lo abra

	/*
		// Test Method
	public static void main(String[] args) {
		Register register = new Register();
	}
	*/
}
