package presentation;

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

	private String nameUserNow;

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

    public void sincronizacionVistaRoleDifficultyAEndGame(String username, String role, String difficulty) {
        // TODO: Machine
        setRoleDificultyNewGame(role, difficulty);
        vistaDifficulty.hacerInvisible();
        vistaEndGame.setTextJlableResult(1);
        vistaEndGame.time = 0;
        vistaEndGame.numRound = 0;
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaRoleDifficultyAPrincipal(String username, String role, String difficulty) {
        setRoleDificultyNewGame(role, difficulty);
        vistaDifficulty.hacerInvisible();
        vistaPrincipal.inicializarBoardReset();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaQuestionToContinueAPrincipal() {
        setRoleDificultyContinuegame(nameUserNow);
        vistaToContinue.hacerInvisible();
        vistaPrincipal.inicializarBoardContinue();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaPrincipalAEndGameWin() {
        // TODO: Save Data
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(1);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameNotWin() {
        vistaPrincipal.desactivar();
        vistaEndGame.setTextJlableResult(2);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameSave() {
        // TODO: Save Data
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

    public boolean user_exists(String user) {
        return controladorDominio.user_exists(user);
    }

    public boolean game_start_user(String username) {
        return controladorDominio.game_start_user(username);
    }

	public void setRoleDificultyNewGame(String role, String difficulty) {
	    // TODO : Hacer la nueva partida
        controladorDominio.setRoleDificultyNewGame(nameUserNow, role, difficulty);
    }

    public void setRoleDificultyContinuegame(String username) {
        // TODO : Continuar la partida
        // controladorDominio.setRoleDificultyContinueGame(username);
    }

    public void sendGuessToDomain(String guess) {
        //controladorDominio.
    }
}
