import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.ExportException;

public class MastermindView {

    private ImageIcon pegRed;
    private ImageIcon pegOrange;
    private ImageIcon pegYellow;
    private ImageIcon pegGreen;
    private ImageIcon pegBlue;
    private ImageIcon pegPurple;
    private ImageIcon pegBlack;
    private ImageIcon pegWhite;
    private ImageIcon selectedColor;
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

        try {
            pegRed = new ImageIcon(ImageIO.read(getClass().getResource("./peg-red.png")));
            pegOrange = new ImageIcon(ImageIO.read(getClass().getResource("./peg-orange.png")));
            pegYellow = new ImageIcon(ImageIO.read(getClass().getResource("./peg-yellow.png")));
            pegGreen = new ImageIcon(ImageIO.read(getClass().getResource("./peg-green.png")));
            pegBlue = new ImageIcon(ImageIO.read(getClass().getResource("./peg-blue.png")));
            pegPurple = new ImageIcon(ImageIO.read(getClass().getResource("./peg-purple.png")));
            pegBlack = new ImageIcon(ImageIO.read(getClass().getResource("./peg-black.png")));
            pegWhite = new ImageIcon(ImageIO.read(getClass().getResource("./peg-white.png")));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        JLabel[] board = {
                g01p1, g01p2, g01p3, g01p4, a01p1, a01p2, a01p3, a01p4,
                g02p1, g02p2, g02p3, g02p4, a02p1, a02p2, a02p3, a02p4,
                g03p1, g03p2, g03p3, g03p4, a03p1, a03p2, a03p3, a03p4,
                g04p1, g04p2, g04p3, g04p4, a04p1, a04p2, a04p3, a04p4,
                g05p1, g05p2, g05p3, g05p4, a05p1, a05p2, a05p3, a05p4,
                g06p1, g06p2, g06p3, g06p4, a06p1, a06p2, a06p3, a06p4,
                g07p1, g07p2, g07p3, g07p4, a07p1, a07p2, a07p3, a07p4,
                g08p1, g08p2, g08p3, g08p4, a08p1, a08p2, a08p3, a08p4,
                g09p1, g09p2, g09p3, g09p4, a09p1, a09p2, a09p3, a09p4,
                g10p1, g10p2, g10p3, g10p4, a10p1, a10p2, a10p3, a10p4
        };

        JLabel[] guesses = {
                g01p1, g01p2, g01p3, g01p4,
                g02p1, g02p2, g02p3, g02p4,
                g03p1, g03p2, g03p3, g03p4,
                g04p1, g04p2, g04p3, g04p4,
                g05p1, g05p2, g05p3, g05p4,
                g06p1, g06p2, g06p3, g06p4,
                g07p1, g07p2, g07p3, g07p4,
                g08p1, g08p2, g08p3, g08p4,
                g09p1, g09p2, g09p3, g09p4,
                g10p1, g10p2, g10p3, g10p4,
        };

        selectRed.setIcon(pegRed);
        selectOrange.setIcon(pegOrange);
        selectYellow.setIcon(pegYellow);
        selectGreen.setIcon(pegGreen);
        selectBlue.setIcon(pegBlue);
        selectPurple.setIcon(pegPurple);
        for (JLabel peg : board) { peg.setIcon(pegBlack); }

        // Hardcoded guess
        g01p1.setIcon(pegRed);
        g01p2.setIcon(pegRed);
        g01p3.setIcon(pegBlue);
        g01p4.setIcon(pegBlue);

        // Hardcoded answer
        a01p1.setIcon(pegWhite);
        a01p2.setIcon(pegWhite);
        a01p3.setIcon(pegRed);
        a01p4.setIcon(pegRed);

        for (JLabel peg : guesses) {
            peg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (selectedColor != null) {
                        peg.setIcon(selectedColor);
                    } else {
                        peg.setIcon(pegBlack);
                    }
                }
            });
            peg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    peg.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    peg.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

        JLabel[] colorSelect = {selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple};

        for (JLabel peg : colorSelect) {
            peg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (peg.isEnabled()) {
                        for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                        peg.setEnabled(false);
                        selectedColor = (ImageIcon) peg.getIcon();
                    }
                    else {
                        peg.setEnabled(true);
                        selectedColor = null;
                    }
                }
            });
            peg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    peg.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    peg.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }

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
