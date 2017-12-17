package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaQuestionToContinue extends JDialog {
    private final CtrlPresentacion ctrlP;
    private final String username;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;
    private JDialog dialogQuestion = new JDialog();

    public VistaQuestionToContinue(CtrlPresentacion ctrlP, String username) {
        this.ctrlP = ctrlP;
        this.username = username;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        inicializarQuestion();
        label.setText("¿Quieres continuar la partida "+username+"?");

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
    }

    private void inicializarQuestion() {
        dialogQuestion.setTitle("Question");
        dialogQuestion.setContentPane(contentPane);
        dialogQuestion.setModal(true);
        dialogQuestion.setMinimumSize(new Dimension(200, 100));
        dialogQuestion.setLocationRelativeTo(null); // Centro
        dialogQuestion.setResizable(false);
        /*dialogQuestion.getRootPane().setDefaultButton(buttonQuit);
        dialogQuestion.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaRankingAPrincipal(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });*/
    }

    private void onOK() {
        // add your code here
        hacerInvisible();
        ctrlP.sincronizacionContinueGame();
    }

    private void onCancel() {
        // add your code here if necessary
        hacerInvisible();
        ctrlP.sincronizacionRole();
    }


    public void hacerVisible() {
        dialogQuestion.pack();
        dialogQuestion.setVisible(true);
    }

    public void hacerInvisible() {
        dialogQuestion.pack();
        dialogQuestion.setVisible(false);
    }
    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaQuestionToContinue vE = new VistaQuestionToContinue(cP,"niño");
        vE.hacerVisible();
    }

}
