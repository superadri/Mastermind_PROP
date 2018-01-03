package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VistaEndGame {

        /** Controlador de presentacion **/

    private CtrlPresentacion controladorPresentacion;

        /** Componentes de la interficie grafica **/
    private JDialog dialogEndGame = new JDialog();
    private JPanel contentPaneForm;
    private JButton buttonShowRanking;
    private JButton buttonNewGame;
    private JLabel labelOutResult;
    public float time;
    public int numRound;

    /** Constructora **/

	public VistaEndGame(CtrlPresentacion pCtrlPresentacion) {
        this.controladorPresentacion = pCtrlPresentacion;
        inicializarComponentes();
        asignarListenersComponentes();
	}

	    /** Métodos públicos **/
	
	public void hacerVisible() {
        dialogEndGame.pack();
        dialogEndGame.setVisible(true);
        System.out.println("Estoy Invisible - VistaEndGame");
	}

    public void hacerInvisible() {
        dialogEndGame.pack();
        dialogEndGame.setVisible(false);
    }

    public void setTextJlableResult(int found){
        if (found == 1) { labelOutResult.setText("You Won! - Time: "+time+", NumRounds: "+numRound); }
        else if (found == 2) { labelOutResult.setText("You Lost!"); }
        else { labelOutResult.setText("Result - Save it!"); }
        labelOutResult.revalidate();
        labelOutResult.repaint();
    }

	    /** Métodos privados **/
	
	private void inicializarComponentes() {
        dialogEndGame.setTitle("Result");
        dialogEndGame.setContentPane(contentPaneForm);
        dialogEndGame.setModal(true);
        dialogEndGame.setMinimumSize(new Dimension(300, 200));
        dialogEndGame.setLocationRelativeTo(null);
        dialogEndGame.setResizable(false);
        dialogEndGame.getRootPane().setDefaultButton(buttonShowRanking);
        dialogEndGame.addWindowListener(new WindowAdapter() {
            @Override //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaEndGameAUser(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });
	}
	
	private void asignarListenersComponentes() {

        buttonNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // dialogEndGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                controladorPresentacion.sincronizacionVistaEndGameAUser();
            }
        });

            // call .dispose() on cancelButton
        buttonShowRanking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // dialogEndGame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            controladorPresentacion.sincronizacionVistaEndGameARanking();
            }
        });

            // call .dispose() on ESCAPE
        contentPaneForm.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            controladorPresentacion.sincronizacionVistaEndGameAUser();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	/*
    public static void main(String[] args) {
            CtrlPresentacion cP = new CtrlPresentacion();
            VistaEndGame dialog = new VistaEndGame(cP);
            dialog.hacerVisible();
            System.exit(0);
    }
    */
}
