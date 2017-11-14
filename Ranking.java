
// package mastermind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ranking {

		// Attribute
	private ArrayList<String> nomUsers = new ArrayList<String>();

		// Constructor
	public Ranking() {
		try {
			File f = new File("persistency/registro.txt");
			Scanner s = new Scanner(f);
			while( s.hasNextLine() ) {
			    String line = s.nextLine();
			    add_to_array(line);
			}
        } catch (FileNotFoundException ex) {
			ex.printStackTrace();
        }
	}

		// Getter && Setter
	public ArrayList<String> getListUsers() {
		return nomUsers;
	}

		// Method
	public void printListUsers() {
		for(int i = 0; i < nomUsers.size(); ++i){
			System.out.println("Input("+i+"):"+nomUsers.get(i));
		}
	}

	private void add_to_array(String line) {
		int i = 0;
		// Hay que acabar de definir como serán los campos de registro.txt
		// Para adaptarlo a él
		/*
		StringBuilder input = new StringBuilder();
		while ( i < line.length() ) {
			while( line.charAt(i) != ' ' ){
				char a = line.charAt(i);
				input.append(a);
				++i;
			}
			input.append(" ");
		}
		String usern = input.toString();
		nomUsers.add(usern);
		*/
	}

		// Test Method
	public static void main(String[] args) {
		Ranking rank = new Ranking();
		rank.printListUsers();
	}
}
