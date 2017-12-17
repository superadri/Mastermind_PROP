package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaRoleDifficulty extends JDialog {
    private final CtrlPresentacion controladorPresentacion;
    private JPanel contentPane;
    private JButton buttonOK;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JDialog dialogQuestion = new JDialog();

    public VistaRoleDifficulty(CtrlPresentacion ctrlP) {
        this.controladorPresentacion = ctrlP;
        inicializarComponentes();
        inicializarQuestion();
    }

    private void inicializarComponentes() {
        comboBox1.addItem("CM");
        comboBox1.addItem("CB");
        comboBox1.addItem("MachinevsMachine");

        comboBox2.addItem("EASY");
        comboBox2.addItem("MEDIUM");
        comboBox2.addItem("HARD");

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        String role = (String)comboBox1.getSelectedItem();
        String difficulty = (String)comboBox2.getSelectedItem();
        controladorPresentacion.setRoleDificulty(role,difficulty);
    }


    public void hacerVisible() {
        dialogQuestion.pack();
        dialogQuestion.setVisible(true);
    }

    public void hacerInvisible() {
        dialogQuestion.pack();
        dialogQuestion.setVisible(false);
    }

    private void inicializarQuestion() {
        // TODO: Configurar correctamente, para añadir ScrollBar
        // scrollPaneRanking = new JScrollPane(textAreaRanking);
        // scrollPaneRanking.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        // scrollPaneRanking.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // panelVistaRanking.setLayout(new BoxLayout(panelVistaRanking, BorderLayout.CENTER));
        // panelVistaRanking.add(scrollPaneRanking);
        dialogQuestion.setTitle("Choose");
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


    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaRoleDifficulty vR = new VistaRoleDifficulty(cP);
        vR.hacerVisible();
    }


}
