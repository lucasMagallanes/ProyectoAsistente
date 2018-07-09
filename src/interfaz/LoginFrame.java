package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import asistente.Cliente;
import dataAccess.DAUsuario;
import modelos.Usuario;

public class LoginFrame  extends Observable{

	private static final long serialVersionUID = 8674831954259826014L;
	private JPanel contentPane;
	private JTextField usuarioTextField;
	private JPasswordField contraseniaTextField;
	private JFrame frame;
	private JLabel credencialesInvalidasLbl;
	private Usuario usuario;

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
		frame.setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		frame.setResizable(false);
		
		JLabel usuarioLbl = new JLabel("Usuario");
		usuarioLbl.setBounds(155, 41, 55, 15);
		
		JLabel contraseniaLbl = new JLabel("Contrase\u00f1a");
		contraseniaLbl.setBounds(155, 88, 83, 15);
		
		usuarioTextField = new JTextField();
		usuarioTextField.setBounds(154, 57, 145, 19);
		usuarioTextField.setColumns(10);
		
		contraseniaTextField = new JPasswordField();
		contraseniaTextField.setBounds(155, 105, 144, 19);
		contraseniaTextField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(contraseniaLbl);
		contentPane.add(contraseniaTextField);
		contentPane.add(usuarioLbl);
		contentPane.add(usuarioTextField);
		
		JButton loginBoton = new JButton("LOGIN");
		loginBoton.setBounds(165, 164, 126, 32);
		loginBoton.addActionListener(e->abrirVentanaPrincipal());
		contentPane.add(loginBoton);
		
		JButton registroBoton = new JButton("Registrarse");
		registroBoton.setBounds(165, 227, 126, 25);
		registroBoton.addActionListener(e->abrirRegistroFrame());
		contentPane.add(registroBoton);		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(146, 210, 164, 2);
		contentPane.add(separator);
		
		credencialesInvalidasLbl = new JLabel("Las credenciales ingresadas son incorrectas.");
		credencialesInvalidasLbl.setForeground(Color.RED);
		credencialesInvalidasLbl.setBounds(98, 137, 272, 16);
		contentPane.add(credencialesInvalidasLbl);
		
		credencialesInvalidasLbl.setVisible(false);
		
		frame.setVisible(true);
	}

	private void abrirRegistroFrame() {
		RegistroFrame registroFrame = new RegistroFrame();
		registroFrame.setModal(true);
		registroFrame.setVisible(true);
	}
	
	private void abrirVentanaPrincipal() {
		if(validarCredenciales()) {
			PrincipalFrame principalFrame = new PrincipalFrame(usuario);
			principalFrame.setVisible(true);
			frame.dispose();
		} else {
			credencialesInvalidasLbl.setVisible(true);
		}
	}
	
	private boolean validarCredenciales() {
		usuario = new DAUsuario().obtenerUsuarioPorCredenciales(usuarioTextField.getText(), contraseniaTextField.getText());
		return usuario != null;
	}
}
