package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

public class VistaRoleDifficulty {

    private CtrlPresentacion controladorPresentacion;

    private JDialog dialogDifficulty = new JDialog();
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel labelNamePlayer;
    private JSpinner spinnerNum;
    private String userName;

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

    public void setNameUserJlabel(String username, boolean existUser) {
        if (existUser) { labelNamePlayer.setText("Bienvenido de nuevo "+username); }
        else { labelNamePlayer.setText("Bienvenido/a "+username+" - Nuevo Registro"); }
        labelNamePlayer.revalidate();
        labelNamePlayer.repaint();
    }

    private void inicializarComponentes() {
        comboBox1.addItem("CB");
        comboBox1.addItem("CM");
        comboBox1.addItem("Machine vs Machine(Random)");
        comboBox1.addItem("Machine vs Machine(Complex)");


        comboBox2.addItem("EASY");
        comboBox2.addItem("MEDIUM");
        comboBox2.addItem("HARD");

        spinnerNum.setValue(1);
        spinnerNum.setEnabled(false);
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
        Integer numGames = (Integer) spinnerNum.getValue();
        if ( role.equals("CB") ) { controladorPresentacion.sincronizacionVistaRoleDifficultyAPrincipal(role, difficulty); }
        else if ( role.equals("CM") ){ controladorPresentacion.sincronizacionVistaRoleDifficultyACM(role, difficulty); }
        else { controladorPresentacion.sincronizacionVistaRoleDifficultyAEndGame(role, difficulty,numGames); }
    }

    private void asignarListenersComponentes() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                spinnerNum.setEnabled( !e.getItem().equals("CB") && !e.getItem().equals("CM") );
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
