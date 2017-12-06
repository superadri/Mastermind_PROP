
import java.util.*;

////////////////////////

public class CtrlDominio {


//////////////////////// Constructor y metodos de inicializacion


  public CtrlDominio() {
  }

  public void inicializarCtrlDominio() {
  }


//////////////////////// Llamadas desde el controlador de presentacion

  public ArrayList<String> llamadaDominio1 (String selectedItem) {
    ArrayList<String> lista = new ArrayList<String>();
    for (int i=0; i<4; ++i)
      lista.add(selectedItem + " " + "resulDominio1-" + i);
    return lista;
  }

  public ArrayList<String> llamadaDominio2() {
    ArrayList<String> lista = new ArrayList<String>();
    for (int i=0; i<3; ++i)
      lista.add("resulDominio2-" + i);
    return lista;
  }

}

////////////////////////
