package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaHelp {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogHelp = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonQuit;
    private JTextPane textPaneAbout;
    private String strTexto =
            "Bienvenido a Mastermind " + "\n" +
                    "Instrucciones:  "  + "\n" +
                    "----------------" + "\n" +
                    "Blanco -> Posicion y letra correcto"+ "\n" +
                    "Rojo -> Letra correcta\n" +
                    "++++++++++++++++" + "\n" +
                    "Dificultad: \n" +
                    "----------------" + "\n" +
                    "EASY -> F: 10, L: 6, C: 4, R: No\n" +
                    "MEDIUM -> F: 7, L: 6, C: 4, R: No\n" +
                    "HARD -> F: 6, L: 6, C: 4, R: Sí\n" +
                    "(F:Filas, L:Colores, C:Columnas, R:Repetidos)\n";

        /** Constructora **/

	public VistaHelp(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
	}

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogHelp.pack();
        dialogHelp.setVisible(true);
	}

    public void hacerInvisible() {
        dialogHelp.pack();
        dialogHelp.setVisible(false);
        System.out.println("Estoy Invisible - VistaHelp");
    }

	    /** Métodos privados **/
	
	private void inicializarComponentes() {
        dialogHelp.setTitle("Help");
        textPaneAbout.setText(strTexto);
        textPaneAbout.setEditable(false);
        dialogHelp.setContentPane(contentPaneForm);
        dialogHelp.setModal(true);
        dialogHelp.setMinimumSize(new Dimension(650, 350));
        dialogHelp.setLocationRelativeTo(null);
        dialogHelp.setResizable(false);
        dialogHelp.getRootPane().setDefaultButton(buttonQuit);
        dialogHelp.addWindowListener(new WindowAdapter() {
            @Override   //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaHelpAPrincipal(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });
	}
	
	private void asignarListenersComponentes() {

            // call .dispose() on cancelButton
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // dialogHelp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                controladorPresentacion.sincronizacionVistaHelpAPrincipal();
            }
        });

            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorPresentacion.sincronizacionVistaHelpAPrincipal();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	/*
    public static void main(String[] args) {
            CtrlPresentacion cP = new CtrlPresentacion();
            VistaHelp dialog = new VistaHelp(cP);
            dialog.hacerVisible();
            System.exit(0);
    }
    */
}
