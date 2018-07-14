package interfaz;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextPane;

import cliente.Cliente;
import server.Mensaje;

public class InterfazChat {

	private JFrame frame;
	private JTextField tfMensaje;
	private String usuarioDestinatario;
	private String usuarioEmisor;
	private Cliente cliente;
	private JTextPane tpChat;
	
	public static void main(String[] args) {
		//InterfazChat ic = new InterfazChat("", "");
	}

	public InterfazChat(String usuarioDestinatario, String usuarioEmisor, Cliente cliente) {
		this.usuarioDestinatario = usuarioDestinatario;
		this.usuarioEmisor = usuarioEmisor;
		this.cliente = cliente;
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Chat con " + usuarioDestinatario);
		frame.setBounds(100, 100, 400, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tfMensaje = new JTextField();
		tfMensaje.setBounds(10, 515, 266, 35);
		frame.getContentPane().add(tfMensaje);
		tfMensaje.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(286, 515, 89, 35);
		frame.getContentPane().add(btnEnviar);

		tpChat = new JTextPane();
		tpChat.setBounds(10, 11, 364, 493);
		frame.getContentPane().add(tpChat);

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfMensaje.getText().trim().equals("")) {
					String textoOriginal = tpChat.getText();
					String mensajeNuevo = usuarioEmisor + ": " + tfMensaje.getText() + "\n";
					tpChat.setText(textoOriginal + mensajeNuevo);
					tfMensaje.setText("");
					
					enviarMensaje(mensajeNuevo);
				}
			}

			private void enviarMensaje(String mensajeNuevo) {
				Mensaje m = new Mensaje();
				
				m.setContenido(mensajeNuevo);
				m.setOrigen(usuarioEmisor);
				m.setDestino(usuarioDestinatario);
				m.setTipo(3);
								
				cliente.enviar(m);
			}
		});

		frame.setVisible(true);
	}
	
	public void recibirMensaje(String mensajeNuevo) {
		String textoOriginal = tpChat.getText();
		tpChat.setText(textoOriginal + mensajeNuevo);
	}
}
