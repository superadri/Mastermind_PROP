package domain;

import java.util.Scanner;

public class Play {

  Game game;
  String role;

  public Play(Game game, String role) {
    this.game = game;
    this.role = role;
  }

	public void makePlay() {
		if ( role.equals("CODEMAKER") ) {
		  	String code = "";
				if (game.computerCM) {
					// code = cm.createCode("EASY");
					// code = cm.createCode("HARDCODER");
		    		code = "FEDC";
				} else { code = scanCombination(); }
		  	game.sendCode(this, code);
		} else if ( role.equals("CODEBREAKER") ) {
			if (game.computerCB) { game.cb.playCombination(); }
			else {
				game.guess = scanCombination();
				game.answer = game.calculateAnswer(game.guess);
		    }
		}
	}

  private String scanCombination() {
    System.out.print("Enter your combination[----]: ");
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

}
