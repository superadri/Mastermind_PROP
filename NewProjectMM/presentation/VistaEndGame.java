package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class VistaEndGame {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogEndGame = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonQuit;
    private JButton buttonNewGame;

    /** Constructora **/

	public VistaEndGame(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
	}

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogEndGame.pack();
        dialogEndGame.setVisible(true);
	}

    public void hacerInvisible() {
        dialogEndGame.pack();
        dialogEndGame.setVisible(false);
        System.out.println("Estoy Invisible - VistaEndGame");
    }

	    /** Métodos privados **/
	
	private void inicializarComponentes() {
        dialogEndGame.setTitle("Result");
        dialogEndGame.setContentPane(contentPaneForm);
        dialogEndGame.setModal(true);
        dialogEndGame.setMinimumSize(new Dimension(300, 200));
        dialogEndGame.setLocationRelativeTo(null);
        dialogEndGame.setResizable(false);
        dialogEndGame.getRootPane().setDefaultButton(buttonQuit);
	}
	
	private void asignarListenersComponentes() {

        buttonNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dialogEndGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                controladorPresentacion.sincronizacionVistaEndGameAUser();
            }
        });

            // call .dispose() on cancelButton
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // dialogEndGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            controladorPresentacion.sincronizacionVistaEndGameAPrincipal();
            }
        });

            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            controladorPresentacion.sincronizacionVistaEndGameAPrincipal();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	/*
    public static void main(String[] args) {
            CtrlPresentacion cP = new CtrlPresentacion();
            VistaEndGame dialog = new VistaEndGame(cP);
            dialog.hacerVisible();
            System.exit(0);
    }
    */
}
