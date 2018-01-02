package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import persistence.CtrlPersistence;
import presentation.CtrlPresentacion;

public class CtrlDominio {

    private CtrlPersistence controladorPersistence;

    private GameFactory gameFactory;
    private Register register;
    private Ranking ranking;

	    /** Constructor **/

	public CtrlDominio() {
        this.controladorPersistence = CtrlPersistence.getInstance();
        this.register = new Register(this,controladorPersistence);
        this.ranking = new Ranking(this);
        this.gameFactory = new GameFactory(this);
	}

        /** Métodos públicos **/

    public Register passRegister() {
        return this.register;
    }

    public Ranking passRanking() {
        return this.ranking;
    }

	public List<String> getNameFileRankings() {
	    return controladorPersistence.getNameFileRankings();
	}

    public List<String> getDataRanking(String nameRank) {
        return controladorPersistence.getDataRanking(nameRank);
    }

    public boolean user_exists(String username){
        return register.user_exists(username);
    }

    public boolean game_start_user(String username) {
        return register.game_start_user(username);
    }

    public void setRoleDificultyNewGame(String username, String role, String difficulty) {
        gameFactory.newgame(username,role,difficulty);
    }

    public void setRoleDificultyContinueGame(String username) {
        gameFactory.continuegame(username);
    }

    public String setCode(String code){
        return code;
    }

    public String getGuess(String guesses) {
        return guesses;
    }

    public String getAnswer(String answers) {
        return answers;
    }

    public String setGuess(String guess) {
        return guess;
    }

    public String setAnswer(String answer) {
        return answer;
    }

    public void finishGame(Double strTime,String strCode,boolean save, String username, String role, String difficulty, ArrayList<String> respuestas) throws IOException {
        gameFactory.set_continueGame(strTime,strCode,save,register,ranking,username,role,difficulty,respuestas);
    }

}
