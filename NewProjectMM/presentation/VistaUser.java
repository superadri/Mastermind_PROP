package presentation;


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
        dialogUser.setVisible(true);
        System.out.println("Estoy Visible - VistaUser");
	}

    public void hacerInvisible() {
        dialogUser.setVisible(false);
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
                if ( controladorPresentacion.user_exists(textFieldName.getText()) ) {
                    System.out.println("User Registrado");
                    System.out.println("Cargando Datos...");
                    if( controladorPresentacion.game_start_user(textFieldName.getText()) ) {
                        System.out.println("Tiene partida pendiente...");
                        controladorPresentacion.sincronizacionVistaUserAQuestionToContinue(textFieldName.getText());
                    } else {
                        System.out.println("No tiene partida pendiente...");
                        controladorPresentacion.sincronizacionVistaUserARoleDifficulty(textFieldName.getText());
                    }
                } else {
                    System.out.println("User No Registrado");
                    controladorPresentacion.sincronizacionVistaUserARoleDifficulty(textFieldName.getText());
                }
                controladorPresentacion.countLevelGuess = 0;
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
