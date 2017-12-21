package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaQuestionToContinue {

    private CtrlPresentacion controladorPresentacion;

    private JDialog dialogContinue = new JDialog();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public VistaQuestionToContinue(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
    }

    public void hacerVisible() {
        dialogContinue.pack();
        dialogContinue.setVisible(true);
    }

    public void hacerInvisible() {
        dialogContinue.pack();
        dialogContinue.setVisible(false);
    }

    public void setNameLabel(String username) {
        label.setText("¿Quieres continuar la partida "+username+"?");
        label.revalidate();
        label.repaint();
    }

    private void inicializarComponentes() {
        dialogContinue.setTitle("Continue");
        dialogContinue.setContentPane(contentPane);
        dialogContinue.setModal(true);
        dialogContinue.setMinimumSize(new Dimension(200, 100));
        dialogContinue.setLocationRelativeTo(null); // Centro
        dialogContinue.setResizable(false);
        dialogContinue.getRootPane().setDefaultButton(buttonOK);
        dialogContinue.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaQuestionToContinueAUser(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });
    }

    private void onOK() {
        // add your code here
        controladorPresentacion.sincronizacionVistaQuestionToContinueAPrincipal();
    }

    private void onCancel() {
        // add your code here if necessary
        controladorPresentacion.sincronizacionVistaQuestionToContinueARoleDifficulty();
    }


    private void asignarListenersComponentes() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call .dispose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { controladorPresentacion.sincronizacionVistaQuestionToContinueAUser(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /*
    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaQuestionToContinue vE = new VistaQuestionToContinue(cP);
        vE.hacerVisible();
    }
    */
}
