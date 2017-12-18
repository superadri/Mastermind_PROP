package presentation;

import java.io.FileNotFoundException;
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

	public int countLevelGuess;

        /** Constructor **/

    public CtrlPresentacion() {
		controladorDominio = new CtrlDominio(this);
        vistaRanking = new VistaRanking(this);
        vistaUser = new VistaUser(this);
        vistaPrincipal = new VistaPrincipal(this);
        vistaEndGame = new VistaEndGame(this);
        vistaHelp = new VistaHelp(this);
        vistaDifficulty = new VistaRoleDifficulty(this);
        vistaAbout = new VistaAbout(this);
    }

	public void inicializarPresentacion() {
		controladorDominio.inicializarCtrlDominio();
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

	public void sincronizacionVistaEndGameAPrincipal() {
        vistaEndGame.hacerInvisible();
        vistaPrincipal.activar();
    }

	public void sincronizacionVistaHelpAPrincipal() {
        vistaHelp.hacerInvisible();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaRoleDifficultyAPrincipal(String role, String difficulty) {
        vistaDifficulty.hacerInvisible();
        vistaPrincipal.activar();
        setRoleDificulty(role, difficulty);
    }

    public void sincronizacionVistaQuestionToContinueAPrincipal() {
        //TODO: usuario quiere continuar su partida
        vistaToContinue.hacerInvisible();
        vistaPrincipal.activar();
    }

    public void sincronizacionVistaPrincipalAEndGame() {
        vistaPrincipal.desactivar();
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
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaEndGameAUser() {
        vistaEndGame.hacerInvisible();
        vistaUser.hacerVisible();
    }

    public void sincronizacionVistaUserAQuestionToContinue(String username) {
        vistaUser.hacerInvisible();
        vistaToContinue = new VistaQuestionToContinue(this,username);
        vistaToContinue.hacerVisible();
    }

    public void sincronizacionVistaUserARoleDifficulty(String username) {
            // TODO: Save username
        vistaUser.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueARoleDifficulty(String username) {
        vistaToContinue.hacerInvisible();
        vistaDifficulty.hacerVisible();
    }

    public void sincronizacionVistaQuestionToContinueAUser() {
        vistaToContinue.hacerInvisible();
        vistaUser.hacerVisible();
    }

	    /** Llamadas al controlador de dominio **/

	public List<String> getDataRanking(String nameRank) {
		return controladorDominio.getDataRanking(nameRank);
	}

	public List<String> getNameRankings() {
		return controladorDominio.getNameRankings();
	}

    public boolean user_exists(String user) {
        return controladorDominio.user_exists(user);
    }

    public boolean game_start_user(String username) {
        return controladorDominio.game_start_user(username);
    }

	public void setRoleDificulty(String role, String difficulty) {
        //TODO: pasar a dominio el role y difficulty
    }
}
