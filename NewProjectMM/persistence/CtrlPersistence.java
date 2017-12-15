package persistence;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CtrlPersistence {
	
	private static CtrlPersistence singletonObject;

	public static CtrlPersistence getInstance() {
		if (singletonObject == null) { singletonObject = new CtrlPersistence(){}; }
		return singletonObject;
	}

	    /** Constructora privada **/
	
	private CtrlPersistence() {
	}

	public List<String> getNameRankings() {
        LinkedList<String> dataNR = new LinkedList<String>();

        try {
            File[] files = new File("../persistence").listFiles();
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
			FileReader fr = new FileReader("../persistence/"+nameRank);
			Scanner scan = new Scanner(fr);
			while (scan.hasNextLine()) { dataR.add(new String(scan.nextLine())); }
		} catch (FileNotFoundException ex){ System.out.println("Error3: " + ex); }

		return dataR;
	}
}
