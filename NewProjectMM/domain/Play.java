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
		  	else { code = scanCombination(); } // // User - Input
                // Inicialias the Game, con este nuevo CodeMaker
		  	game.sendCode(this, code);

		  	// Defines quien hará de CodeBreaker
		} else if ( role.equals("CODEBREAKER") ) {
		        // Aquí hay que definir que Machine debe ejecutarse
			if (game.computerCB) {  // Machine Complex
                if (controladorDominio.getWhoMachine().equals("MACHINEC")) {
                    game.cb.playCombination();
                } else {    // Machine Randome
                    game.guess = cm.createCode();
                    game.answer = game.calculateAnswer(game.guess);
                }
            } else {
				game.guess = scanCombination(); // User - Input
                game.answer = game.calculateAnswer(game.guess);
		    }
		}
	}

    private String scanCombination() {
        return controladorDominio.getGuess();
    }

}
