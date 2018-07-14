package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import com.google.gson.Gson;

import cliente.Sala;

public class ChatHandler extends Thread {
	public final int ESPERANDO_LOGIN = 0;
	public final int LOGGEADO = 1;

	private Socket cliente;
	private DataInputStream in;
	private DataOutputStream out;
	
	private String usuario;
	private int estado = ESPERANDO_LOGIN;
	public static HashMap<String, ChatHandler> clientes;
	public static HashMap<Integer, Sala> salas;
	public static int numSala;
	private boolean conectado = true;

	public ChatHandler(Socket cliente) throws IOException {
		this.cliente = cliente;
		this.in = new DataInputStream(new BufferedInputStream(this.cliente.getInputStream()));
		this.out = new DataOutputStream(new BufferedOutputStream(this.cliente.getOutputStream()));
	}

	// Escucha mensajes del usuario

	@Override
	public void run() {
		while (conectado) {
			try {
				Mensaje msg = new Mensaje(in.readUTF());
				procesar(msg);
			} catch (IOException e) {
			}
		}
	}

	private void procesar(Mensaje msg) {
		if (this.estado == ESPERANDO_LOGIN) {
			login(msg);
		} else {
			switch (msg.getTipo()) {
			case Mensaje.BROADCAST:
				broadcast(msg);
				break;
			case Mensaje.MENSAJE_PRIVADO:
				mensajePrivado(msg);
				break;
			case Mensaje.DESCONECTAR:
				desconectar(msg);
				break;
			case Mensaje.NUEVA_SALA:
				crearSala(msg);
				break;
			default:
				break;
			}
		}
	}

	private void crearSala(Mensaje msg) {
		salas.putIfAbsent(numSala, new Sala(numSala, msg.getContenido()));
		numSala++;
		actualizarSalas();
	}

	private void actualizarSalas() {
		String contenido = "";
		for (int salaID : salas.keySet()) {
			contenido += salaID + "," + salas.get(salaID) + "\n";
		}
		Mensaje msg = new Mensaje(contenido, Mensaje.ACTUALIZAR_SALAS);
		broadcast(msg);
	}

	private void login(Mensaje msg) {
		String usuarioEntrante = msg.getOrigen();
		for (String usuarioRegistrado : clientes.keySet()) {
			if( usuarioEntrante.equals(usuarioRegistrado)) {
				Mensaje a = new Mensaje();
				a.setContenido("usuario esta en uso");// usuario ya existe
				a.setTipo(Mensaje.USUARIO_EN_USO);
				this.enviar(a);
			}
		}
		System.out.println("Usuario valido");
		enviar(msg);
		estado = LOGGEADO;
		clientes.put(usuarioEntrante, this);
		this.usuario = usuarioEntrante;
		actualizarUsuarios();
	}

	private void actualizarUsuarios() {
		String contenido = "";
		for (String usuario : clientes.keySet())
			contenido += usuario + ",";
		Mensaje msg = new Mensaje(contenido, Mensaje.ACTUALIZAR);
		broadcast(msg);
	}

	private void broadcast(Mensaje msg) {
		for( ChatHandler usuario : clientes.values()) {
			usuario.enviar(msg);
		}
	}

	private void enviar(Mensaje msg){
		try {
			Gson gson = new Gson();
			String mensaje = gson.toJson(msg);
			this.out.writeUTF(mensaje);
			this.out.flush();
		} catch (IOException e) {
		}
	}

	private void mensajePrivado(Mensaje msg) {
		for (ChatHandler manejador : clientes.values()) {
			if (manejador.usuario.equals(msg.getDestino()))
				manejador.enviar(msg);
		}
	}

	private void desconectar(Mensaje msg) {
		clientes.remove(usuario);
		actualizarUsuarios();
		conectado = false;
	}

}