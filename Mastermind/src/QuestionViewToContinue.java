import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionViewToContinue {
    private JButton noButton;
    private JButton siButton;
    JPanel questio;
    CtrlPresentacion ctrlP;

    public QuestionViewToContinue(CtrlPresentacion ctrlP) {
        this.ctrlP = ctrlP;
        siButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlP.continueGameView();
            }
        });
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ctrlP.newgameView();
            }
        });
    }
}
