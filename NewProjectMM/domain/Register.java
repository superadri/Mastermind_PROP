package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Register {

    private  CtrlDominio controladorDominio;

    private HashMap<String, Player> listUsers;
    
    public Register(CtrlDominio controladorDominio) {
        this.controladorDominio = controladorDominio;
        listUsers = new HashMap<String, Player>();
  		try {
            File f = new File("./persistence/registro.txt");
            Player player;
            Scanner in = new Scanner(f);
  	        in.useLocale(Locale.ENGLISH);
  	        while(in.hasNext()) {
  	           String username = in.next();
  	            //pendingGame
  	            String a = in.next();
  	            char c = a.charAt(0);
  	            if (a.equals("1")) {
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
  	                player = new Player(c,username,respuestas,time,codigo,dificultat,rol);
  	            } else { player = new Player(c,username); }
  	            listUsers.put(username,player);
  	        }
  		} catch (FileNotFoundException ex) { ex.printStackTrace(); }
    }

    public boolean user_exists(String username){ return listUsers.containsKey(username); }

    public Player getPlayer(String username){ return listUsers.get(username); }

    public boolean game_start_user(String username){ return listUsers.get(username).getGame_start() == '1'; }

    public void finished_game(String newNameUser) throws IOException{
        File filename = new File("./persistence/registro.txt");
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        Player player;
        if (!user_exists(newNameUser)){
            player = new Player('0',newNameUser);
            listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            stringBuilder.append(newNameUser+" 0\n");
        } else {
            player = listUsers.get(newNameUser);
            if (player.getGame_start() == '1') { player.delete_all(); }
            fw = new FileWriter(filename);
            for ( Player itr : listUsers.values() ) {
                player = itr;
                stringBuilder.append(player.getUsername()+" "+player.getGame_start());
                if (player.getGame_start() == '1') {
                    stringBuilder.append(" "+player.getTime()+" ");
                    ArrayList<String> respuestas = player.getRespuestas();
                    stringBuilder.append(respuestas.size()/2+" ");
                    for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(player.getCodigo()+" "+player.getDificultat()+" "+player.getRol());
                }
                stringBuilder.append("\n");
            }
        }
        fw.write(stringBuilder.toString());//appends the string to the file
        fw.close();
    }
     public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) throws IOException{
         File filename = new File("./persistence/registro.txt");
         Player player;
         FileWriter fw;
         StringBuilder stringBuilder = new StringBuilder();
         if (!user_exists(newNameUser)){
            player = new Player('1',newNameUser,respuestas,time,codigo,dificultat,rol);
            listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            stringBuilder.append(newNameUser+" 1 ");
            String cadena = String.valueOf(time);
            stringBuilder.append(cadena+" ");
            String size = String.valueOf(respuestas.size()/2);
            stringBuilder.append(size+" ");
            for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
            stringBuilder.append(codigo+" "+dificultat+" "+rol+"\n");
        }
        else {
            player = listUsers.get(newNameUser);
            player.setSaveGame(respuestas,time,codigo,dificultat,rol);
            fw = new FileWriter(filename);
            for ( Player itr : listUsers.values() ) {
                player = itr;
                stringBuilder.append(player.getUsername()+" "+player.getGame_start());
                if (player.getGame_start() == '1') {
                    stringBuilder.append(" "+ player.getTime()+" ");
                    ArrayList<String> respuestas1 = player.getRespuestas();
                    stringBuilder.append(respuestas1.size()/2+" ");
                    for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(player.getCodigo()+" "+player.getDificultat()+" "+player.getRol());
                }
                stringBuilder.append("\n");
            }
        }
         fw.write(stringBuilder.toString());//appends the string to the file
         fw.close();
    }

	/*
	public static void main(String[] args) {
		Register register = new Register();
	}
	*/
}
