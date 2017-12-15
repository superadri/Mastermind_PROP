package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javafx.util.*;

public class Ranking {

	private File f;
	private String route;
	private Map<Double, String> m;

		// Attribute
	private ArrayList<String> nomUsers = new ArrayList<String>();

		// Constructor
	public Ranking() {
		this.f = null;
		this.m = new HashMap<Double, String>();
	}

	// Ordena primero por dificultad (implicito, un archivo para cada), despues
	// por turnos, despues por tiempo (que es mas dificil que haya empate)
	public void updateRanking(String difficulty, int turns, Double time, String username, String role) {
		m.clear();
		openFile(difficulty,role);
		// A partir de aqui ya hay un fichero abierto en f
		// y nos podemos olvidar de "difficulty
		m.put(time, username);

		saveFile();
	}

	public void showRanking(String difficulty, String role) {
		System.out.println("\nRANKING: " );
		m.clear();
		openFile(difficulty,role);
		// A partir de aqui ya hay un fichero abierto en f
		// y nos podemos olvidar de "difficulty"
		MyComparator comparator = new MyComparator(m);
		Map<Double, String> sortedMap = new TreeMap<Double, String>(comparator);
		sortedMap.putAll(m);
		Iterator it = sortedMap.entrySet().iterator();
		int count = 1;
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			//System.out.println(e.getKey() + " " + (e.getValue()).getKey() + " " + (e.getValue()).getValue());
			System.out.println(count + " - "+ e.getKey() + " " + e.getValue());
			++count;
		}
		System.out.println();
	}

	private void openFile(String difficulty,String role) {
			try {
				this.route = "persistency/ranking"+difficulty+role+".txt";
				this.f = new File(route);
				Scanner s = new Scanner(f);
				s.useLocale(Locale.ENGLISH);
				while( s.hasNext() ) {
				    //String turn = s.next(); // por implementar
						Double time = Double.parseDouble(s.next());
						String username = s.next();
						addToMap(/*turn,*/ time, username);
				}
	    } catch (FileNotFoundException ex) {
				ex.printStackTrace();
	    }
	}

		// Campos: turnos tiempo username
	private void addToMap(/*String turn,*/ Double time, String username) {
		/*
		Pair<String, String> p = new Pair<String, String>(time, username);
		m.put(turn, p);
		*/
		m.put(time, username);
	}

	private void saveFile() {
		try {
			FileWriter fw = new FileWriter(this.f); //the true will append the new data
			MyComparator comparator = new MyComparator(m);
    		Map<Double, String> sortedMap = new TreeMap<Double, String>(comparator);
    		sortedMap.putAll(m);
			Iterator it = sortedMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry)it.next();
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append(e.getKey());
				stringBuilder.append(" ");
				stringBuilder.append(e.getValue());
				stringBuilder.append("\n");
				String finalString = stringBuilder.toString();
				fw.write(finalString);//appends the string to the file
			}
			// m = sortedMap;
			fw.close();
		} catch(Exception e) {
				e.printStackTrace();
		}
	}

		// Test Method
		/*
	public static void main(String[] args) {
		Ranking rank = new Ranking();
		rank.showRanking("EASY");
		rank.updateRanking("EASY", 3, "5.2468", "Paco");
		rank.saveFile();
	}
	*/
}

class MyComparator implements Comparator<Double> {
    Map<Double, String> map;

    public MyComparator(Map<Double, String> map) {
        this.map = map;
    }

    public int compare(Double a, Double b) {
		int compD = Double.compare(a, b);
		if (compD > 0) { compD = 1; }
		else if (compD < 0) { compD = -1; }
		else if (compD == 0) { compD = -1; }
		return compD;
    }
}
