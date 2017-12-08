import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.server.ExportException;

public class MastermindView {

    private JPanel backgroundPanel;
    private JButton buttonMakeGuess;
    private JLabel labelGuess10;
    private JPanel contentPanel;
    private JPanel selectColorPanel;
    private JPanel boardPanel;
    private JPanel panelLabels;
    private JPanel panelGuesses;
    private JPanel panelAnswers;
    private JLabel selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple;
    private JLabel  g01p1, g01p2, g01p3, g01p4, a01p1, a01p2, a01p3, a01p4,
                    g02p1, g02p2, g02p3, g02p4, a02p1, a02p2, a02p3, a02p4,
                    g03p1, g03p2, g03p3, g03p4, a03p1, a03p2, a03p3, a03p4,
                    g04p1, g04p2, g04p3, g04p4, a04p1, a04p2, a04p3, a04p4,
                    g05p1, g05p2, g05p3, g05p4, a05p1, a05p2, a05p3, a05p4,
                    g06p1, g06p2, g06p3, g06p4, a06p1, a06p2, a06p3, a06p4,
                    g07p1, g07p2, g07p3, g07p4, a07p1, a07p2, a07p3, a07p4,
                    g08p1, g08p2, g08p3, g08p4, a08p1, a08p2, a08p3, a08p4,
                    g09p1, g09p2, g09p3, g09p4, a09p1, a09p2, a09p3, a09p4,
                    g10p1, g10p2, g10p3, g10p4, a10p1, a10p2, a10p3, a10p4;

    public MastermindView() {

        JLabel[] board = {  g01p1, g01p2, g01p3, g01p4, a01p1, a01p2, a01p3, a01p4,
                            g02p1, g02p2, g02p3, g02p4, a02p1, a02p2, a02p3, a02p4,
                            g03p1, g03p2, g03p3, g03p4, a03p1, a03p2, a03p3, a03p4,
                            g04p1, g04p2, g04p3, g04p4, a04p1, a04p2, a04p3, a04p4,
                            g05p1, g05p2, g05p3, g05p4, a05p1, a05p2, a05p3, a05p4,
                            g06p1, g06p2, g06p3, g06p4, a06p1, a06p2, a06p3, a06p4,
                            g07p1, g07p2, g07p3, g07p4, a07p1, a07p2, a07p3, a07p4,
                            g08p1, g08p2, g08p3, g08p4, a08p1, a08p2, a08p3, a08p4,
                            g09p1, g09p2, g09p3, g09p4, a09p1, a09p2, a09p3, a09p4,
                            g10p1, g10p2, g10p3, g10p4, a10p1, a10p2, a10p3, a10p4 };

        try {

            Image img = ImageIO.read(getClass().getResource("./peg-red.png"));
            selectRed.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-orange.png"));
            selectOrange.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-yellow.png"));
            selectYellow.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-green.png"));
            selectGreen.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-blue.png"));
            selectBlue.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-purple.png"));
            selectPurple.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-black.png"));
            for (JLabel peg : board) { peg.setIcon(new ImageIcon(img)); }

            // Hardcoded guess
            img = ImageIO.read(getClass().getResource("./peg-red.png"));
            g01p1.setIcon(new ImageIcon(img));
            g01p2.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-blue.png"));
            g01p3.setIcon(new ImageIcon(img));
            g01p4.setIcon(new ImageIcon(img));

            // Hardcoded answer
            img = ImageIO.read(getClass().getResource("./peg-red.png"));
            a01p2.setIcon(new ImageIcon(img));
            a01p4.setIcon(new ImageIcon(img));
            img = ImageIO.read(getClass().getResource("./peg-white.png"));
            a01p1.setIcon(new ImageIcon(img));
            a01p3.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println(ex);
        }

        JLabel[] colorSelect = {selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple};

        selectRed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectRed.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectRed.setEnabled(false);
                }
                else { selectRed.setEnabled(true); }
            }
        });

        selectOrange.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectOrange.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectOrange.setEnabled(false);
                }
                else { selectOrange.setEnabled(true); }
            }
        });

        selectYellow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectYellow.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectYellow.setEnabled(false);
                }
                else { selectYellow.setEnabled(true); }
            }
        });

        selectGreen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectGreen.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectGreen.setEnabled(false);
                }
                else { selectGreen.setEnabled(true); }
            }
        });

        selectBlue.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectBlue.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectBlue.setEnabled(false);
                }
                else { selectBlue.setEnabled(true); }
            }
        });

        selectPurple.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectPurple.isEnabled()) {
                    for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                    selectPurple.setEnabled(false);
                }
                else { selectPurple.setEnabled(true); }
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
