package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaUser {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogUser = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonLogin;
    private JButton buttonQuit;
    private JTextField textFieldName;

	    /** Constructora **/
	
	public VistaUser(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
	}

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogUser.pack();
        dialogUser.setVisible(true);
	}

    public void hacerInvisible() {
        dialogUser.pack();
        dialogUser.setVisible(false);
        System.out.println("Estoy Invisible - VistaUser");
    }

	    /** Métodos privados **/
	
	private void inicializarComponentes() {
        dialogUser.setTitle("Login");
        dialogUser.setContentPane(contentPaneForm);
        dialogUser.setModal(true);
        dialogUser.setMinimumSize(new Dimension(300, 200));
        dialogUser.setLocationRelativeTo(null);
        dialogUser.setResizable(false);
        dialogUser.getRootPane().setDefaultButton(buttonLogin);
	}
	
	private void asignarListenersComponentes() {
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            if (!textFieldName.getText().equals("")) {
                System.out.println("Name: " + textFieldName.getText());
                controladorPresentacion.sincronizacionVistaUserAPrincipal();
            } else { System.out.println("Name Error"); }
            }
        });

            // call .dispose() on cancelButton
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // dialogUser.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            System.exit(0);
            }
        });

            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { System.exit(0); }
            }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	/*
    public static void main(String[] args) {
            CtrlPresentacion cP = new CtrlPresentacion();
            VistaUser dialog = new VistaUser(cP);
            dialog.hacerVisible();
            System.exit(0);
    }
    */
}
