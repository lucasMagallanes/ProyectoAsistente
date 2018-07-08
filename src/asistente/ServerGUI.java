package asistente;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerGUI extends Observable {

	private JFrame frame;
	private JTextArea eventos;
	private JButton botonConectar;
	private JButton botonDesconectar;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI window = new ServerGUI();
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
	public ServerGUI(Server sv) {
		addObserver(sv);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Servidor");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				setChanged();
				notifyObservers(2);
				frame.dispose();
			}
		});
		frame.setBounds(100, 100, 1051, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		botonConectar = new JButton("Conectar");
		botonConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(1);
				botonConectar.setEnabled(false);
				botonDesconectar.setEnabled(true);
			}
		});
		botonConectar.setBounds(12, 13, 100, 30);
		frame.getContentPane().add(botonConectar);
		
		botonDesconectar = new JButton("Desconectar");
		botonDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(2);
				botonConectar.setEnabled(true);
				botonDesconectar.setEnabled(false);
			}
		});
		botonDesconectar.setBounds(173, 13, 100, 30);
		botonDesconectar.setEnabled(false);
		frame.getContentPane().add(botonDesconectar);
		
		eventos = new JTextArea();
		eventos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		eventos.setBounds(12, 139, 1009, 318);
		eventos.setEditable(false);
		
		JScrollPane barraScroll = new JScrollPane(eventos);
		barraScroll.setBounds(12, 100, 1009, 318);
		frame.getContentPane().add(barraScroll);
		
		JLabel lblEventos = new JLabel("Eventos");
		lblEventos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEventos.setBounds(12, 70, 56, 16);
		frame.getContentPane().add(lblEventos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 60, 1009, 24);
		frame.getContentPane().add(separator);
		
		frame.setVisible(true);
	}
	
	public void imprimirEvento(String evento) {
		Document doc = eventos.getDocument();
		try {
			doc.insertString(doc.getLength(), ("\n" + evento), null);
		} catch (BadLocationException e) {
		}
	}
}


















