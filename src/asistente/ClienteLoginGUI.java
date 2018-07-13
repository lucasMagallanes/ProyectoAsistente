package asistente;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.awt.event.ActionEvent;

public class ClienteLoginGUI extends Observable {

	private JFrame frame;
	private JTextField usuario;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteLoginGUI window = new ClienteLoginGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ClienteLoginGUI(Cliente motor) {
		addObserver(motor);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Iniciar sesi�n");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false); 	
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsuario.setBounds(12, 71, 64, 16);
		frame.getContentPane().add(lblUsuario);
		
		usuario = new JTextField();
		usuario.setBounds(105, 69, 315, 22);
		frame.getContentPane().add(usuario);
		usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasea.setBounds(12, 125, 85, 16);
		frame.getContentPane().add(lblContrasea);
		
		password = new JPasswordField();
		password.setBounds(105, 123, 315, 22);
		frame.getContentPane().add(password);
		
		JButton btnConectar = new JButton("CONECTAR");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				String usr = usuario.getText();
				String pass = String.valueOf(password.getPassword());
				
				if(!usr.equals("") && !pass.equals("")) {
					setChanged();
					notifyObservers(usr + " " + String.valueOf(pass));
					//frame.dispose();
				} else 
					JOptionPane.showMessageDialog(frame, "Uno de los campos se encuentra vac�o", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		btnConectar.setBounds(160, 187, 97, 25);
		frame.getContentPane().add(btnConectar);
		
		frame.setVisible(true);
	}
	
	public void setVisible(boolean visible) {
		if(visible)
			frame.setVisible(visible);
		else
			frame.dispose();
	}
}
