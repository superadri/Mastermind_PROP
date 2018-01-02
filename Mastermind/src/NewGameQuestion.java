import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class NewGameQuestion {
    JPanel granPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton newGameButton;
    private CtrlPresentacion ctrlP;

    public NewGameQuestion(CtrlPresentacion ctrlP) {

        this.ctrlP = ctrlP;
        comboBox1.addItem("CodeMaker");
        comboBox1.addItem("CodeBreaker");
        comboBox1.addItem("MachinevsMachine");

        comboBox2.addItem("Easy");
        comboBox2.addItem("Medium");
        comboBox2.addItem("Hard");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String role = (String)comboBox1.getSelectedItem();
                String difficulty = (String)comboBox2.getSelectedItem();
                try {
                    ctrlP.setnewgameView(role,difficulty);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
