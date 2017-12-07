import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class NewGame extends JFrame {

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public NewGame(CtrlPresentacio ctrl) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("code");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10,10,80,20);
        getContentPane().add(comboBox);
        comboBox.addItem("CodeMaker");
        comboBox.addItem("CodeBreaker");
        comboBox.addItem("MachinevsMachine");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10,10,80,20);
        getContentPane().add(comboBox_1);
        comboBox_1.addItem("Easy");
        comboBox_1.addItem("Medium");
        comboBox_1.addItem("Hard");
		
		JButton btnNewButton = new JButton("NewGame");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String rol = (String)comboBox.getSelectedItem();
				String difficulty = (String)comboBox_1.getSelectedItem();
				try {
					ctrl.questioNewGame(rol,difficulty);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JLabel lblqueRolQuieres = new JLabel("\u00BFQue rol quieres jugar?");
		
		JLabel lblEscogeDificultad = new JLabel("Escoge dificultad");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblqueRolQuieres, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEscogeDificultad))
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(comboBox_1, Alignment.LEADING, 0, 125, Short.MAX_VALUE))
					.addGap(42))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(167)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(168, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblqueRolQuieres))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEscogeDificultad))
					.addGap(28)
					.addComponent(btnNewButton)
					.addContainerGap(50, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
