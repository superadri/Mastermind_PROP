package domain;

import java.util.Scanner;

public class Play {

    private CtrlDominio controladorDominio;

    private Game game;
    private CodeMaker cm;
    public String role;

    public Play(CtrlDominio controladorDominio, Game game, String role) {
        this.controladorDominio = controladorDominio;
        this.cm = new CodeMaker(controladorDominio);
        this.game = game;
        this.role = role;
    }

	public void makePlay() {
		if ( role.equals("CODEMAKER") ) {
		  	String code = "";
		  	if (game.computerCM) { code = cm.createCode(); }
		  	else { code = scanCombination(); }
		  	game.sendCode(this, code);
		} else if ( role.equals("CODEBREAKER") ) {
			if (game.computerCB) { game.cb.playCombination(); }
			else {
				game.guess = scanCombination(); // Input
                game.answer = game.calculateAnswer(game.guess);
		    }
		}
	}

    private String scanCombination() {
        return controladorDominio.getGuess();
    }

}
