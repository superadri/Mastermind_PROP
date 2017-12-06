
import java.awt.*;
import javax.swing.*;

////////////////////////

public class VistaLEEME {

  public VistaLEEME() {
  }

  public void hacerVisible() {
    String strTitulo = "LEEME";
    String[] strBotones = {"Ok"};
    String strTexto =
      "Este ejemplo no pretende ser un modelo universal, sino un posible " + "\n" + 
      "punto de partida para la construcción de una interficie en Java. "  + "\n" +
      "\n" + 
      "Las interficies en Java se pueden hacer de muchas maneras, y ésta " + "\n" + 
      "es una de ellas, pero en ningún momento se sugiere que haya que "+ "\n" + 
      "hacerlo así o que ésta sea la mejor manera de hacerlo.\n";
    JOptionPane optionPane =
      new JOptionPane(strTexto,JOptionPane.INFORMATION_MESSAGE);
    optionPane.setOptions(strBotones);
    optionPane.setBackground(Color.orange);
    JDialog dialogOptionPane = optionPane.createDialog(new JFrame(),strTitulo);
    dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialogOptionPane.pack();
    dialogOptionPane.setVisible(true);
  }

}

////////////////////////
