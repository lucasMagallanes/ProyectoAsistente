package asistente;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Server implements Observer {

	private List<Thread> listaThreads = new LinkedList<>();
	private HashMap<Socket,ObjectOutputStream> listaClientes;	//Lista de sockets de los clientes
	private ServerSocket listener;	//Socket del sv para escuchar a los nuevos clientes
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private ServerGUI gui;
	
	public Server(int puerto) throws IOException {
		this.listaClientes = new HashMap<Socket,ObjectOutputStream>();
		this.listener = new ServerSocket(puerto);
		this.gui = new ServerGUI(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {				// Evento 1 = conectar, Evento 2 = desconectar
		if((Integer)arg == 1) {
			Thread ts = new Thread(this.new Escuchar());
			listaThreads.add(ts);
			ts.start();
		} else {
			gui.imprimirEvento("Desconectando servidor");
			
			try {
				listener.close();
			} catch (IOException e1) {
				gui.imprimirEvento("Error al desconectar el socket listener");
			}
			
			for(Socket s : listaClientes.keySet()) {
				try {
					listaClientes.get(s).writeObject("Salir");
				} catch (IOException e) {
					gui.imprimirEvento("Error al desconectar al cliente");
				}
			}
			
			for(Thread t : listaThreads)
				t.interrupt();
		}
	}
	
	public class Escuchar implements Runnable {
		
		@Override
		public void run() {	
			gui.imprimirEvento("SERVIDOR CREADO EN EL PUERTO " + listener.getLocalPort());
			try {
				while(true) {
					Socket socketCliente = listener.accept(); 
					gui.imprimirEvento("Conexion de " + socketCliente.getInetAddress() + ":" + socketCliente.getPort());
					in = new ObjectInputStream(socketCliente.getInputStream());	//Inicializo flujo para leer data del cliente
					out = new ObjectOutputStream(socketCliente.getOutputStream());	//Inicializo flujo para escribir data al cliente
					listaClientes.put(socketCliente, out);	//Al cliente aceptado lo agrego en el hashmap
					
					//Thread para leer al cliente (uno por cada cliente)
					LeerCliente lc_thread = new LeerCliente(socketCliente, in, out);
					Thread leer = new Thread(lc_thread);
					listaThreads.add(leer);
					leer.start();
				}
			} catch (IOException e) {
				gui.imprimirEvento(e.getMessage());
			}
		}
	}
	
	public class LeerCliente implements Runnable {
		
		private Socket socketCliente;
		private ObjectInputStream in;			
		private ObjectOutputStream out;	
		private String str;
		private boolean salir;
		
		LeerCliente(Socket cliente, ObjectInputStream in, ObjectOutputStream out) throws IOException {
			this.in = in;
			this.out = out;
			this.socketCliente = cliente;
			this.salir = false;
		}
		
		@Override
		public void run() {
			do {
				try {
					str = (String) in.readObject();
				} catch (IOException | ClassNotFoundException e) {
					salir = true;
				}
				
				if(salir == false) {
					for(Socket cliente : listaClientes.keySet()) {
						if(cliente != socketCliente) {
							try {
								listaClientes.get(cliente).writeObject(str);
							} catch (IOException e) {
								gui.imprimirEvento("Error al reenviar el mensaje al resto de los clientes");
							}
						}
					}
				} else {
					try {
						salir = str.equals("Salir");
					} catch (Exception e) {
						try {
							if(salir == true) {
								in.close();
								out.close();
								socketCliente.close();
								listaClientes.remove(socketCliente);
								return;
							}
						} catch (Exception e2) {
							gui.imprimirEvento("Error al cerrar el socket y los flujos");
							return;
						}
					}
				}
			} while(salir == false && !Thread.interrupted());
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new File("config/configs.txt"));
		
		//Inicio el servidor
		Server servidor = new Server(Integer.parseInt(sc.nextLine()));
//		Thread ts = new Thread(servidor.new Escuchar());
//		ts.start();
		sc.close();
	}

}


