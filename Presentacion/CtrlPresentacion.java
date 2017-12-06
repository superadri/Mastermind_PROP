
import java.util.*;

////////////////////////

public class CtrlPresentacion {

  private CtrlDominio ctrlDominio;
  private VistaPrincipal vistaPrincipal = null;    // (= null) innecesario
  private VistaSecundaria vistaSecundaria = null;  // (= null) innecesario


//////////////////////// Constructor y metodos de inicializacion


  public CtrlPresentacion() {
    ctrlDominio = new CtrlDominio();
    if (vistaPrincipal == null)  // innecesario
      vistaPrincipal = new VistaPrincipal(this);
  }

  public void inicializarPresentacion() {
    ctrlDominio.inicializarCtrlDominio();
    vistaPrincipal.hacerVisible();
  }


//////////////////////// Metodos de sincronizacion entre vistas


  public void sincronizacionVistaPrincipal_a_Secundaria() {
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


//////////////////////// Llamadas al controlador de dominio


  public ArrayList<String> llamadaDominio1 (String selectedItem) {
    return ctrlDominio.llamadaDominio1(selectedItem);
  }

  public ArrayList<String> llamadaDominio2() {
    return ctrlDominio.llamadaDominio2();
  }

}

////////////////////////
