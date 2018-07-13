package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import server.MainServer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterfazServidor {

	private JFrame frame;
	private MainServer servidor;
	private JButton btnConectar;
	private JButton btnDesconectar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				InterfazServidor window = new InterfazServidor();
				window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazServidor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Servidor");
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(servidor != null) {
					try {
						servidor.desconectar();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frame.setBounds(100, 100, 316, 174);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(29, 14, 59, 17);
		frame.getContentPane().add(lblPuerto);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(29, 55, 68, 14);
		frame.getContentPane().add(lblEstado);
		
		JLabel lblDesconectado = new JLabel("Desconectado");
		lblDesconectado.setBounds(101, 55, 142, 14);
		frame.getContentPane().add(lblDesconectado);
		
		JLabel label = new JLabel("10001");
		label.setBounds(98, 15, 46, 14);
		frame.getContentPane().add(label);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iniciarServidor();
				lblDesconectado.setText("Conectado");
				btnConectar.setEnabled(false);
				btnDesconectar.setEnabled(true);
			}
		});
		btnConectar.setBounds(10, 102, 110, 23);
		frame.getContentPane().add(btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					servidor.desconectar();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				lblDesconectado.setText("Desconectado");
				btnConectar.setEnabled(true);
				btnDesconectar.setEnabled(false);
			}	
		});
		btnDesconectar.setBounds(180, 102, 110, 23);
		btnDesconectar.setEnabled(false);
		frame.getContentPane().add(btnDesconectar);
	}
	
	public void iniciarServidor() {
		try {
			servidor = new MainServer(10001);
			servidor.start();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}


























