package domain;

import java.util.Scanner;

public class Play {

    private CtrlDominio controladorDominio;

    private Game game;
    private CodeMaker cm;
    public String role;

    public Play(CtrlDominio controladorDominio, Game game, String role) {
        this.controladorDominio = controladorDominio;
        this.cm = new CodeMaker(controladorDominio, game.width, game.repetition, game.nLetters);
        this.game = game;
        this.role = role;
    }

	public void makePlay() {
            // Defines quien hará de CodeMaker
		if ( role.equals("CODEMAKER") ) {
		  	String code = "";
		  	if (game.computerCM) { code = cm.createCode(); } // Machine
		  	else { code = scanCombination(); } // User - Input
		  	game.sendCode(this, code);
		} else if ( role.equals("CODEBREAKER") ) { // Defines quien hará de CodeBreaker
			if (game.computerCB) {
                if (controladorDominio.getWhoMachine().equals("MACHINEC")) { // Machine Complex
                    game.cb.playCombination();
                } else {    // Machine Randome
                    game.guess = cm.createCode();
                    game.answer = game.calculateAnswer(game.guess);
                }
            } else { // User - Input
				game.guess = scanCombination();
                game.answer = game.calculateAnswer(game.guess);
		    }
		}
	}

    private String scanCombination() {
        return controladorDominio.getGuess();
    }
}
