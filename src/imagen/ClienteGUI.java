package imagen;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class ClienteGUI {

	private JFrame frame;
	private JTextPane mensajesChat;
	private JTextField escribir;
	//private Cliente motor;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteGUI window = new ClienteGUI("Test");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 * 
	 */
	public ClienteGUI(String usuario) {
		//this.motor = motor;
		initialize(usuario);
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	private void initialize(String usuario) {
		
		frame = new JFrame(usuario);
		frame.setBounds(100, 100, 1150, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false); 							//desactivo el maximizar
		
		mensajesChat = new JTextPane();
		mensajesChat.setLayout(new BoxLayout(mensajesChat, BoxLayout.Y_AXIS));
		mensajesChat.setEditable(false);
		mensajesChat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mensajesChat.setBounds(12, 13, 970, 604);
		
		JScrollPane barraScroll = new JScrollPane(mensajesChat);
		barraScroll.setBounds(12, 13, 970, 604);
		frame.getContentPane().add(barraScroll);
		
		escribir = new JTextField();
		escribir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//motor.notificar();
			}
		});
		escribir.setBounds(12, 630, 868, 22);
		frame.getContentPane().add(escribir);
		escribir.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//motor.notificar();
			}
		});
		btnEnviar.setBounds(892, 629, 90, 25);
		frame.getContentPane().add(btnEnviar);
		
		frame.setVisible(true);
	}
	
	public void imprimir(String str) {
		if(!str.equals("")) {
			Document doc = mensajesChat.getDocument();
			try {
				doc.insertString(doc.getLength(), ("\n" + str), null);
			} catch (BadLocationException e) {
				//e.printStackTrace();
			}
		}
	}
	
	public String getMensaje() {
		String msj = escribir.getText();
		imprimir(msj);
		escribir.setText("");
		
		return msj;
	}
	
	public void imprimirImagen(String link) {
		JLabel label = new JLabel(getImagen(link));
		//mensajesChat.insertIcon(getImagen(link));
		//frame.setVisible(true);
		mensajesChat.insertComponent(label);
	}
	
	private ImageIcon getImagen(String link) {
		ImageIcon img = null;
		
		if(link.contains("http") == true) {
			try {
				img = new ImageIcon(new URL(link));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else 
			img = new ImageIcon(link);

		return img;
	}
}
