
////////////////////////

public class MainEjemplo {
  public static void main (String[] args) {
    javax.swing.SwingUtilities.invokeLater (
      new Runnable() {
        public void run() {
            new VistaLEEME().hacerVisible();
            CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
          ctrlPresentacion.inicializarPresentacion();
    }});
  }
}

////////////////////////
