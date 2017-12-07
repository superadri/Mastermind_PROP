
import java.io.IOException;
import java.util.*;

////////////////////////

public class CtrlPresentacio {

  private GameFactory gf;
  private static VistaAdd vistaAdd = null;    // (= null) innecesari
  //private VistaSecundaria vistaSecundaria = null;  // (= null) innecesario
  private QuestioToContinue qtc;
  private  NewGame newGame;
  private  ViewInstruccions vI;

//////////////////////// Constructor y metodos de inicializacion


  public CtrlPresentacio() throws IOException {
    this.gf = new GameFactory(this);
  }


public void setVisibleAddUser() {

    if (vistaAdd == null)  vistaAdd = new VistaAdd(this);
	vistaAdd.setVisible(true);
}


public void setUser(String user) throws IOException {
	// TODO Auto-generated method stub
	gf.setUser(user);
	
}

public void setNotVisibleAddUser() {
	vistaAdd.setVisible(false);
}

public void setQuestionToContinue() {
	 qtc = new QuestioToContinue(this);
	 qtc.setVisible(true);
}

public void setNoQuestionToContinue() {
	 qtc.setVisible(false);
}


public void newGame() {
	gf.newGame();
}

public void continueGame() throws IOException {
	// TODO Auto-generated method stub
	gf.continueGame();
}


public void setQuestionNewGame() {
	 newGame = new NewGame(this);
	 newGame.setVisible(true);
}

public void setNoQuestionNewGame() {
	 newGame.setVisible(false);
}

public void questioNewGame(String rol, String difficulty) throws IOException {
	// TODO Auto-generated method stub
	if (rol.equals("CodeMaker")) rol = "CM";
	else if (rol.equals("Codebreaker")) rol = "CB";
	else if (rol.equals("MachinevsMachine")) rol = "MACHINE";
	
	if (difficulty.equals("Hard")) difficulty = "HARD";
	else if (difficulty.equals("Medium")) difficulty = "MEDIUM";
	else if (difficulty.equals("Easy")) difficulty = "EASY";
	
	gf.setNewGame(rol,difficulty);
	
}


public void otherGame() throws IOException {
	this.gf = new GameFactory(this);
	
}


public void setAddView() {
	// TODO Auto-generated method stub

	vI.setVisible(false);
	vistaAdd.setVisible(true);
}


public void setInstruccions(CtrlPresentacio ctrlP) {
	// TODO Auto-generated method stub
	vistaAdd.setVisible(false);
	vI = new ViewInstruccions(this);
	vI.setVisible(true);;
}



//////////////////////// Metodos de sincronizacion entre vistas

/*  public void sincronizacionVistaPrincipal_a_Secundaria() {
    vistaPrincipal.desactivar();
    // Solo se crea una vista secundaria (podria crearse una nueva cada vez)
    if (vistaSecundaria == null)
      vistaSecundaria = new VistaSecundaria(this);
      vistaSecundaria.hacerVisible();
  }

  public void sincronizacionVistaSecundaria_a_Principal() {
    // Se hace invisible la vista secundaria (podria anularse)
    vistaSecundaria.hacerInvisible();
    vistaPrincipal.activar();
  }
*/

//////////////////////// Llamadas al controlador de dominio

/*
  public ArrayList<String> llamadaDominio1 (String selectedItem) {
    return ctrlDominio.llamadaDominio1(selectedItem);
  }

  public ArrayList<String> llamadaDominio2() {
    return ctrlDominio.llamadaDominio2();
  }*/

}

////////////////////////
