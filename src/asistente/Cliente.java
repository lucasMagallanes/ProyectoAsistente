package asistente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Cliente implements Observer {
	
	private List<Thread> listaThreads = new LinkedList<>();
	private Socket socketCliente;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private String ip;
	private int puerto;
	
	@SuppressWarnings("unused")
//	private LoginFrame login;
	private ClienteLoginGUI login;
	private ClienteGUI chat;
	private boolean mensajesALeer;
	private boolean abrirChat;
	//private String usuario;
	@SuppressWarnings("unused")
	//private String password;
	Credencial credenciales;
	
	
	public Cliente(String ip, int puerto) {
		this.ip = ip;
		this.puerto = puerto;
		this.mensajesALeer = false;
//		this.login = new LoginFrame(this);
		this.login = new ClienteLoginGUI(this);
		this.abrirChat = false;
		Thread hiloCliente = new Thread(this.new Conectar(this));
		hiloCliente.start();
	}
	
	public void notificar() {
		this.mensajesALeer = true;
	}
	
	public boolean mensajeDisponible() {
		return this.mensajesALeer;
	}
	
	/*public String getUsuario() {
		return this.usuario;
	}*/

	private void Salir() {
		for(Thread t : listaThreads) 
			t.interrupt();
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		String[] datos = ((String)arg1).split(" ");
		//System.out.println(datos[0] + " - " + datos[1]);
		//this.usuario = datos[0];
		//this.password = datos[1];
		credenciales = new Credencial(datos[0], datos[1]);
		if (!validarCredenciales(credenciales)) {
			JOptionPane.showMessageDialog(null, "Credenciales invalidas");
		} else {
			login.setVisible(false);
			abrirChat = true;
		}
		
		//this.chat = new ClienteGUI(this, this.usuario);
		
		/** Conecto los clientes al servidor **/
		//Thread hiloCliente = new Thread(this.new Conectar());
		//hiloCliente.start();
	}
	
	public boolean validarCredenciales(Credencial credenciales) {
		try {
			out = new ObjectOutputStream(socketCliente.getOutputStream());
			out.writeObject(credenciales);
			in = new ObjectInputStream(socketCliente.getInputStream());
			return (Boolean) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return false;
		}
	}
	
	public class Conectar implements Runnable {
		
		Cliente motor;
		
		public Conectar(Cliente motor) {
			this.motor = motor;
		}
		
		@Override
		public void run() {
			try {
				//System.out.println("Cliente creado");
				//Me conecto al cliente, usando el localhost:puertoSV
				socketCliente = new Socket(ip, puerto);
				//in = new ObjectInputStream(socketCliente.getInputStream());
				//out = new ObjectOutputStream(socketCliente.getOutputStream());
				
				
				//En cada cliente creo e inicio un thread para escribir al sv
				Escribir esc = new Escribir(socketCliente, credenciales.getUser());
				Thread esc_thread = new Thread(esc);
				listaThreads.add(esc_thread);
				esc_thread.start();
				
				//En cada cliente creo e inicio un thread para leer lo que viene del sv
				Leer leer = new Leer(socketCliente);
				Thread leer_thread = new Thread(leer);
				listaThreads.add(leer_thread);
				leer_thread.start();
				
				login.setVisible(true);
				while(!abrirChat) {
					Thread.sleep(100);
				}
				chat = new ClienteGUI(motor, credenciales.getUser());
				
			} catch(IOException | InterruptedException e) {
				chat.imprimir("Error al conectar al servidor y/o inicializar los threads");
			}
		}
	}
	
	public class Leer implements Runnable {

		
		private String msj;
		
		Leer(Socket socketCliente) throws IOException {
			//Inicializo el flujo de entrada del socket
			
		}
		
		@Override
		public void run() {
			do {
				if(in != null) {
					try {
						msj = (String) in.readObject();
					} catch (IOException | ClassNotFoundException e) {
						chat.imprimir("Error al leer. Puede que el cliente se haya desconectado");
					}
				} 
				
				if(!msj.equals("Salir")) 
					chat.imprimir(msj);
				else 
					in = null;
			} while(in != null && !Thread.interrupted());
		}
	}

	public class Escribir implements Runnable {

		
		private String usuario;
		//private Scanner teclado;
		
		Escribir(Socket socketCliente, String usuario) throws IOException {
			//Inicializo el flujo de salida del socket
			
			this.usuario = usuario;
		}
		
		@Override
		public void run() {
			
			String str = null;
			
			do {
				if(mensajeDisponible() == true) {
					str = "" + this.usuario + ": " + chat.getMensaje();
					mensajesALeer = false;
					
					try {
						out.writeObject(str);
						out.flush();
					} catch (IOException e) {
						chat.imprimir("Error al escribir en el servidor");
					}
				}
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} while((str == null || !str.equals("Salir")) && !Thread.interrupted());
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("config/configc.txt"));
		
		/** Creo los clientes **/
		Cliente cliente = new Cliente(sc.nextLine(), Integer.parseInt(sc.nextLine()));
		
		/*
		//Conecto los clientes al servidor
		Thread cl1_con = new Thread(cl1.new Conectar());
		cl1_con.start();*/
		
		sc.close();
	}

}
