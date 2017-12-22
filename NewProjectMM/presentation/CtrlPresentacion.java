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
	private String difficulty;
	private boolean control;

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
            // TODO: Machine
        // numGames -> número de partidas que se quieren que jueguen
        this.role = role;
        this.difficulty = difficulty;
        setRoleDificultyNewGameMachine(numGames);
        vistaDifficulty.hacerInvisible();
        vistaEndGame.setTextJlableResult(1);
        vistaEndGame.time = 0;
        vistaEndGame.numRound = 0;
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaRoleDifficultyAPrincipal(String role, String difficulty) {
        this.role = role;
        this.difficulty = difficulty;
        this.control = false;
        vistaDifficulty.hacerInvisible();
        vistaPrincipal.inicializarBoardReset();
        vistaPrincipal.activar();
        setRoleDificultyNewGame();
    }

    public void sincronizacionVistaRoleDifficultyACM(String role, String difficulty) {
        this.role = role;
        this.difficulty = difficulty;
        vistaDifficulty.hacerInvisible();
        vistaCM.hacerVisible();
    }

    public void sincronizacionVistaCMARoleDifficulty() {
        vistaCM.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaCMAPrincipal(String codeCM) {
        // TODO: Para poner el resultado
        // this.difficulty;
        // this.role;
        // codeCM -> CM, que has definido tú para la máquina lo adivine
        controladorDominio.setCodeMake(codeCM, this.role, this.difficulty);
        vistaCM.hacerInvisible();
        vistaPrincipal.inicializarBoardReset();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaQuestionToContinueAPrincipal() {
        // TODO: Tienes que cargarlo y ponerlo aquí, para poder restablecer la partida, y luego si es necesario guardar
            // Ahora está HardCoder, hay que coger el de la partida guardada
        this.difficulty = "EASY";
        this.role = "CB";
        setRoleDificultyContinuegame();
        vistaToContinue.hacerInvisible();
        vistaPrincipal.inicializarBoardContinue();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaPrincipalAEndGameWin() {
        // TODO: Hay que comprobar, que estás registarado, y ponerlo 0, si lo estás y sino lo creas (por la partidas pendientes)
        // TODO: Ranking, hacer una update, en función de si, existes, y luego si has mejorado, sino se deja el mejor time
        // TODO: A la hora de guardar hay que hacerlo ordenado
            // this.difficulty;
            // this.role;
            // this.nameUserNow;
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(1);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameNotWin() {
        // TODO: Hay que comprobar, que estás registarado, y ponerlo 0, si lo estás y sino lo creas (por la partidas pendientes)
        // TODO: Ranking, hacer una update, en función de si, existes, y luego si has mejorado, sino se deja el mejor time
        // TODO: A la hora de guardar hay que hacerlo ordenado
            // this.difficulty;
            // this.role;
            // this.nameUserNow;
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(2);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameSave(ArrayList<String> codeOutGuess, ArrayList<String> codeOutAnswers) throws IOException {
        controladorDominio.passDataToRegister(codeOutGuess,codeOutAnswers,nameUserNow,role,difficulty);
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(3);
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

	public List<String> getDataRanking(String nameRank) {
		return controladorDominio.getDataRanking(nameRank);
	}

	public List<String> getNameFileRankings() {
		return controladorDominio.getNameFileRankings();
	}

	    // Si existe
    public boolean user_exists(String user) {
        return controladorDominio.user_exists(user);
    }

        // Si tiene partida pendiente
    public boolean game_start_user(String username) {
        return controladorDominio.game_start_user(username);
    }

    public void setRoleDificultyNewGameMachine(int numGames) {
        // TODO : Hacer la nueva partida
        // controladorDominio.setRoleDificultyNewGameMachine(nameUserNow, role, difficulty, numGames);
    }

	public void setRoleDificultyNewGame() {
	    // TODO : Hacer la nueva partida
        controladorDominio.setRoleDificultyNewGame(nameUserNow, role, difficulty);
    }

    public void setRoleDificultyContinuegame() {
        // TODO : Continuar la partida
        controladorDominio.setRoleDificultyContinueGame(nameUserNow);
    }

    public void setGuesstoDominio(String code, boolean start) {
        controladorDominio.setGuess(code);
        if (!control) { controladorDominio.gameFactory.mastermind.game.startNewGame(); }
        else { controladorDominio.gameFactory.mastermind.game.runGame(); }
        control = start;
    }

    public String getAnswer(){
	    return controladorDominio.getAnswer();
    }

    public Double getTime() {
	    return controladorDominio.getTime();
    }
}
