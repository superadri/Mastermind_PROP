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

	public int countLevelGuess;

        /** Constructor **/

    public CtrlPresentacion() {
		controladorDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaUser = new VistaUser(this);
        vistaPrincipal = new VistaPrincipal(this);
        vistaHelp = new VistaHelp(this);
        vistaDifficulty = new VistaRoleDifficulty(this);
        vistaAbout = new VistaAbout(this);
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

    public void sincronizacionVistaRoleDifficultyAPrincipal(String username, String role, String difficulty) {
        setRoleDificultyNewGame(username, role, difficulty);
        if ( role.equals("Machine vs Machine(Random)") || role.equals("Machine vs Machine(Complex)")) {
            vistaDifficulty.hacerInvisible();
            vistaEndGame = new VistaEndGame(this,1);
            vistaEndGame.time = 0;
            vistaEndGame.numRound = 0;
            vistaEndGame.hacerVisible();
        } else {
            vistaDifficulty.hacerInvisible();
            vistaPrincipal.activar();
        }
    }

    public void sincronizacionVistaQuestionToContinueAPrincipal(String username) {
        setRoleDificultyContinuegame(username);
        vistaToContinue.hacerInvisible();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaPrincipalAEndGameWin(float time, int numRounds) {
        vistaPrincipal.desactivar();
        vistaEndGame = new VistaEndGame(this,1);
        vistaEndGame.time = time;
        vistaEndGame.numRound = numRounds;
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameNotWin() {
        vistaPrincipal.desactivar();
        vistaEndGame = new VistaEndGame(this,2);
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGameSave(int found) {
        vistaPrincipal.desactivar();
        if (found == 1) { vistaEndGame = new VistaEndGame(this,1); } // You Win
        else if (found == 2) { vistaEndGame = new VistaEndGame(this,2); } // You Lost
        else { vistaEndGame = new VistaEndGame(this,3); } // Middle Game
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
        vistaUser.setNameLabel(nameUserNow);
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaEndGameAUser() {
        vistaEndGame.hacerInvisible();
        vistaUser.setNameLabel(nameUserNow);
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaEndGameARanking() {
        vistaPrincipal.desactivar();
        vistaRanking.hacerVisible();
    }

    public void sincronizacionVistaUserAQuestionToContinue(String username) {
        this.nameUserNow = username;
        vistaToContinue = new VistaQuestionToContinue(this, username);
        vistaUser.hacerInvisible();
        vistaToContinue.hacerVisible();
    }

    public void sincronizacionVistaUserARoleDifficulty(String username) {
        vistaDifficulty.nameName = username;
        vistaUser.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueARoleDifficulty() {
        vistaDifficulty.nameName = nameUserNow;
        vistaToContinue.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueAUser() {
        vistaToContinue.hacerInvisible();
        vistaUser.setNameLabel(nameUserNow);
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

	public void setRoleDificultyNewGame(String username, String role, String difficulty) {
        controladorDominio.setRoleDificultyNewGame(username, role, difficulty);
    }

    public void setRoleDificultyContinuegame(String username) {
        controladorDominio.setRoleDificultyContinueGame(username);
    }
}
