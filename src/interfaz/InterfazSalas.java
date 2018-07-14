package interfaz;

import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cliente.Cliente;
import cliente.Sala;
import server.Mensaje;

import javax.swing.JLabel;

public class InterfazSalas extends Thread {

	private JFrame frame;
	private JTextField mensajePrivado;
	private JTextField textTopico;
	private DefaultListModel<String> salas;
	private DefaultListModel<String> usuariosConectados;
	private Cliente cliente;
	private String nombreUsuario;
	private HashMap<String, InterfazChat> ventanasAbiertas;
	private JList<String> jlUsuariosConectados;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSalas window = new InterfazSalas(null, "");
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
	public InterfazSalas(Cliente cliente, String nombreUsuario) {
		this.cliente = cliente;
		this.nombreUsuario = nombreUsuario;
		this.ventanasAbiertas = new HashMap<String, InterfazChat>();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("Salas");
		frame.setBounds(100, 100, 963, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		salas = new DefaultListModel<String>();

		JList<String> listaSalas = new JList<>(salas);
		listaSalas.setBounds(10, 52, 200, 376);
		frame.getContentPane().add(listaSalas);
		
		usuariosConectados = new DefaultListModel<String>();
		
		JButton btnAbrirSala = new JButton("Abrir");
		btnAbrirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sala = listaSalas.getSelectedIndex() + 1;
				if (sala > 0)
					JOptionPane.showMessageDialog(null, "Sala " + sala);
			}
		});
		btnAbrirSala.setBounds(10, 441, 200, 25);
		frame.getContentPane().add(btnAbrirSala);

		JTextArea mensajesPrivados = new JTextArea();
		mensajesPrivados.setBounds(222, 52, 519, 366);
		frame.getContentPane().add(mensajesPrivados);

		jlUsuariosConectados = new JList<String>(usuariosConectados);
		jlUsuariosConectados.setBounds(753, 52, 180, 414);
		frame.getContentPane().add(jlUsuariosConectados);

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
				int usuario = jlUsuariosConectados.getSelectedIndex() + 1;
				if (usuario > 0) {
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
				if (!textTopico.getText().equals("")) {
					Mensaje msg = new Mensaje();
					msg.setContenido(textTopico.getText());
					msg.setTipo(Mensaje.NUEVA_SALA);
					msg.setOrigen(cliente.getUsuario());
					cliente.enviar(msg);
				}
				// JOptionPane.showMessageDialog(null, "Crear sala con topico " +
				// textTopico.getText() );
			}
		});

		jlUsuariosConectados.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList<String> list = (JList) evt.getSource();
				String usuarioSeleccionado = list.getSelectedValue().toString();
				
				if(!estaVentanaAbierta(usuarioSeleccionado)) {
					InterfazChat ic = new InterfazChat(usuarioSeleccionado, nombreUsuario, cliente);
					ventanasAbiertas.put(usuarioSeleccionado, ic);
				}
					
				//JOptionPane.showMessageDialog(null, usuarioSeleccionado, "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCrear.setBounds(222, 479, 97, 25);
		frame.getContentPane().add(btnCrear);

		frame.setVisible(true);
	}

	private void setComponentes() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while (true) {
			for (Sala sala : cliente.salas.values()) {
				String topico = sala.getTopico();
				if (!salas.contains(topico)) {
					salas.addElement(topico);
				}
			}
			for (String usuario : cliente.usuarios) {
				if (!usuariosConectados.contains(usuario)) {
					usuariosConectados.addElement(usuario);
				}
			}
			usuariosConectados.removeElement(nombreUsuario);
			
			jlUsuariosConectados = new JList<String>(usuariosConectados);

			verMensajesPrivados(cliente.getMensajesPrivados());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	private void verMensajesPrivados(HashMap<String, List<String>> mensajesPrivados) {
		for (Map.Entry<String, List<String>> entry : mensajesPrivados.entrySet()) {
			String usuarioEmisor = entry.getKey();
			

			if (!estaVentanaAbierta(usuarioEmisor)) {
				InterfazChat ic = new InterfazChat(usuarioEmisor,nombreUsuario, cliente);
				ventanasAbiertas.put(entry.getKey(), ic);
			}
			
			InterfazChat ic = ventanasAbiertas.get(usuarioEmisor);
			List<String> mensajes = entry.getValue();
			
			for(String msg : mensajes)
				ic.recibirMensaje(msg);
		}

		cliente.limpiarMensajesPrivados();
	}
	
	private boolean estaVentanaAbierta(String usuario) {
		return ventanasAbiertas.containsKey(usuario);
	}
}
