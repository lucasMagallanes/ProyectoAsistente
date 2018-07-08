package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import asistente.Cliente;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class SalaFrame extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextPane mensajesTextPane;
	private JTextField mensajeTextField;
	private Cliente motor;

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
	public SalaFrame(Cliente motor) {
		this();
		this.motor = motor;
	}
	
	public SalaFrame() {
		setBounds(60, 0, 1306, 768);
		getContentPane().setLayout(null);
		
		mensajesTextPane = new JTextPane();
		mensajesTextPane.setBounds(10, 10, 1200, 604);
		mensajesTextPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane mensajesScrollPane = new JScrollPane(mensajesTextPane);
		mensajesScrollPane.setBounds(10, 10, 1200, 604);
		getContentPane().add(mensajesScrollPane);
		
		mensajeTextField = new JTextField();
		mensajeTextField.setBounds(10, 630, 1090, 22);
		mensajeTextField.setColumns(10);
		getContentPane().add(mensajeTextField);
		
		JButton enviarBtn = new JButton("Enviar");
		enviarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				motor.notificar();
			}
		});
		enviarBtn.setBounds(1108, 630, 100, 25);
		getContentPane().add(enviarBtn);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(28, 47, 636, 280);
//		getContentPane().add(scrollPane);
	}
	
	public void imprimir(String str) {
		if( !str.equals("") ) {
			Document doc = this.mensajesTextPane.getDocument();
				try {
					doc.insertString(doc.getLength(), ("\n" + str), null);
				} catch (BadLocationException evento) {
					evento.printStackTrace();
				}
		}
	}

	public String getMensaje() {
		String msj = this.mensajeTextField.getText();
		imprimir(msj);
		mensajeTextField.setText("");
		
		return msj;
	}
	
	public void imprimirImage(String link) {
		mensajesTextPane.insertIcon(getImagen(link));
	}
	
	public ImageIcon getImagen(String link) {
		ImageIcon img = null;
		
		if( link.contains("http") == true ) {
			try {
				img = new ImageIcon(new URL(link));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		else
			img = new ImageIcon(link);
		
		return img;
	}
}
