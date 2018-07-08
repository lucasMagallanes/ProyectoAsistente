package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SalaFrame extends JDialog {
	private JTextField mensajeTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SalaFrame dialog = new SalaFrame();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SalaFrame() {
		setBounds(100, 100, 700, 450);
		getContentPane().setLayout(null);
		
		JTextPane chatPanel = new JTextPane();
		chatPanel.setBounds(28, 47, 636, 280);
		getContentPane().add(chatPanel);
		
		mensajeTextField = new JTextField();
		mensajeTextField.setBounds(28, 360, 536, 25);
		getContentPane().add(mensajeTextField);
		mensajeTextField.setColumns(10);
		
		JButton enviarBtn = new JButton("Enviar");
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		enviarBtn.setBounds(576, 360, 88, 25);
		getContentPane().add(enviarBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 47, 636, 280);
		getContentPane().add(scrollPane);
	}
}
