package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaRoleDifficulty {

    private CtrlPresentacion controladorPresentacion;

    private JDialog dialogDifficulty = new JDialog();
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;
    private JComboBox comboBox2;

    public VistaRoleDifficulty(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        inicializarQuestion();
        asignarListenersComponentes();
    }

    public void hacerVisible() {
        dialogDifficulty.pack();
        dialogDifficulty.setVisible(true);
        System.out.println("Estoy Visible - VistaRoleDifficulty");
    }

    public void hacerInvisible() {
        dialogDifficulty.pack();
        dialogDifficulty.setVisible(false);
    }

    private void inicializarComponentes() {
        comboBox1.addItem("CM");
        comboBox1.addItem("CB");
        comboBox1.addItem("MachinevsMachine");

        comboBox2.addItem("EASY");
        comboBox2.addItem("MEDIUM");
        comboBox2.addItem("HARD");
    }

    private void inicializarQuestion() {
        dialogDifficulty.setTitle("Choose");
        dialogDifficulty.setContentPane(contentPane);
        dialogDifficulty.setModal(true);
        dialogDifficulty.setMinimumSize(new Dimension(300, 150));
        dialogDifficulty.setLocationRelativeTo(null); // Centro
        dialogDifficulty.setResizable(false);
        dialogDifficulty.getRootPane().setDefaultButton(buttonOK);
        dialogDifficulty.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaRoleDifficultyAUser(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });
    }

    private void onOK() {
        // add your code here
        String role = (String)comboBox1.getSelectedItem();
        String difficulty = (String)comboBox2.getSelectedItem();
        controladorPresentacion.sincronizacionVistaRoleDifficultyAPrincipal(role,difficulty);
    }

    private void asignarListenersComponentes() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call .dispose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { controladorPresentacion.sincronizacionVistaRoleDifficultyAUser(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /*
    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaRoleDifficulty vR = new VistaRoleDifficulty(cP);
        vR.hacerVisible();
    }
    */

}
