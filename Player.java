// package mastermind;

import java.util.ArrayList;

public class Player {
    char game_start;
    String username;
    //esto si game_start = 1;
    ArrayList<String> respuestas = new ArrayList<>();
    double time;
    String codigo;


    public Player(char game_start, String username) {
        this.game_start = game_start;
        this.username = username;
    }

     public Player(char game_start, String username, ArrayList<String> respuestas,double time,String codigo) {
        this.game_start = game_start;
        this.username = username;
        this.respuestas = respuestas;
        this.time = time;
        this.codigo = codigo;
    }

    public char getGame_start() {
        return game_start;
    }

    public String getUsername() {
        return username;
    }

	/*
	  // Test Method
	public static void main(String[] args) {
		Player player = new Player('y',"Juancho");
		System.out.println("Name: "+player.getUsername()+", Start: "+player.getGame_start());
	}
	*/

}
