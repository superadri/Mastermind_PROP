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

    private String guess, answer, codeMaker;
    private double time;
    private String[][] answerMatrix;
    private String[] rounds;
    private String difficulty, role;

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

    public void passDataToRegister(ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers, String nameUserNow, String role, String difficulty, double time, String codeMaker) throws IOException {
        ArrayList<String> respuestas = new ArrayList<String>();
        for (int i = 0; i < codeOutAnswers.size(); ++i) {
            respuestas.add(codeOutGuess.get(i));
            respuestas.add(codeOutAnswers.get(i));
        }
        gameFactory.set_continueGame(time,codeMaker, true, register, ranking, nameUserNow, role, difficulty, respuestas);
    }

    public void passDataToRanking(String nameUserNow, String role, String difficulty, double time, String codeMaker) throws IOException {
        gameFactory.set_continueGame(time, codeMaker, false, register, ranking, nameUserNow, role, difficulty,null);
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

    public String getAnswer() { return this.answer; }

    public String[][] getAnswerMatrix() { return this.answerMatrix; }

    public void setAnswerMatrix(String[][] answerMatrix) { this.answerMatrix = answerMatrix; }

    public String getGuess() {
        return this.guess;
    }

    public void setCodeMaker(String codeMaker) { this.codeMaker = codeMaker; }

    public String getCodeMaker() { return this.codeMaker; }

    public void stopTime() { gameFactory.mastermind.game.stopTime(); }

    public void setRounds(String[] rounds) { this.rounds = rounds; }

    public String[] getRounds() { return this.rounds; }

    public void passUpdateDataDifficultyAndRole(String difficulty, String role) {
        this.difficulty = difficulty;
        this.role = role;
    }

    public String getDifficulty() { return this.difficulty; }

    public String getRole() { return this.role; }
}
