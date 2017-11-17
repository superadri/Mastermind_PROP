
// package mastermind;

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
	//private Map< String, Pair<String, String> > m;
	private Map< String, String > m;

		// Attribute
	private ArrayList<String> nomUsers = new ArrayList<String>();

		// Constructor
	public Ranking() {
		this.f = null;
		//this.m = new HashMap< String, Pair<String, String> >();
		this.m = new HashMap< String, String >();
	}

	// Ordena primero por dificultad (implicito, un archivo para cada), despues
	// por turnos, despues por tiempo (que es mas dificil que haya empate)
	public void updateRanking(String difficulty, int turns, String time, String username) {
		openFile(difficulty);
		// A partir de aqui ya hay un fichero abierto en f
		// y nos podemos olvidar de "difficulty
		m.put(time, username);
		saveFile();
	}

	public void showRanking(String difficulty) {
		System.out.println("\nRANKING: " );
		openFile(difficulty);
		// A partir de aqui ya hay un fichero abierto en f
		// y nos podemos olvidar de "difficulty"
		Iterator it = m.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			//System.out.println(e.getKey() + " " + (e.getValue()).getKey() + " " + (e.getValue()).getValue());
			System.out.println(e.getKey() + " " + e.getValue());
		}
		System.out.println();
	}

	private void openFile(String difficulty) {
			try {
				this.route = "persistency/ranking"+difficulty+".txt";
				this.f = new File(route);
				Scanner s = new Scanner(f);
				s.useLocale(Locale.ENGLISH);
				while( s.hasNext() ) {
				    //String turn = s.next(); // por implementar
						String time = s.next();
						String username = s.next();
						addToMap(/*turn,*/ time, username);
				}
	    } catch (FileNotFoundException ex) {
				ex.printStackTrace();
	    }
	}

		// Campos: turnos tiempo username
	private void addToMap(/*String turn,*/ String time, String username) {
		/*
		Pair<String, String> p = new Pair<String, String>(time, username);
		m.put(turn, p);
		*/
		m.put(time, username);
	}

	private void saveFile() {
		try {
			FileWriter fw = new FileWriter(this.f); //the true will append the new data
			Iterator it = m.entrySet().iterator();
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
