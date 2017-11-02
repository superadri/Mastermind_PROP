
// package mastermind;

public class Player {
    char game_start;
    String username;

    public Player(char game_start, String username) {
        this.game_start = game_start;
        this.username = username;
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
