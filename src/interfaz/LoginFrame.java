package interfaz;

import java.awt.EventQueue;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import asistente.Cliente;

import javax.swing.JSeparator;

public class LoginFrame  extends Observable{

	private static final long serialVersionUID = 8674831954259826014L;
	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JPasswordField ContraseniaTextField;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public LoginFrame(Cliente motor) {
		this();
		addObserver(motor);
	}
	
	public LoginFrame() {
		setComponentes();
	}

	private void setComponentes() {
		frame = new JFrame("Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		
		JLabel usuarioLbl = new JLabel("Usuario");
		usuarioLbl.setBounds(155, 41, 55, 15);
		
		JLabel contraseniaLbl = new JLabel("Contrase\u00f1a");
		contraseniaLbl.setBounds(155, 88, 83, 15);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(154, 57, 145, 19);
		usuarioTextField.setColumns(10);
		
		ContraseniaTextField = new JPasswordField();//new JTextField();
		ContraseniaTextField.setBounds(155, 105, 144, 19);
		ContraseniaTextField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(contraseniaLbl);
		contentPane.add(ContraseniaTextField);
		contentPane.add(usuarioLbl);
		contentPane.add(usuarioTextField);
		
		JButton loginBoton = new JButton("LOGIN");
		loginBoton.setBounds(165, 136, 126, 32);
		loginBoton.addActionListener(e->abrirVentanaPrincipal());
		contentPane.add(loginBoton);
		
		JButton registroBoton = new JButton("Registrarse");
		registroBoton.setBounds(165, 205, 126, 25);
		registroBoton.addActionListener(e->abrirRegistroFrame());
		contentPane.add(registroBoton);		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(155, 191, 155, 2);
		contentPane.add(separator);
		
		frame.setVisible(true);
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
