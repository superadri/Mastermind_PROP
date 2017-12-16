package presentation;

import com.sun.istack.internal.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;

public class VistaUser {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogUser = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonLogin;
    private JButton buttonQuit;
    private JTextField textFieldName;
    private Image windowsGlobal;

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
        dialogUser.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) {
            }
        });
    }
	
	private void asignarListenersComponentes() {
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            if (!textFieldName.getText().equals("")) {
                System.out.println("Name: " + textFieldName.getText());
                String messageUser = "User No Registrado";
                if ( controladorPresentacion.user_exists(textFieldName.getText()) ) { messageUser = "User Registrado"; }
                System.out.println(messageUser);
                controladorPresentacion.countLevelGuess = 0;
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
