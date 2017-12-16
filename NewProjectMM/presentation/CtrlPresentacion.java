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

	public int countLevelGuess;

	    //TODO: Hay que definir el role, cuando haces click X, para cerrar la ventana, en global

        /** Constructor **/

    public CtrlPresentacion() {
		controladorDominio = new CtrlDominio();
        vistaRanking = new VistaRanking(this);
        vistaUser = new VistaUser(this);
        vistaPrincipal = new VistaPrincipal(this);
        vistaEndGame = new VistaEndGame(this);
        vistaHelp = new VistaHelp(this);
        vistaAbout = new VistaAbout(this);
    }

	public void inicializarPresentacion() {
		controladorDominio.inicializarCtrlDominio();
        vistaUser.hacerVisible();
        vistaPrincipal.hacerVisible();
    }

	    /** Métodos de sincronización entre vistas **/

    public void sincronizacionVistaAboutAPrincipal() {
        vistaPrincipal.activar();
        vistaAbout.hacerInvisible();
    }

	public void sincronizacionVistaRankingAPrincipal() {
        vistaPrincipal.activar();
        vistaRanking.hacerInvisible();
    }

	public void sincronizacionVistaEndGameAPrincipal() {
        vistaPrincipal.activar();
        vistaEndGame.hacerInvisible();
    }

	public void sincronizacionVistaHelpAPrincipal() {
        vistaPrincipal.activar();
		vistaHelp.hacerInvisible();
    }

    public void sincronizacionVistaUserAPrincipal() {
        vistaPrincipal.activar();
        vistaUser.hacerInvisible();
    }

    public void sincronizacionVistaPrincipalAAbout() {
        vistaPrincipal.desactivar();
        vistaAbout.hacerVisible();
    }

	public void sincronizacionVistaPrincipalARanking() {
        vistaPrincipal.desactivar();
        vistaRanking.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAHelp() {
        vistaPrincipal.desactivar();
        vistaHelp.hacerVisible();
    }

    public void sincronizacionVistaPrincipalAEndGame() {
        vistaPrincipal.desactivar();
        vistaEndGame.hacerVisible();
    }

    public void sincronizacionVistaEndGameAUser() {
        vistaPrincipal.desactivar();
        vistaEndGame.hacerInvisible();
        vistaUser.hacerVisible();
    }

	    /** Llamadas al controlador de dominio **/

    public boolean user_exists(String user) { return controladorDominio.user_exists(user); }

	public List<String> getDataRanking(String nameRank) {
		return controladorDominio.getDataRanking(nameRank);
	}

	public List<String> getNameRankings() {
		return controladorDominio.getNameRankings();
	}
}
