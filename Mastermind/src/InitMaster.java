import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InitMaster {
    JPanel PanelIni;
    private JButton STARTButton;
    private JButton RULESButton;
    private JTextField textField1;

    private String username;
    public InitMaster(CtrlPresentacion ctrlP) {
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                username = textField1.getText();
                if (!username.equals("")) {
                    try {
                        ctrlP.sendUsername(username);
                        textField1.setText("");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });
        RULESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlP.showRules();
            }
        });
    }
}

