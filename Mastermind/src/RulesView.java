import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulesView {
    private final CtrlPresentacion ctrlP;
    private JButton exitButton;
    JPanel rules;

    public RulesView(CtrlPresentacion ctrlP) {
        this.ctrlP = ctrlP;
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlP.hideRules();
            }
        });
    }
}
