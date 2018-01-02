package presentation;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;

public class VistaRanking {

		/** Controlador de presentacion **/

	private CtrlPresentacion controladorPresentacion;


		/** Resto de atributos **/
	private JDialog dialogRanking = new JDialog();
	private JPanel panelVistaRanking;
	private JComboBox comboBoxRanking;
	private JButton buttonQuit;
    private JTextArea textAreaRanking;
    private JScrollPane scrollPaneRanking;

        /** Constructora **/

	public VistaRanking(CtrlPresentacion pCtrlPresentacion) {
		this.controladorPresentacion = pCtrlPresentacion;
		inicializarComponentes();
    }

		/** Métodos públicos **/

	public void hacerVisible() {
        asignarListenersComponentes();
        setComboBoxRankingName();
        settextAreaRanking();
		dialogRanking.pack();
		dialogRanking.setVisible(true);
		System.out.println("Estoy Visible - VistaRanking");
	}

	public void hacerInvisible() {
        dialogRanking.pack();
        dialogRanking.setVisible(false);
	}

    public void setComboBoxRankingName() {
        List<String> dataRanking = controladorPresentacion.getNameFileRankings();
        if (dataRanking.size() == 0) { dataRanking.add("..."); } // Value to Default or Empty
        for (String data : dataRanking) { comboBoxRanking.addItem(data); }
    }

        /** Métodos privados **/

	private void inicializarComponentes() {
		inicializarRanking();
    }

	private void inicializarRanking() {
        // TODO: Configurar correctamente, para añadir ScrollBar
			// scrollPaneRanking = new JScrollPane(textAreaRanking);
			// scrollPaneRanking.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			// scrollPaneRanking.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			// panelVistaRanking.setLayout(new BoxLayout(panelVistaRanking, BorderLayout.CENTER));
			// panelVistaRanking.add(scrollPaneRanking);
        dialogRanking.setTitle("Ranking");
		dialogRanking.setContentPane(panelVistaRanking);
		dialogRanking.setModal(true);
        dialogRanking.setMinimumSize(new Dimension(300, 400));
        dialogRanking.setLocationRelativeTo(null); // Centro
		dialogRanking.setResizable(false);
		dialogRanking.getRootPane().setDefaultButton(buttonQuit);
        dialogRanking.addWindowListener(new WindowAdapter() {
            @Override  //User clicked 'X'
            public void windowClosing(WindowEvent arg0) { controladorPresentacion.sincronizacionVistaRankingAPrincipal(); }

            @Override //Window is closed, now you can free resources if you need.
            public void windowClosed(WindowEvent arg0) { }
        });
        textAreaRanking.setEditable(false);
    }

	private void asignarListenersComponentes() {

        comboBoxRanking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            System.out.println("Has clickado comboBoxRanking");
            settextAreaRanking();
            }
        });

			// call .dispose() on cancelButton
		buttonQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
            String texto = ((JButton) event.getSource()).getText();
            System.out.println("Has clickado el boton con texto: " + texto);
            controladorPresentacion.sincronizacionVistaRankingAPrincipal();
			}
		});

			// call .dispose() on ESCAPE
		panelVistaRanking.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            controladorPresentacion.sincronizacionVistaRankingAPrincipal();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

    private void settextAreaRanking() {
        String nameFile = (String) comboBoxRanking.getSelectedItem();
        List<String> dataList = controladorPresentacion.getDataRanking(nameFile);
        if (dataList.size() == 0 ) { textAreaRanking.setText("..."); }
        else {
            char num = '1';
            textAreaRanking.setText("");
            int i = 0;
            while(i < dataList.size() && i < 10){
                String data = dataList.get(i);
                String contentData [] = data.split(" ");
                DecimalFormat decimal = new DecimalFormat("0.000");
                double time = Double.parseDouble(contentData[0]);
                if (num <= '9')textAreaRanking.append(num+".- "+contentData[1]+" "+decimal.format(time) +"\n");
                else  textAreaRanking.append("10.- "+contentData[1]+" "+decimal.format(time) +"\n");
                ++i;
                ++num;
            }
        }
        textAreaRanking.setMargin(new Insets(10,10,10,10));
    }

    /*
	public static void main(String[] args) {
		CtrlPresentacion cP = new CtrlPresentacion();
		VistaRanking vR = new VistaRanking(cP);
		vR.hacerVisible();
		System.exit(0);
	}
	*/
}
