package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.CtrlDominio;

public class CtrlPresentacion {

	private CtrlDominio controladorDominio;

	private VistaPrincipal vistaPrincipal;
	private VistaRanking vistaRanking;
	private VistaUser vistaUser;
	private VistaEndGame vistaEndGame;
	private VistaHelp vistaHelp;
	private VistaAbout vistaAbout;
	private VistaRoleDifficulty vistaDifficulty;
	private VistaQuestionToContinue vistaToContinue;
	private VistaCM vistaCM;

	private String nameUserNow;
	private String role;
    private boolean codeMakerRight;
    public String difficulty;

    /** Constructor **/

    public CtrlPresentacion() {
		controladorDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaHelp = new VistaHelp(this);
        vistaAbout = new VistaAbout(this);
        vistaUser = new VistaUser(this);
        vistaDifficulty = new VistaRoleDifficulty(this);
        vistaPrincipal = new VistaPrincipal(this);
        vistaToContinue = new VistaQuestionToContinue(this);
        vistaEndGame = new VistaEndGame(this);
        vistaCM = new VistaCM(this);
    }

	public void inicializarPresentacion() {
        vistaUser.hacerVisible();
        vistaPrincipal.hacerVisible();
        vistaPrincipal.desactivar();
    }

	    /** Métodos de sincronización entre vistas **/

    public void sincronizacionVistaAboutAPrincipal() {
        vistaAbout.hacerInvisible();
        vistaPrincipal.activar();
    }

	public void sincronizacionVistaRankingAPrincipal() {
        vistaRanking.hacerInvisible();
        vistaPrincipal.activar();
    }

	public void sincronizacionVistaHelpAPrincipal() {
        vistaHelp.hacerInvisible();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaRoleDifficultyAEndGame(String role, String difficulty, Integer numGames) {
            // TODO: Machine -> Hay que hacer Refactor de la matriz,
            //  para que no la haga todo el tiempo, pero la implementación es la misma
        this.role = role;
        this.difficulty = difficulty;
        controladorDominio.setNumRightGame(0);
        setRoleDificultyNewGameMachine(numGames);
        int numGameRight = controladorDominio.getNumRightGame();
        vistaDifficulty.hacerInvisible();
        vistaEndGame.setTextJlableResult(3, numGameRight, numGames);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaRoleDifficultyAPrincipal(String role, String difficulty) {
        this.role = role;
        this.difficulty = difficulty;
        this.codeMakerRight = false;
        vistaDifficulty.hacerInvisible();
        vistaPrincipal.inicializarBoardReset();
        vistaPrincipal.activar();
        setRoleDificultyNewGame();
    }

    public void sincronizacionVistaRoleDifficultyACM(String role, String difficulty) {
        this.role = role;
        this.difficulty = difficulty;
        vistaDifficulty.hacerInvisible();
        vistaCM.iniPegCM();
        vistaCM.hacerVisible();
    }

    public void sincronizacionVistaCMARoleDifficulty() {
        vistaCM.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaCMAPrincipal(String codeCM) {
        controladorDominio.setGuess(codeCM);
        controladorDominio.setCodeMake(codeCM, this.nameUserNow, this.role, this.difficulty);
        vistaCM.hacerInvisible();
        this.codeMakerRight = true;
        vistaPrincipal.inicializarBoardContinue();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaQuestionToContinueAPrincipal() {
        setRoleDificultyContinuegame();
        this.difficulty = controladorDominio.getDifficulty();
        this.role = controladorDominio.getRole();
        this.codeMakerRight = false;
        vistaToContinue.hacerInvisible();
        vistaPrincipal.inicializarBoardContinue();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaPrincipalAEndGame(int found, double time, int numRound) {
        try { controladorDominio.passDataToRanking(nameUserNow, role, difficulty, time); }
        catch (IOException e) { e.printStackTrace(); }
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(found, time, numRound);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameSave(ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers, double time, int numRounds, String codeMaker) throws IOException {
        controladorDominio.passDataToRegister(codeOutGuess, codeOutAnswers, nameUserNow, role, difficulty, time, codeMaker);
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(4,time,numRounds);
        vistaEndGame.hacerVisible();
    }

	public void sincronizacionVistaPrincipalARanking() {
        vistaPrincipal.desactivar();
        vistaRanking.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAHelp() {
        vistaPrincipal.desactivar();
        vistaHelp.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAAbout() {
        vistaPrincipal.desactivar();
        vistaAbout.hacerVisible();
    }

    public void sincronizacionVistaRoleDifficultyAUser() {
        vistaDifficulty.hacerInvisible();
        vistaUser.setNameTextField(nameUserNow);
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAUser() {
        vistaPrincipal.desactivar();
        vistaUser.setNameTextField(nameUserNow);
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaEndGameAUser() {
        vistaEndGame.hacerInvisible();
        vistaUser.setNameTextField(nameUserNow);
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaEndGameARanking() {
        vistaPrincipal.desactivar();
        vistaRanking.hacerVisible();
    }

    public void sincronizacionVistaUserAQuestionToContinue(String username) {
        this.nameUserNow = username;
        vistaUser.hacerInvisible();
        vistaToContinue.setNameLabel(username);
        vistaToContinue.hacerVisible();
    }

    public void sincronizacionVistaUserARoleDifficulty(String username) {
        this.nameUserNow = username;
        vistaUser.hacerInvisible();
        vistaDifficulty.setNameUserJlabel(username, user_exists(nameUserNow));
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueARoleDifficulty() {
        vistaToContinue.hacerInvisible();
        vistaDifficulty.setNameUserJlabel(nameUserNow, user_exists(nameUserNow));
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueAUser() {
        vistaToContinue.hacerInvisible();
        vistaUser.setNameTextField(nameUserNow);
        vistaUser.hacerVisible();
    }

	    /** Llamadas al controlador de dominio **/

	public List<String> getDataRanking(String nameRank) { return controladorDominio.getDataRanking(nameRank); }

	public List<String> getNameFileRankings() { return controladorDominio.getNameFileRankings(); }

	    // Si existe
    public boolean user_exists(String user) { return controladorDominio.user_exists(user); }

        // Si tiene partida pendiente
    public boolean game_start_user(String username) { return controladorDominio.game_start_user(username); }

    public void setRoleDificultyNewGameMachine(int numGames) { controladorDominio.setRoleDificultyNewGameMachine(nameUserNow, role, difficulty, numGames); }

	public void setRoleDificultyNewGame() { controladorDominio.setRoleDificultyNewGame(nameUserNow, role, difficulty); }

    public void setRoleDificultyContinuegame() { controladorDominio.setRoleDificultyContinueGame(nameUserNow); }

    public void setGuesstoDominio(String code) {
        controladorDominio.setGuess(code);
        controladorDominio.gameFactory.mastermind.game.runGame();
    }

    public String getAnswer(){ return controladorDominio.getAnswer(); }

    public void stopTime() { controladorDominio.stopTime(); }

    public double getTime() { return controladorDominio.getTime(); }

    public String getcodeMaker() { return  controladorDominio.getCodeMaker(); }

    public String[] getRounds() { return controladorDominio.getRounds(); }

    public boolean isCodeMakerRight() { return this.codeMakerRight; }

    public int getHeight() { return controladorDominio.getHeight(); }
}
