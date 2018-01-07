package persistence;

import domain.Player_User;
import domain.Register;

import java.io.*;
import java.util.*;

public class CtrlPersistence {
	
	private static CtrlPersistence singletonObject;

    public static CtrlPersistence getInstance() {
		if (singletonObject == null) { singletonObject = new CtrlPersistence(){}; }
		return singletonObject;
	}

	    /** Constructora privada **/
	
	private CtrlPersistence() {
	}

    public List<String> getNameFileRankings() {
        LinkedList<String> dataNR = new LinkedList<String>();

        try {
            File[] files = new File("./persistence").listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    String extension = file.getName();
                    if (extension.contains(".")) {
                        boolean found = false;
                        StringTokenizer tokens = new StringTokenizer(extension, ".");
                        while (tokens.hasMoreTokens()) {
                            if (tokens.nextToken().equals("registro")) { break; }
                            found = tokens.nextToken().equals("txt");
                        }
                        if (found) { dataNR.add(file.getName()); }
                    } else { throw new IllegalArgumentException("String " + extension + " no contiene ."); }
                }
            }
        } catch (NullPointerException ex) { System.out.println("Error2: " + ex); }
        Collections.sort(dataNR);
        return dataNR;
    }

    public List<String> getDataRanking(String nameRank) {
        LinkedList<String> dataR = new LinkedList<String>();
        try {
            FileReader fr = new FileReader("./persistence/" + nameRank);
            Scanner scan = new Scanner(fr);
            while (scan.hasNextLine()) {
                dataR.add(new String(scan.nextLine()));
            }
            scan.close();
            fr.close();
        } catch (IOException ex) { System.out.println("Error3: " + ex); }

        return dataR;
    }

    public HashMap<String, Player_User> getListUsers() {
        HashMap<String, Player_User> listUsers = new HashMap<String, Player_User>();
        try {
            FileReader f = new FileReader("./persistence/registro.txt");
            Scanner in = new Scanner(f);
            in.useLocale(Locale.ENGLISH);
            Player_User player;
            while (in.hasNext()) {
                String username = in.next();
                String a = in.next();
                char c = a.charAt(0);
                if (a.equals("1")) {
                    double time = in.nextDouble();
                    int i = in.nextInt() * 2;
                    ArrayList<String> respuestas = new ArrayList<String>();
                    while (i > 0) {
                        respuestas.add(in.next());
                        --i;
                    }
                    String codigo = in.next();
                    String dificultat = in.next();
                    String rol = in.next();
                    player = new Player_User(c, username, respuestas, time, codigo, dificultat, rol);
                } else { player = new Player_User(c, username); }
                listUsers.put(username, player);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return listUsers;
    }

    public void finished_Game(String newNameUser, Register register) throws IOException {
        File filename = new File("./persistence/registro.txt");
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        Player_User player;
        if ( !register.user_exists(newNameUser) ){
            player = new Player_User('0',newNameUser);
            register.listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            if (filename.length() != 0) { stringBuilder.append("\n"+newNameUser+" 0 "); }
            else { stringBuilder.append(newNameUser+" 0 "); }
        } else {
            player = register.listUsers.get(newNameUser);
            if ( player.getGame_start() == '1' ) { player.delete_all(); }
            fw = new FileWriter(filename);
            boolean controlIni = true;
            for ( Player_User ply : register.listUsers.values() ) {
                if (!controlIni) { stringBuilder.append("\n"+ply.getUsername()+" "+ply.getGame_start()); }
                else {
                    stringBuilder.append(ply.getUsername()+" "+ply.getGame_start());
                    controlIni = false;
                }
                if (ply.getGame_start() == '1') {
                    stringBuilder.append(" "+ply.getTime()+" ");
                    ArrayList<String> respuestasFinal = ply.getRespuestas();
                    stringBuilder.append(respuestasFinal.size()/2+" ");
                    for ( String itr1 : respuestasFinal ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(ply.getCodigo()+" "+ply.getDificultat()+" "+ply.getRol());
                }
            }
        }
        fw.write(stringBuilder.toString());//appends the string to the file
        fw.close();
    }

    public void finished_Game_CM_MvM(String newNameUser, Register register) throws IOException {
        File filename = new File("./persistence/registro.txt");
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        Player_User player;
        if ( !register.user_exists(newNameUser) ){
            player = new Player_User('0',newNameUser);
            register.listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            if (filename.length() != 0) { stringBuilder.append("\n"+newNameUser+" 0 "); }
            else { stringBuilder.append(newNameUser+" 0 "); }
            fw.write(stringBuilder.toString());//appends the string to the file
            fw.close();
        }
    }

    public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas, double time, String codigo, String dificultat, String rol, Register register) throws IOException {
        File filename = new File("./persistence/registro.txt");
        Player_User player;
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        if ( !register.user_exists(newNameUser) ){
            player = new Player_User('1',newNameUser,respuestas,time,codigo,dificultat,rol);
            register.listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            if (filename.length() != 0) { stringBuilder.append("\n"+newNameUser+" 1 "); }
            else { stringBuilder.append(newNameUser+" 1 "); }
            String timeS = String.valueOf(time);
            stringBuilder.append(timeS+" ");
            String size = String.valueOf(respuestas.size()/2);
            stringBuilder.append(size+" ");
            for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
            stringBuilder.append(codigo+" "+dificultat+" "+rol);
        } else {
            player = register.listUsers.get(newNameUser);
            player.setSaveGame(respuestas, time, codigo, dificultat, rol);
            fw = new FileWriter(filename);
            boolean controlIni = true;
            for ( Player_User ply : register.listUsers.values() ) {
                if (!controlIni) { stringBuilder.append("\n"+ply.getUsername()+" "+ply.getGame_start()); }
                else {
                    stringBuilder.append(ply.getUsername()+" "+ply.getGame_start());
                    controlIni = false;
                }
                if (ply.getGame_start() == '1') {
                    stringBuilder.append(" "+ ply.getTime()+" ");
                    ArrayList<String> respuestasFinal = ply.getRespuestas();
                    stringBuilder.append(respuestasFinal.size()/2+" ");
                    for ( String itr1 : respuestasFinal ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(ply.getCodigo()+" "+ply.getDificultat()+" "+ply.getRol());
                }
            }
        }
        fw.write(stringBuilder.toString());//appends the string to the file
        fw.close();
    }
}
