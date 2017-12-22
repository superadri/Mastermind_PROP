package domain;

import java.io.*;
import java.util.*;

public class Ranking {

	private CtrlDominio controladorDominio;

	private File file;
	private String route;
	private Map<Double, String> mapDataRanking;

		// Attribute
	private ArrayList<String> nomUsers = new ArrayList<String>();

		// Constructor
	public Ranking(CtrlDominio controladorDominio) {
		this.controladorDominio = controladorDominio;
		this.file = null;
		this.mapDataRanking = new HashMap<Double, String>();
	}

	public void updateRanking(String difficulty, Double time, String username, String role) {
        mapDataRanking.clear();
		openFile(difficulty,role);
        mapDataRanking.put(time, username);
		saveFile();
	}

	public void showRanking(String difficulty, String role) {
		System.out.println("showRanking" );
        mapDataRanking.clear();
		openFile(difficulty,role);
		MyComparator comparator = new MyComparator(mapDataRanking);
		Map<Double, String> sortedMap = new TreeMap<Double, String>(comparator);
		sortedMap.putAll(mapDataRanking);
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
			this.route = "./persistence/ranking"+difficulty+role+".txt";
			this.file = new File(route);
			Scanner s = new Scanner(file);
			s.useLocale(Locale.ENGLISH);
			while( s.hasNext() ) {
				Double time = Double.parseDouble(s.next());
				String username = s.next();
				addToMap(time, username);
			}
	    } catch (FileNotFoundException ex) { ex.printStackTrace(); }
	}

	private void addToMap(Double time, String username) {
        mapDataRanking.put(time, username);
	}

	private void saveFile() {
		try {
			FileWriter fw = new FileWriter(this.file); //the true will append the new data
			MyComparator comparator = new MyComparator(mapDataRanking);
    		Map<Double, String> sortedMap = new TreeMap<Double, String>(comparator);
    		sortedMap.putAll(mapDataRanking);
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
			fw.close();
		} catch(Exception e) { e.printStackTrace(); }
	}

	/*
		// Test Method
		public static void main(String[] args) {
            CtrlDominio controladorDominio = null;
			Ranking rank = new Ranking(controladorDominio);
			rank.showRanking("EASY","CM");
			rank.updateRanking("EASY", 3, 7.7, "Paco", "CM");
            rank.showRanking("EASY","CM");
        }
    */
}

class MyComparator implements Comparator<Double> {
    Map<Double, String> map;

    public MyComparator(Map<Double, String> map) {
        this.map = map;
    }

    public int compare(Double a, Double b) {
		return Double.compare(a, b);
    }
}
