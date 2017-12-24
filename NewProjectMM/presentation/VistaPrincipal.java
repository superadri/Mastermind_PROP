package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class VistaPrincipal {

    /**
     * Controlador de presentacion
     **/

    private CtrlPresentacion controladorPresentacion;

    private JDialog frameVista = new JDialog();
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
    private JPanel invalidGuessPanel;
    private JLabel invalidGuessLabel;
    private JLabel selectRed, selectOrange, selectYellow, selectGreen, selectBlue, selectPurple;
    private JLabel g01p1, g01p2, g01p3, g01p4, a01p1, a01p2, a01p3, a01p4,
            g02p1, g02p2, g02p3, g02p4, a02p1, a02p2, a02p3, a02p4,
            g03p1, g03p2, g03p3, g03p4, a03p1, a03p2, a03p3, a03p4,
            g04p1, g04p2, g04p3, g04p4, a04p1, a04p2, a04p3, a04p4,
            g05p1, g05p2, g05p3, g05p4, a05p1, a05p2, a05p3, a05p4,
            g06p1, g06p2, g06p3, g06p4, a06p1, a06p2, a06p3, a06p4,
            g07p1, g07p2, g07p3, g07p4, a07p1, a07p2, a07p3, a07p4,
            g08p1, g08p2, g08p3, g08p4, a08p1, a08p2, a08p3, a08p4,
            g09p1, g09p2, g09p3, g09p4, a09p1, a09p2, a09p3, a09p4,
            g10p1, g10p2, g10p3, g10p4, a10p1, a10p2, a10p3, a10p4;

    private JLabel[] board = {
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

    private JLabel[] guesses = {
            g01p1, g01p2, g01p3, g01p4,
            g02p1, g02p2, g02p3, g02p4,
            g03p1, g03p2, g03p3, g03p4,
            g04p1, g04p2, g04p3, g04p4,
            g05p1, g05p2, g05p3, g05p4,
            g06p1, g06p2, g06p3, g06p4,
            g07p1, g07p2, g07p3, g07p4,
            g08p1, g08p2, g08p3, g08p4,
            g09p1, g09p2, g09p3, g09p4,
            g10p1, g10p2, g10p3, g10p4
    };

    private JLabel[] answers = {
            a01p1, a01p2, a01p3, a01p4,
            a02p1, a02p2, a02p3, a02p4,
            a03p1, a03p2, a03p3, a03p4,
            a04p1, a04p2, a04p3, a04p4,
            a05p1, a05p2, a05p3, a05p4,
            a06p1, a06p2, a06p3, a06p4,
            a07p1, a07p2, a07p3, a07p4,
            a08p1, a08p2, a08p3, a08p4,
            a09p1, a09p2, a09p3, a09p4,
            a10p1, a10p2, a10p3, a10p4
    };

    private JMenuBar menubarVista = new JMenuBar();
    private JMenu menuFile = new JMenu("File");
    private JMenuItem menuitemSave = new JMenuItem("Save");
    private JMenuItem menuitemQuit = new JMenuItem("Quit");
    private JMenu menuOption = new JMenu("Option");
    private JMenuItem menuitemRanking = new JMenuItem("Ranking");
    private JMenu menuHelp = new JMenu("Help");
    private JMenuItem menuitemReedme = new JMenuItem("Reedme");
    private JMenu menuAbout = new JMenu("About");
    private JMenuItem menuitemInfo = new JMenuItem("Show");

    private boolean foundAnswer;
    private int controlSecuencia;

    /**
     * Constructora
     **/

    public VistaPrincipal(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
    }

    /**
     * Métodos públicos
     **/

    public void hacerVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
        System.out.println("Estoy Visible - VistaPrincipal");
    }

    public void hacerInvisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    public void activar() {
        frameVista.setEnabled(true);
    }

    public void desactivar() {
        frameVista.setEnabled(false);
    }

    /**
     * Métodos private
     **/

    private void inicializarComponentes() {
        inicializarFrameVista();
        inicializarMenubarVista();
        inicializarPeg();
        asignarListenersComponentes();
    }

    private void inicializarFrameVista() {
        frameVista.setTitle("Mastermind");
        frameVista.setContentPane(backgroundPanel);
        frameVista.setModal(true);
        frameVista.setMinimumSize(new Dimension(550, 550));
        frameVista.setLocationRelativeTo(null);
        frameVista.setResizable(false);
        frameVista.getRootPane().setDefaultButton(buttonMakeGuess);
        frameVista.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) {
                System.exit(0);
            }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) {
            }
        });
    }

    private void inicializarMenubarVista() {
        menuFile.add(menuitemSave);
        menuFile.add(menuitemQuit);
        menubarVista.add(menuFile);
        menuOption.add(menuitemRanking);
        menubarVista.add(menuOption);
        menuHelp.add(menuitemReedme);
        menubarVista.add(menuHelp);
        menuAbout.add(menuitemInfo);
        menubarVista.add(menuAbout);
        frameVista.setJMenuBar(menubarVista);
    }

    private void inicializarPeg() {
        try {
            pegRed = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-red.png")));
            pegRed.setDescription("r");
            pegOrange = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-orange.png")));
            pegOrange.setDescription("o");
            pegYellow = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-yellow.png")));
            pegYellow.setDescription("y");
            pegGreen = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-green.png")));
            pegGreen.setDescription("g");
            pegBlue = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-blue.png")));
            pegBlue.setDescription("b");
            pegPurple = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-purple.png")));
            pegPurple.setDescription("p");
            pegBlack = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-black.png")));
            pegBlack.setDescription("n");
            pegWhite = new ImageIcon(ImageIO.read(getClass().getResource("../persistence/peg-white.png")));
            pegWhite.setDescription("w");
        } catch (java.io.IOException ex) { System.out.println("Error1: " + ex); }

        selectRed.setIcon(pegRed);
        selectOrange.setIcon(pegOrange);
        selectYellow.setIcon(pegYellow);
        selectGreen.setIcon(pegGreen);
        selectBlue.setIcon(pegBlue);
        selectPurple.setIcon(pegPurple);
    }

    public void inicializarBoardContinue() {
            // TODO: Este sería la reconstrucción de una partida, continuar game
            // mod 4
        String[] rounds = controladorPresentacion.getRounds();
        this.controlSecuencia = (rounds.length/2)*4;
        this.foundAnswer = false;
        for (JLabel peg : board) { peg.setIcon(pegBlack); }
        int controlS = 0;
        for (int i = 0; i < rounds.length; i+=2) {
            String guess = rounds[i];
            String answer = rounds[i + 1];
            setColorGuessIni(guess,controlS);
            setColorAnswersIni(answer,controlS);
            controlS += 4;
        }
        deleteAllListenerPeg();
        listenerPegAll();
    }

    public void inicializarBoardReset() {
            // Existe un desfase, porque indirectamente hace make guess...
        this.controlSecuencia = 0;
        this.foundAnswer = false;
        for (JLabel peg : board) { peg.setIcon(pegBlack); }
        deleteAllListenerPeg();
        listenerPegAll();
    }

    public void setColorAnswers(String codeAnswer){
        int j = 0;
        for (int i = 0; i < answers.length; ++i) {
            final JLabel peg = answers[i];
            if (i >= controlSecuencia && i < controlSecuencia+4) {
                char letter = codeAnswer.charAt(j);
                if (letter == 'R') { peg.setIcon(pegRed); }
                else if (letter == 'B') { peg.setIcon(pegWhite); }
                else { peg.setIcon(pegBlack); }
                ++j;
            } else if (i > controlSecuencia + 4) { break; }
        }
    }

    public void setColorGuess(String codeGuess){
        int j = 0;
        for (int i = 0; i < guesses.length; ++i) {
            final JLabel peg = guesses[i];
            if (i >= controlSecuencia && i < controlSecuencia+4) {
                char letter = codeGuess.charAt(j);
                if (letter == 'A') { peg.setIcon(pegGreen); }
                else if (letter == 'B') { peg.setIcon(pegBlue); }
                else if (letter == 'C') { peg.setIcon(pegOrange); }
                else if (letter == 'D') { peg.setIcon(pegRed); }
                else if (letter == 'E') { peg.setIcon(pegPurple); }
                else if (letter == 'F') { peg.setIcon(pegYellow); }
                else { peg.setIcon(pegBlack); }
                ++j;
            } else if (i > controlSecuencia + 4) { break; }
        }
    }

    public void setColorAnswersIni(String codeAnswer, int controlS){
        int j = 0;
        for (int i = 0; i < answers.length; ++i) {
            final JLabel peg = answers[i];
            if (i >= controlS && i < controlS+4) {
                char letter = codeAnswer.charAt(j);
                if (letter == 'R') { peg.setIcon(pegRed); }
                else if (letter == 'B') { peg.setIcon(pegWhite); }
                else { peg.setIcon(pegBlack); }
                ++j;
            } else if (i > controlSecuencia + 4) { break; }
        }
    }

    public void setColorGuessIni(String codeGuess, int controlS){
        int j = 0;
        for (int i = 0; i < guesses.length; ++i) {
            final JLabel peg = guesses[i];
            if (i >= controlS && i < controlS+4) {
                char letter = codeGuess.charAt(j);
                if (letter == 'A') { peg.setIcon(pegGreen); }
                else if (letter == 'B') { peg.setIcon(pegBlue); }
                else if (letter == 'C') { peg.setIcon(pegOrange); }
                else if (letter == 'D') { peg.setIcon(pegRed); }
                else if (letter == 'E') { peg.setIcon(pegPurple); }
                else if (letter == 'F') { peg.setIcon(pegYellow); }
                else { peg.setIcon(pegBlack); }
                ++j;
            } else if (i > controlSecuencia + 4) { break; }
        }
    }

    private boolean checkValidAnswer() {
        int check = (controlSecuencia/4)+1;
        // Controla que no haya huecos vacios (pegs negros)
        switch (check)  {
            case 1: if (g01p1.getIcon() == pegBlack ||
                        g01p2.getIcon() == pegBlack ||
                        g01p3.getIcon() == pegBlack ||
                        g01p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 2: if (g02p1.getIcon() == pegBlack ||
                        g02p2.getIcon() == pegBlack ||
                        g02p3.getIcon() == pegBlack ||
                        g02p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 3: if (g03p1.getIcon() == pegBlack ||
                        g03p2.getIcon() == pegBlack ||
                        g03p3.getIcon() == pegBlack ||
                        g03p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 4: if (g04p1.getIcon() == pegBlack ||
                        g04p2.getIcon() == pegBlack ||
                        g04p3.getIcon() == pegBlack ||
                        g04p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 5: if (g05p1.getIcon() == pegBlack ||
                        g05p2.getIcon() == pegBlack ||
                        g05p3.getIcon() == pegBlack ||
                        g05p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 6: if (g06p1.getIcon() == pegBlack ||
                        g06p2.getIcon() == pegBlack ||
                        g06p3.getIcon() == pegBlack ||
                        g06p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 7: if (g07p1.getIcon() == pegBlack ||
                        g07p2.getIcon() == pegBlack ||
                        g07p3.getIcon() == pegBlack ||
                        g07p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 8: if (g08p1.getIcon() == pegBlack ||
                        g08p2.getIcon() == pegBlack ||
                        g08p3.getIcon() == pegBlack ||
                        g08p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 9: if (g09p1.getIcon() == pegBlack ||
                        g09p2.getIcon() == pegBlack ||
                        g09p3.getIcon() == pegBlack ||
                        g09p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
            case 10: if (g10p1.getIcon() == pegBlack ||
                        g10p2.getIcon() == pegBlack ||
                        g10p3.getIcon() == pegBlack ||
                        g10p4.getIcon() == pegBlack) {
                    invalidGuessLabel.setText("Invalid guess: you must fill all pegs.");
                    return false;
                }
                break;
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
                case 2:
                    if (g02p1.getIcon() == g02p2.getIcon() || g02p1.getIcon() == g02p3.getIcon() ||
                            g02p1.getIcon() == g02p4.getIcon() || g02p2.getIcon() == g02p3.getIcon() ||
                            g02p2.getIcon() == g02p4.getIcon() || g02p3.getIcon() == g02p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 3:
                    if (g03p1.getIcon() == g03p2.getIcon() || g03p1.getIcon() == g03p3.getIcon() ||
                            g03p1.getIcon() == g03p4.getIcon() || g03p2.getIcon() == g03p3.getIcon() ||
                            g03p2.getIcon() == g03p4.getIcon() || g03p3.getIcon() == g03p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 4:
                    if (g04p1.getIcon() == g04p2.getIcon() || g04p1.getIcon() == g04p3.getIcon() ||
                            g04p1.getIcon() == g04p4.getIcon() || g04p2.getIcon() == g04p3.getIcon() ||
                            g04p2.getIcon() == g04p4.getIcon() || g04p3.getIcon() == g04p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 5:
                    if (g05p1.getIcon() == g05p2.getIcon() || g05p1.getIcon() == g05p3.getIcon() ||
                            g05p1.getIcon() == g05p4.getIcon() || g05p2.getIcon() == g05p3.getIcon() ||
                            g05p2.getIcon() == g05p4.getIcon() || g05p3.getIcon() == g05p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 6:
                    if (g06p1.getIcon() == g06p2.getIcon() || g06p1.getIcon() == g06p3.getIcon() ||
                            g06p1.getIcon() == g06p4.getIcon() || g06p2.getIcon() == g06p3.getIcon() ||
                            g06p2.getIcon() == g06p4.getIcon() || g06p3.getIcon() == g06p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 7:
                    if (g07p1.getIcon() == g07p2.getIcon() || g07p1.getIcon() == g07p3.getIcon() ||
                            g07p1.getIcon() == g07p4.getIcon() || g07p2.getIcon() == g07p3.getIcon() ||
                            g07p2.getIcon() == g07p4.getIcon() || g07p3.getIcon() == g07p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 8:
                    if (g08p1.getIcon() == g08p2.getIcon() || g08p1.getIcon() == g08p3.getIcon() ||
                            g08p1.getIcon() == g08p4.getIcon() || g08p2.getIcon() == g08p3.getIcon() ||
                            g08p2.getIcon() == g08p4.getIcon() || g08p3.getIcon() == g08p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 9:
                    if (g09p1.getIcon() == g09p2.getIcon() || g09p1.getIcon() == g09p3.getIcon() ||
                            g09p1.getIcon() == g09p4.getIcon() || g09p2.getIcon() == g09p3.getIcon() ||
                            g09p2.getIcon() == g09p4.getIcon() || g09p3.getIcon() == g09p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
                case 10:
                    if (g10p1.getIcon() == g10p2.getIcon() || g10p1.getIcon() == g10p3.getIcon() ||
                            g10p1.getIcon() == g10p4.getIcon() || g10p2.getIcon() == g10p3.getIcon() ||
                            g10p2.getIcon() == g10p4.getIcon() || g10p3.getIcon() == g10p4.getIcon()) {
                        invalidGuessLabel.setText("Invalid guess: repetition is not allowed in this difficulty mode.");
                        return false;
                    }
                    break;
            }
        }
        return true;
    }

    private void asignarListenersComponentes() {

        // Listeners para los botones
        buttonMakeGuess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if ( checkValidAnswer() ) {
                    invalidGuessPanel.setVisible(false);

                    String codeOut = "";
                    for (JLabel peg : guesses) {
                        MouseListener[] mls = peg.getMouseListeners();
                        if (mls != null) {
                            for (MouseListener ml : mls) {
                                codeOut += traductorColorToStringGuess(peg);
                            }
                        }
                    }

                    controladorPresentacion.setGuesstoDominio(codeOut,true);
                    String answerOut = controladorPresentacion.getAnswer();
                    setColorAnswers(answerOut);
                    foundAnswer = answerOut.equals("BBBB");

                    if (controlSecuencia == 36 || foundAnswer) {
                        deleteAllListenerPeg();
                        double time = controladorPresentacion.getTime();
                        int numRound = (controlSecuencia/4)+1;
                        System.out.println("Fin Game - " + time);
                        int found = 2;
                        String messageEnd = "You Lost!";
                        if (foundAnswer) {
                            found = 1;
                            messageEnd = "You Win!";
                        }
                        System.out.println(messageEnd);
                        controladorPresentacion.sincronizacionVistaPrincipalAEndGame(found, time, numRound, codeOut);
                    } else {
                        controlSecuencia += 4;
                        int Level = (controlSecuencia/4)+1;
                        System.out.println("Level: " + Level );
                    }
                    listenerPegAll();
                } else { invalidGuessPanel.setVisible(true); }
            }
        });

        menuitemRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                controladorPresentacion.sincronizacionVistaPrincipalARanking();
            }
        });

        menuitemInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                controladorPresentacion.sincronizacionVistaPrincipalAAbout();
            }
        });

        menuitemReedme.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                controladorPresentacion.sincronizacionVistaPrincipalAHelp();
            }
        });

        menuitemSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                ArrayList<String> codeOutGuess = new ArrayList<String>();
                ArrayList<String> codeOutAnswers = new ArrayList<String>();
                String codePegGuess = "";
                String codePegAnswers = "";
                for (int i = 1; i <= guesses.length; ++i) {
                    JLabel peg1 = guesses[i-1];
                    JLabel peg2 = answers[i-1];
                    codePegGuess += traductorColorToStringGuess(peg1);
                    codePegAnswers += traductorColorToStringAnswer(peg2);
                    if (i % 4 == 0) {
                        if (codePegGuess.equals("nnnn")) { break; }
                        codeOutGuess.add(codePegGuess);
                        codeOutAnswers.add(codePegAnswers);
                        codePegGuess = "";
                        codePegAnswers = "";
                    }
                }
                System.out.println(codeOutGuess+" - "+codeOutAnswers);
                try {
                    controladorPresentacion.stopTime();
                    double time = controladorPresentacion.getTime();
                    int numRound = (controlSecuencia/4)+1;
                    String codeMaker = controladorPresentacion.getcodeMaker();
                    controladorPresentacion.sincronizacionVistaPrincipalAEndGameSave(codeOutGuess,codeOutAnswers,time,numRound,codeMaker);
                }
                catch (IOException e) { e.printStackTrace(); }
            }
        });

        // call .dispose() on cancelButton
        menuitemQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String texto = ((JMenuItem) event.getSource()).getText();
                System.out.println("Has seleccionado el menuitem con texto: " + texto);
                System.exit(0);
            }
        });

        // call .dispose() on ESCAPE
        backgroundPanel.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

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
    }

        // Activa o Desactiva, el cursor para poder leer las secuencias de los Guess Tablero
    private void listenerPegAll() {
        for (int i = 0; i < guesses.length; ++i) {
            final JLabel peg = guesses[i];
            MouseListener ml = myMouseListener(peg);
            if (i >= controlSecuencia && i < controlSecuencia+4) { peg.addMouseListener(ml); }
            else if (i < controlSecuencia) { removeClickListener(peg); }
            else if (i > controlSecuencia+4) { break; }
        }
    }

    private void deleteAllListenerPeg() {
        for (int i = 0; i < guesses.length; ++i) { removeClickListener(guesses[i]); }
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

    private void removeClickListener(JLabel peg) {
        MouseListener[] mls = peg.getMouseListeners();
        if (mls != null) {
            for (MouseListener ml : mls) { peg.removeMouseListener(ml); }
        }
    }

        // Traduce sintaxis peg to código algoritmo to Guess
    private char traductorColorToStringGuess(JLabel peg){
        char outLetter = 'n';
        if (peg.getIcon().toString().equals("g")) { outLetter = 'A'; }
        else if (peg.getIcon().toString().equals("b")) { outLetter = 'B'; }
        else if (peg.getIcon().toString().equals("o")) { outLetter = 'C'; }
        else if (peg.getIcon().toString().equals("r")) { outLetter = 'D'; }
        else if (peg.getIcon().toString().equals("p")) { outLetter = 'E'; }
        else if (peg.getIcon().toString().equals("y")) { outLetter = 'F'; }
        return outLetter;
    }

        // Traduce sintaxis peg to código algoritmo to Answer
    private char traductorColorToStringAnswer(JLabel peg){
        char outLetter = 'n';
        if (peg.getIcon().toString().equals("n")) { outLetter = 'N'; }
        else if (peg.getIcon().toString().equals("r")) { outLetter = 'R'; }
        else if (peg.getIcon().toString().equals("w")) { outLetter = 'B'; }
        return outLetter;
    }

    /*
    public static void main(String[] args) {
        CtrlPresentacion cP = new CtrlPresentacion();
        VistaPrincipal vP = new VistaPrincipal(cP);
        vP.hacerVisible();
        System.exit(0);
    }
    */
}
