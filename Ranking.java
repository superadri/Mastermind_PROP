
// package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ranking {

		// Attribute
	private ArrayList<String> nomUsers = new ArrayList<String>();
	private ArrayList<Integer> scoreUsers = new ArrayList<Integer>();

		// Constructor
	public Ranking() {
		try {
			// File f = new File("/usr/share/mastermind/registro.txt");
			File f = new File("/Users/sirdrope/Git_Project/mastermind/registro.txt");
			Scanner s = new Scanner(f);
			while( s.hasNextLine() ) {
			    String line = s.nextLine();
			    add_to_array(line);
			}
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

		// Getter && Setter
	public ArrayList<String> getListUsers() {
		return nomUsers;
	}

		// Method
	public void printListUsers() {
		for(int i = 0; i < nomUsers.size(); ++i){
			System.out.println("name: "+nomUsers.get(i)+", score: "+scoreUsers.get(i));
		}
	}

	private void add_to_array(String line) {
		int i = 0;
		StringBuilder username = new StringBuilder();
		StringBuilder userscore = new StringBuilder();
		while(line.charAt(i) != ',' ){
			char a = line.charAt(i);
			username.append(a);
			++i;
		}
		++i;
		while( i < line.length() ){
			char a = line.charAt(i);
			userscore.append(a);
			++i;
		}
		String usern = username.toString();
		String usersc = userscore.toString();
		nomUsers.add(usern);
		scoreUsers.add(Integer.parseInt(usersc));
	}

		// Test Method
	public static void main(String[] args) {
		Ranking rank = new Ranking();
		rank.printListUsers();
	}
}
