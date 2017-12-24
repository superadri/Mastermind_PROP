package persistence;

import domain.Player;
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

    public HashMap<String, Player> getListUsers() {
        HashMap<String, Player> listUsers = new HashMap<String, Player>();
        try {
            File f = new File("./persistence/registro.txt");
            Scanner in = new Scanner(f);
            Player player;
            in.useLocale(Locale.ENGLISH);
            while (in.hasNextLine()) {
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
                    player = new Player(c, username, respuestas, time, codigo, dificultat, rol);
                } else {
                    player = new Player(c, username);
                }
                listUsers.put(username, player);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return listUsers;
    }

    public void finished_Game(String newNameUser, Register register) throws IOException {
        File filename = new File("./persistence/registro.txt");
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        Player player;
        if (!register.user_exists(newNameUser)){
            player = new Player('0',newNameUser);
            register.listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            stringBuilder.append("\n"+newNameUser+" 0");
        } else {
            player = register.listUsers.get(newNameUser);
            if (player.getGame_start() == '1') { player.delete_all(); }
            fw = new FileWriter(filename);
            boolean first= true;
            for ( Player itr : register.listUsers.values() ) {
                player = itr;
                if (!first) { stringBuilder.append("\n"); }
                stringBuilder.append(player.getUsername()+" "+player.getGame_start());
                if (player.getGame_start() == '1') {
                    stringBuilder.append(" "+player.getTime()+" ");
                    ArrayList<String> respuestas = player.getRespuestas();
                    stringBuilder.append(respuestas.size()/2+" ");
                    for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(player.getCodigo()+" "+player.getDificultat()+" "+player.getRol());
                }
                first = false;
            }
        }
        fw.write(stringBuilder.toString());//appends the string to the file
        fw.close();
    }

    public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas, double time, String codigo, String dificultat, String rol, Register register) throws IOException {
        File filename = new File("./persistence/registro.txt");
        Player player;
        FileWriter fw;
        StringBuilder stringBuilder = new StringBuilder();
        if (!register.user_exists(newNameUser)){
            player = new Player('1',newNameUser,respuestas,time,codigo,dificultat,rol);
            register.listUsers.put(newNameUser,player);
            fw = new FileWriter(filename,true); //the true will append the new data
            stringBuilder.append("\n"+newNameUser+" 1 ");
            String cadena = String.valueOf(time);
            stringBuilder.append(cadena+" ");
            String size = String.valueOf(respuestas.size()/2);
            stringBuilder.append(size+" ");
            for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
            stringBuilder.append(codigo+" "+dificultat+" "+rol);
        } else {
            player = register.listUsers.get(newNameUser);
            player.setSaveGame(respuestas,time,codigo,dificultat,rol);
            fw = new FileWriter(filename);
            boolean first = true;
            for ( Player itr : register.listUsers.values() ) {
                player = itr;
                if (!first) { stringBuilder.append("\n"); }
                stringBuilder.append(player.getUsername()+" "+player.getGame_start());
                if (player.getGame_start() == '1') {
                    stringBuilder.append(" "+ player.getTime()+" ");
                    ArrayList<String> respuestas1 = player.getRespuestas();
                    stringBuilder.append(respuestas1.size()/2+" ");
                    for ( String itr1 : respuestas ) { stringBuilder.append(itr1+" "); }
                    stringBuilder.append(player.getCodigo()+" "+player.getDificultat()+" "+player.getRol());
                }
                first = false;
            }
        }
        fw.write(stringBuilder.toString());//appends the string to the file
        fw.close();
    }
}
