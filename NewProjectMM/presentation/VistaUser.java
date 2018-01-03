package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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

    public void setNameTextField(String username) {
        textFieldName.setText(username);
        textFieldName.revalidate();
        textFieldName.repaint();
	}

	    /** Métodos privados **/

	private void inicializarComponentes() {
        // ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("../persistence/windowsGlobal.png")));
        // Image scaleImage  = icon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT);
        // label = new JLabel(new ImageIcon(scaleImage));
        // contentPaneForm.add(label);
        // dialogUser.getContentPane().add(BorderLayout.CENTER, label);
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
            } else { System.out.println("Name Error"); }
            }
        });

            // call .dispose() on cancelButton
        buttonQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
