package persistence;

import domain.Ranking;
import domain.Register;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CtrlPersistence {
	
	private static CtrlPersistence singletonObject;
	private Register register;
    private Ranking rank;

    public static CtrlPersistence getInstance() {
		if (singletonObject == null) { singletonObject = new CtrlPersistence(){}; }
		return singletonObject;
	}

	    /** Constructora privada **/
	
	private CtrlPersistence() {

	}

    public void inicializarCtrlPersistencia() {
        this.register = new Register(this);
        this.rank = new Ranking(this);
    }

	public List<String> getNameRankings() {
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

        return dataNR;
	}

	public List<String> getDataRanking(String nameRank) {
		LinkedList<String> dataR = new LinkedList<String>();

		try {
		    FileReader fr = new FileReader("./persistence/"+nameRank);
			Scanner scan = new Scanner(fr);
			while (scan.hasNextLine()) { dataR.add(new String(scan.nextLine())); }
            scan.close();
            fr.close();
		} catch (IOException ex){ System.out.println("Error3: " + ex); }

		return dataR;
	}
    public boolean user_exists(String username) {
	    return register.user_exists(username);
    }

    public boolean game_start_user(String username) {
	    return register.game_start_user(username);
    }
}
