import java.io.IOException;

public class MainMastermind {
    public static void main (String[] args) {
       {
            try {
                CtrlPresentacion ctrlPresentacion = new CtrlPresentacion();
                ctrlPresentacion.initGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
       };
    }
}
