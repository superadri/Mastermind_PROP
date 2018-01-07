package domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import persistence.CtrlPersistence;

public class CtrlDominio {

    private CtrlPersistence controladorPersistence;

    public MastermindFactory mastermindFactory;
    private Register register;
    private Ranking ranking;

    private String guess, answer, codeMaker;
    private double time;
    private String[][] answerMatrix;
    private String[] rounds;
    private String difficulty, role;
    private String whoMachine;
    private int numRightGame;

    /** Constructor **/

	public CtrlDominio() {
        this.controladorPersistence = CtrlPersistence.getInstance();
        this.register = new Register(this);
        this.ranking = new Ranking(this);
        this.mastermindFactory = new MastermindFactory(this);
	}

        /** Métodos públicos **/

    public Register getRegister() {
        return this.register;
    }

    /*
    public Ranking passRanking() { return this.ranking; }
    */

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

    public void setRoleDificultyNewGame(String username, String role, String difficulty) { mastermindFactory.newgame(username,role,difficulty); }

    public void setRoleDificultyNewGameMachine(String username, String role, String difficulty, int numGames) {
        mastermindFactory.newgameMachine(username,role,difficulty,numGames);
    }

    public void setRoleDificultyContinueGame(String username) {
        mastermindFactory.continuegame(username);
    }

    public void passDataToRegister(ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers, String nameUserNow, String role, String difficulty, double time, String codeMaker) throws IOException {
	    mastermindFactory.set_continueGameRegister(time, codeMaker, register, nameUserNow, role, difficulty, codeOutGuess, codeOutAnswers);
    }

    public void passDataToRanking(String nameUserNow, String role, String difficulty, double time) throws IOException {
        mastermindFactory.set_continueGameRanking(time, register, ranking, nameUserNow, role, difficulty);
    }

    public HashMap<String, Player_User> getListUsers(){
        return controladorPersistence.getListUsers();
    }

    public void finished_game(String newNameUser, Register register) throws IOException {
        controladorPersistence.finished_Game(newNameUser, register);
    }

    public void finished_game_CM_MvM(String newNameUser, Register register) throws IOException {
        controladorPersistence.finished_Game_CM_MvM(newNameUser, register);
    }

    public void set_continueGame(char game_start, String newNameUser, ArrayList<String> respuestas, double time, String codigo, String dificultat, String rol) throws IOException {
        controladorPersistence.set_continueGame(game_start, newNameUser, respuestas, time, codigo, dificultat, rol, register);
    }

    public void setCodeMake(String code, String username, String role, String difficulty){
        this.codeMaker = code;
        this.whoMachine = "MACHINEC";
        mastermindFactory.newgame(username,role,difficulty);
    }

    public double getTime(){ return this.time; }

    public void setTime(Double time){ this.time = time; }

    public void setGuess(String guess) { this.guess = guess; }

    public void setAnswer(String answer) { this.answer = answer; }

    public String getAnswer() { return this.answer; }

    public String[][] getAnswerMatrix() { return this.answerMatrix; }

    public void setAnswerMatrix(String[][] answerMatrix) { this.answerMatrix = answerMatrix; }

    public String getGuess() { return this.guess; }

    public void setCodeMaker(String codeMaker) { this.codeMaker = codeMaker; }

    public String getCodeMaker() { return this.codeMaker; }

    public void stopTime() { mastermindFactory.mastermind.game.stopTime(); }

    public void setRounds(String[] rounds) { this.rounds = rounds; }

    public String[] getRounds() { return this.rounds; }

    public void passUpdateDataDifficultyAndRole(String difficulty, String role) {
        this.difficulty = difficulty;
        this.role = role;
    }

    public String getDifficulty() { return this.difficulty; }

    public String getRole() { return this.role; }

    public void setWhoMachine(String wMachine) { this.whoMachine = wMachine; }

    public String getWhoMachine() { return this.whoMachine; }

    public void setNumRightGame(int num) { this.numRightGame = num; }

    public int getNumRightGame() { return this.numRightGame; }

    public int getHeight() { return mastermindFactory.mastermind.height; }
}
