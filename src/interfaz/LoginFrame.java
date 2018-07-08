package interfaz;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 8674831954259826014L;
	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JTextField ContraseniaTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginFrame() {
		setComponentes();
	}

	private void setComponentes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel usuarioLbl = new JLabel("Usuario");
		usuarioLbl.setBounds(155, 41, 55, 15);
		
		JLabel contraseniaLbl = new JLabel("Contraseña");
		contraseniaLbl.setBounds(155, 88, 83, 15);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(154, 57, 145, 19);
		usuarioTextField.setColumns(10);
		
		ContraseniaTextField = new JTextField();
		ContraseniaTextField.setBounds(155, 105, 144, 19);
		ContraseniaTextField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(contraseniaLbl);
		contentPane.add(ContraseniaTextField);
		contentPane.add(usuarioLbl);
		contentPane.add(usuarioTextField);
		
		JButton loginBtn = new JButton("LOGIN");
		loginBtn.setBounds(165, 136, 126, 32);
		loginBtn.addActionListener(e->abrirVentanaPrincipal());
		contentPane.add(loginBtn);
		
		JButton registroBtn = new JButton("Registrarse");
		registroBtn.setBounds(165, 205, 126, 25);
		registroBtn.addActionListener(e->abrirRegistroFrame());
		contentPane.add(registroBtn);		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(155, 191, 155, 2);
		contentPane.add(separator);
	}

	private void abrirRegistroFrame() {
		RegistroFrame registroFrame = new RegistroFrame();
		registroFrame.setVisible(true);
	}
	
	private void abrirVentanaPrincipal() {
		if(validarCredenciales()) {
			PrincipalFrame principalFrame = new PrincipalFrame();
			principalFrame.setVisible(true);
		}
	}
	
	private boolean validarCredenciales() {
		return true;
	}
}
