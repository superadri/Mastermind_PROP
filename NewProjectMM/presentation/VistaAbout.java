package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaAbout {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogAbout = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonQuit;
    private JTextPane textPaneAbout;
    private String strTexto =
            "Andreu Esteras" + "\n" +
            "Andrià Marcoval" + "\n" +
            "Pedro Llanos" + "\n";

        /** Constructora **/

	public VistaAbout(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
	}

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogAbout.pack();
        dialogAbout.setVisible(true);
	}

    public void hacerInvisible() {
        dialogAbout.pack();
        dialogAbout.setVisible(false);
        System.out.println("Estoy Invisible - VistaHelp");
    }

	    /** Métodos privados **/
	
	private void inicializarComponentes() {
        dialogAbout.setTitle("Help");
        textPaneAbout.setText(strTexto);
        textPaneAbout.setEditable(false);
        dialogAbout.setContentPane(contentPaneForm);
        dialogAbout.setModal(true);
        dialogAbout.setMinimumSize(new Dimension(300, 200));
        dialogAbout.setLocationRelativeTo(null);
        dialogAbout.setResizable(false);
        dialogAbout.getRootPane().setDefaultButton(buttonQuit);
        dialogAbout.addWindowListener(new WindowAdapter() {
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
            // dialogAbout.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                controladorPresentacion.sincronizacionVistaAboutAPrincipal();
            }
        });

            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorPresentacion.sincronizacionVistaAboutAPrincipal();
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
