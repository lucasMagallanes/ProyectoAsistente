package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import server.Mensaje;

public class Cliente extends Thread {
	public static final int ESPERANDO_LOGIN = 0;
	public static final int LOGGEADO = 1;
	public static final int USUARIO_EN_USO = 2;
	
	boolean escuchando = true; 
	private Socket cliente;
	private DataInputStream in;
	private DataOutputStream out;
	private String usuario;
	private HashMap<Integer, Sala> salas;
	private List<String> usuarios;
	public int estado = ESPERANDO_LOGIN;

	public Cliente(String host, int puerto) throws UnknownHostException, IOException {		
		this.cliente = new Socket(host, puerto);
		this.in = new DataInputStream(new BufferedInputStream(this.cliente.getInputStream()));
		this.out = new DataOutputStream(new BufferedOutputStream(this.cliente.getOutputStream()));
	}

	// Escucha mensajes del servidor
	@Override
	public void run() {

		while (escuchando) {
			try {
				Mensaje msg = new Mensaje(in.readUTF());
				System.out.println("msj escuchado");
				procesar(msg);
			} catch (IOException e) {
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

		}
	}

	private void procesar(Mensaje msg) {
		if (estado == ESPERANDO_LOGIN) {
			login(msg);
		} else {
			switch (msg.getTipo()) {
			case Mensaje.BROADCAST:
				recibirBroadcast(msg);
				break;
			case Mensaje.MENSAJE_PRIVADO:
				mensajePrivado(msg);
				break;
			case Mensaje.DESCONECTAR:
				desconectar(msg);
				break;
			case Mensaje.ACTUALIZAR:
				actualizarUsuarios(msg);
				break;
			case Mensaje.ACTUALIZAR_SALAS:
				actualizarSalas();
				break;
			default:
				break;
			}
		}
	}

	private void actualizarSalas() {
		// Actualizar en la ventana las salas disponibles
		
	}

	private void actualizarUsuarios(Mensaje msg) {
		usuarios = new LinkedList<String>();
		String[] usuariosMensaje = msg.getContenido().split(",");
		for( String usuario : usuariosMensaje) {
			usuarios.add(usuario);
		}
		// Actualizar en la ventan los usuarios conectados
		
	}

	private void recibirBroadcast(Mensaje msg) {
		for(int sala : salas.keySet()) {
			if( msg.getSala() == sala) {
				salas.get(sala).recibirMensaje(msg);
				return;
			}
		}
	}

	private void mensajePrivado(Mensaje msg) {
		// Imprimir en la ventana el mensaje recibido
		
	}

	private void desconectar(Mensaje msg) {
		// desconectado por el servidor
		escuchando = false;
		try {
			cliente.close();
		} catch (IOException e) {
		}
	}

	private void login(Mensaje msg) {
		System.out.println("en login");
		if (msg.getTipo() ==7) {
			this.estado = USUARIO_EN_USO;
		}
		else {
			this.usuario = msg.getContenido();
			estado = LOGGEADO;
			
		}
	}
	
	private void crearSala(String topico) {
		Mensaje msg = new Mensaje();
		msg.setOrigen(usuario);
		msg.setContenido(topico);
		msg.setTipo(Mensaje.NUEVA_SALA);
		enviar(msg);
		
	}

	public void enviar(Mensaje msg) {
		Gson gson = new GsonBuilder().create();
		String mensaje = gson.toJson(msg);
		System.out.println(mensaje);
		try {
			out.writeUTF(mensaje);
			out.flush();
		} catch (IOException e) {
		}
	}
}