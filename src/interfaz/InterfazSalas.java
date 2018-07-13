package interfaz;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import javax.swing.JLabel;

public class InterfazSalas {

	private JFrame frame;
	private JTextField mensajePrivado;
	private JTextField textTopico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSalas window = new InterfazSalas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazSalas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		DefaultListModel<String> dlmSalas = new DefaultListModel<String>();
		dlmSalas.addElement("Topico 1");
		
		
		JList<String> listaSalas = new JList<>(dlmSalas);
		listaSalas.setBounds(10, 52, 200, 376);
		frame.getContentPane().add(listaSalas);
		
		JButton btnAbrirSala = new JButton("Abrir");
		btnAbrirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sala = listaSalas.getSelectedIndex() + 1;
				if( sala > 0 )
					JOptionPane.showMessageDialog(null, "Sala " + sala);
			}
		});
		btnAbrirSala.setBounds(10, 441, 200, 25);
		frame.getContentPane().add(btnAbrirSala);
		
		JTextArea mensajesPrivados = new JTextArea();
		mensajesPrivados.setBounds(222, 52, 519, 366);
		frame.getContentPane().add(mensajesPrivados);
		
		DefaultListModel<String> dlmUsuarios = new DefaultListModel<String>();
		dlmUsuarios.addElement("Usuario 1");
		
		JList<String> listaUsuarios = new JList<String>(dlmUsuarios);
		listaUsuarios.setBounds(753, 52, 180, 414);
		frame.getContentPane().add(listaUsuarios);
		
		mensajePrivado = new JTextField();
		mensajePrivado.setBounds(222, 429, 410, 37);
		frame.getContentPane().add(mensajePrivado);
		mensajePrivado.setColumns(10);
		
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setBounds(10, 13, 56, 16);
		frame.getContentPane().add(lblSalas);
		
		JLabel lblUsuariosConectados = new JLabel("Usuarios Conectados");
		lblUsuariosConectados.setBounds(753, 13, 180, 16);
		frame.getContentPane().add(lblUsuariosConectados);
		
		JLabel lblMensajesPrivados = new JLabel("Mensajes Privados");
		lblMensajesPrivados.setBounds(222, 13, 519, 16);
		frame.getContentPane().add(lblMensajesPrivados);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int usuario = listaUsuarios.getSelectedIndex() + 1;
				if( usuario > 0 ) {
					JOptionPane.showMessageDialog(null, mensajePrivado.getText() + " al Usuario " + usuario);
				}
			}
		});
		btnEnviar.setBounds(644, 429, 97, 37);
		frame.getContentPane().add(btnEnviar);
		
		JLabel lblTopico = new JLabel("Topico:");
		lblTopico.setBounds(10, 479, 56, 16);
		frame.getContentPane().add(lblTopico);
		
		textTopico = new JTextField();
		textTopico.setBounds(62, 479, 148, 22);
		frame.getContentPane().add(textTopico);
		textTopico.setColumns(10);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Crear sala con topico " + textTopico.getText() );
			}
		});
		btnCrear.setBounds(222, 479, 97, 25);
		frame.getContentPane().add(btnCrear);
		
		frame.setVisible(true);
	}
}
