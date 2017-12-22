package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import persistence.CtrlPersistence;

public class CtrlDominio {

    private CtrlPersistence controladorPersistence;

    public GameFactory gameFactory;
    private Register register;
    private Ranking ranking;

    private String guess, answer;
    private double time;

	    /** Constructor **/

	public CtrlDominio() {
        this.controladorPersistence = CtrlPersistence.getInstance();
        this.register = new Register(this);
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

    public void setRoleDificultyNewGame(String username, String role, String difficulty) { gameFactory.newgame(username,role,difficulty); }

    /*
    public void setRoleDificultyNewGameMachine(String username, String role, String difficulty, int numGames) {
        gameFactory.newgameMachine(username,role,difficulty,numGames);
        getMastermindAttributes();
    }
    */

    public void setRoleDificultyContinueGame(String username) {
        gameFactory.continuegame(username);
    }

    public void passDataToRegister(ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers, String nameUserNow, String role, String difficulty) throws IOException {
        // TODO: Hay que coger el time, codeCM, para guardarlo (1.22,"AAAA") -> HardCoder
        ArrayList<String> respuestas = new ArrayList<String>();
        for (int i = 0; i < codeOutAnswers.size(); ++i) {
            respuestas.add(codeOutGuess.get(i));
            respuestas.add(codeOutAnswers.get(i));
        }
        gameFactory.set_continueGame(1.22,"AAAA", true, register, ranking, nameUserNow, role, difficulty, respuestas);
    }

    public HashMap<String, Player> getListUsers(){
        return controladorPersistence.getListUsers();
    }

    public void finished_game(String newNameUser, Register register) throws IOException {
        controladorPersistence.finished_Game(newNameUser, register);
    }
    public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas,double time,String codigo,String dificultat,String rol) throws IOException {
        controladorPersistence.set_continueGame(game_start, newNameUser, respuestas, time, codigo, dificultat, rol, register);
    }

    public void setCodeMake(String code, String role, String difficulty){
        // TODO: Hacer Check de "code", para devolver el Answer
    }

    public double getTime(){
        return this.time;
    }

    public void setTime(Double time){
        this.time = time;
    }

    public void setGuess(String guess) { this.guess = guess; }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getGuess() {
        return this.guess;
    }
}
