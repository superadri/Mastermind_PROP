import java.awt.*;
import javax.swing.*;

////////////////////////

public class VistaInstrucciones {

	private JDialog dialogOptionPane;

	public VistaInstrucciones() {}

	public void hacerVisible() {
		String strTitulo = "LEEME";
		String[] strBotones = {"Ok"};
		String strTexto =
		"Bienvenido a Mastermind " + "\n" +
		"Instrucciones:  "  + "\n" +
		"----------------" + "\n" +
		"B -> Posicion y letra correcto"+ "\n" +
		"R -> Letra correcta\n";
		JOptionPane optionPane = new JOptionPane(strTexto,JOptionPane.INFORMATION_MESSAGE);
		optionPane.setOptions(strBotones);
		optionPane.setBackground(Color.orange);
		dialogOptionPane = optionPane.createDialog(new JFrame(),strTitulo);
		dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialogOptionPane.pack();
		dialogOptionPane.setVisible(true);
	}
}
