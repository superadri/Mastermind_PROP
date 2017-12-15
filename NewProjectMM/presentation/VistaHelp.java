package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VistaHelp {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogHelp = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonQuit;
    private JTextPane textPaneEndGame;
    private String strTexto =
            "Bienvenido a Mastermind " + "\n" +
                    "Instrucciones:  "  + "\n" +
                    "----------------" + "\n" +
                    "B -> Posicion y letra correcto"+ "\n" +
                    "R -> Letra correcta\n";

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
        textPaneEndGame.setText(strTexto);
        textPaneEndGame.setEditable(false);
        dialogHelp.setContentPane(contentPaneForm);
        dialogHelp.setModal(true);
        dialogHelp.setMinimumSize(new Dimension(300, 200));
        dialogHelp.setLocationRelativeTo(null);
        dialogHelp.setResizable(false);
        dialogHelp.getRootPane().setDefaultButton(buttonQuit);
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
