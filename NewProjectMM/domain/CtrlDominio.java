package domain;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import persistence.CtrlPersistence;
import domain.*;
import presentation.CtrlPresentacion;

public class CtrlDominio {

    private CtrlPersistence controladorPersistence;
    private Register register;
	    // TODO: Poner todas las grandes estructuras del proyecto aquí, Map, ArrayList...

	    /** Constructor **/

	public CtrlDominio() {
        register = new Register(this);
	    inicializarCtrlDominio();
	}

        /** Métodos públicos **/

    public void inicializarCtrlDominio() {
        controladorPersistence = CtrlPersistence.getInstance();
    }

	public List<String> getNameRankings() {
	    return controladorPersistence.getNameRankings();
	}

    public List<String> getDataRanking(String nameRank) {
        return controladorPersistence.getDataRanking(nameRank);
    }

    public boolean user_exists(String username){
        return register.user_exists(username);
    }
}
