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
    private JLabel LabelNamePlayer;
    private JSpinner spinnerNum;
    public String nameName;
    private boolean controlMachine = false;

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
        LabelNamePlayer.setText(nameName);
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
        int numGames = (int) spinnerNum.getValue();
        controladorPresentacion.sincronizacionVistaRoleDifficultyAPrincipal(nameName, role, difficulty);
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
                if (e.getItem().equals("Machine vs Machine(Random)") || e.getItem().equals("Machine vs Machine(Complex)")) {
                    spinnerNum.setEnabled(true);
                } else { spinnerNum.setEnabled(false); }
            }
        });

        // call .dispose() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { controladorPresentacion.sincronizacionVistaRoleDifficultyAUser(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaRoleDifficulty vR = new VistaRoleDifficulty(cP);
        vR.hacerVisible();
    }

}