import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MastermindView {
    private JPanel backgroundPanel;
    private JButton buttonRed;
    private JButton buttonOrange;
    private JButton buttonYellow;
    private JButton buttonGreen;
    private JButton buttonBlue;
    private JButton buttonPurple;
    private JButton buttonGuess;
    private JLabel labelGuess10;
    private JPanel contentPanel;
    private JPanel buttonBarPanel;
    private JPanel boardPanel;
    private JPanel titlePanel;
    private JPanel guessAndAnswersPanel;
    private JPanel guess10Panel;
    private JPanel guess9Panel;
    private JPanel guess8Panel;
    private JPanel guess7Panel;
    private JPanel guess6Panel;
    private JPanel guess5Panel;
    private JPanel guess4Panel;
    private JPanel guess3Panel;
    private JPanel guess2Panel;
    private JPanel guess1Panel;
    private JLabel Label10;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button24;
    private JButton button25;
    private JButton button26;
    private JButton button27;
    private JButton button28;
    private JButton button29;
    private JButton button30;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JButton button34;
    private JButton button35;
    private JButton button36;
    private JButton button37;
    private JButton button38;
    private JButton button39;
    private JButton button40;
    private JButton button41;
    private JButton button42;
    private JButton button43;
    private JButton button44;
    private JButton button45;
    private JButton button46;
    private JButton button47;
    private JButton button48;
    private JButton button49;
    private JButton button50;
    private JButton button51;
    private JButton button52;
    private JButton button53;
    private JButton button54;
    private JButton button55;
    private JButton button56;
    private JButton button57;
    private JButton button58;
    private JButton button59;
    private JButton button60;
    private JButton button61;
    private JButton button62;
    private JButton button63;
    private JButton button64;
    private JButton button65;
    private JButton button66;
    private JButton button67;
    private JButton button68;
    private JButton button69;
    private JButton button70;
    private JButton button71;
    private JButton button72;
    private JButton button73;
    private JButton button74;
    private JButton button75;
    private JButton button76;
    private JButton button77;
    private JButton button78;
    private JButton button79;
    private JButton button80;

    public MastermindView() {

        try {
            Image img = ImageIO.read(getClass().getResource("./redbutton-hi.png"));
            //buttonRed.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        JButton[] colorSelectButtons = {buttonRed, buttonOrange, buttonYellow, buttonGreen, buttonBlue, buttonPurple};

        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonRed.setEnabled(false);
            }
        });

        buttonOrange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonOrange.setEnabled(false);
            }
        });

        buttonYellow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonYellow.setEnabled(false);
            }
        });

        buttonGreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonGreen.setEnabled(false);
            }
        });

        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonBlue.setEnabled(false);
            }
        });

        buttonPurple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton btn : colorSelectButtons) { btn.setEnabled(true); }
                buttonPurple.setEnabled(false);
            }
        });

        // Visible es diferent de Enabled
        //buttonGuess.setVisible(false);
        //button4.setVisible(false);

        //guess10Panel
        //JPanel guess10Panel = new JPanel();
        //guess10Panel.add(Label10);
        //guess10Panel.add(labelGuess10);
        //guess10Panel.add(labelAnswer10);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MastermindView");
        frame.setContentPane(new MastermindView().backgroundPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Menu dialog = new Menu();
        dialog.pack();
        dialog.setVisible(true);
    }
}
