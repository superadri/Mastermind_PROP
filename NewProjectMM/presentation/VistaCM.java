package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaCM {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogCM = new JDialog();
    private JPanel invalidGuessPanel;
    private JLabel invalidGuessLabel;
    private JPanel selectColorPanel;
    private ImageIcon pegRed;
    private ImageIcon pegOrange;
    private ImageIcon pegYellow;
    private ImageIcon pegGreen;
    private ImageIcon pegBlue;
    private ImageIcon pegPurple;
    private ImageIcon pegBlack;
    private ImageIcon selectedColor;
    private JPanel contentPaneForm;
    private JButton buttonMakeGuess;
    private JLabel g01p1, g01p2, g01p3, g01p4, selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple;
    private JLabel[] guesses = { g01p1, g01p2, g01p3, g01p4 };

        /** Constructora **/

	public VistaCM(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogCM.setVisible(true);
        System.out.println("Estoy Visible - VistaCM");
	}

    public void hacerInvisible() {
	    dialogCM.setVisible(false);
    }

    public void iniPegCM() {
        for (JLabel peg : guesses) { peg.setIcon(pegBlack); }
    }

	    /** Métodos privados **/
    private void inicializarComponentes() {
        inicializardialogCM();
        inicializarPeg();
        asignarListenersComponentes();
    }

    private void inicializardialogCM() {
        dialogCM.setTitle("CM");
        dialogCM.setContentPane(contentPaneForm);
        dialogCM.setModal(true);
        dialogCM.setMinimumSize(new Dimension(550, 200));
        dialogCM.setLocationRelativeTo(null);
        dialogCM.setResizable(false);
        dialogCM.getRootPane().setDefaultButton(buttonMakeGuess);
        dialogCM.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaCMARoleDifficulty(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) {
            }
        });
    }

    private void inicializarPeg() {
        try {
            pegRed = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-red.png")));
            pegOrange = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-orange.png")));
            pegYellow = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-yellow.png")));
            pegGreen = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-green.png")));
            pegBlue = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-blue.png")));
            pegPurple = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-purple.png")));
            pegBlack = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-black.png")));
        } catch (java.io.IOException ex) { System.out.println("Error1: " + ex); }

        selectRed.setIcon(pegRed);
        selectOrange.setIcon(pegOrange);
        selectYellow.setIcon(pegYellow);
        selectGreen.setIcon(pegGreen);
        selectBlue.setIcon(pegBlue);
        selectPurple.setIcon(pegPurple);
        for (JLabel peg : guesses) { peg.setIcon(pegBlack); }
    }

    private boolean checkValidAnswer() {
        int check = 1;
        // Controla que no haya huecos vacios (pegs negros)
        switch (check) {
            case 1:
                if (g01p1.getIcon() == pegBlack ||
                        g01p2.getIcon() == pegBlack ||
                        g01p3.getIcon() == pegBlack ||
                        g01p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
        }

        // Controla que no haya repeticion si es dificultad EASY o MEDIUM
        // TODO esta hardcodeado, seria mejor acceder a un booleano repetition ya que si se cambia que dificultades aceptan repeticion habra que cambiar esto manualmente.
        if (!controladorPresentacion.difficulty.equals("HARD")) {
            switch (check) {
                case 1:
                    if (g01p1.getIcon() == g01p2.getIcon() || g01p1.getIcon() == g01p3.getIcon() ||
                            g01p1.getIcon() == g01p4.getIcon() || g01p2.getIcon() == g01p3.getIcon() ||
                            g01p2.getIcon() == g01p4.getIcon() || g01p3.getIcon() == g01p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
	
	private void asignarListenersComponentes() {

        for (int i = 0; i < guesses.length; ++i) {
            final JLabel peg = guesses[i];
            MouseListener ml = myMouseListener(peg);
            peg.addMouseListener(ml);
        }

        final JLabel[] colorSelect = {selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple};

        for (final JLabel peg : colorSelect) {
            peg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (peg.isEnabled()) {
                        for (JLabel peg : colorSelect) { peg.setEnabled(true); }
                        peg.setEnabled(false);
                        selectedColor = (ImageIcon) peg.getIcon();
                    } else {
                        peg.setEnabled(true);
                        selectedColor = null;
                    }
                }

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

        buttonMakeGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ( checkValidAnswer() ) {
                    String codeOut = "";
                    for (JLabel peg : guesses) {
                        MouseListener[] mls = peg.getMouseListeners();
                        if (mls != null) {
                            for (MouseListener ml : mls) {
                                codeOut += traductorColorToStringGuess(peg.getIcon());
                            }
                        }
                    }
                    controladorPresentacion.sincronizacionVistaCMAPrincipal(codeOut);
                } else { invalidGuessPanel.setVisible(true); }
            }
        });
            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) { controladorPresentacion.sincronizacionVistaCMARoleDifficulty(); }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

    private MouseListener myMouseListener(final JLabel peg){
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (selectedColor != null) { peg.setIcon(selectedColor); }
                else { peg.setIcon(pegBlack); }
            }

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
        };
        return ml;
    }

    // Traduce sintaxis peg to código algoritmo to Guess
    private char traductorColorToStringGuess(Icon peg){
        char outLetter = 'n';
        if (peg.equals(pegGreen)) { outLetter = 'A'; }
        else if (peg.equals(pegBlue)) { outLetter = 'B'; }
        else if (peg.equals(pegOrange)) { outLetter = 'C'; }
        else if (peg.equals(pegRed)) { outLetter = 'D'; }
        else if (peg.equals(pegPurple)) { outLetter = 'E'; }
        else if (peg.equals(pegYellow)) { outLetter = 'F'; }
        return outLetter;
    }

    /*
    public static void main(String[] args) {
            CtrlPresentacion cP = new CtrlPresentacion();
            VistaCM dialog = new VistaCM(cP);
            dialog.hacerVisible();
            System.exit(0);
    }
    */
}
