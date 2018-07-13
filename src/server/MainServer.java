package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MainServer extends Thread {
	
	private ServerSocket server;
	private boolean correr = true;
	
	public MainServer(int puerto) throws IOException {
		server = new ServerSocket(puerto);
	}
	
	@Override
	public void run() {
		Socket cliente;
		ChatHandler.clientes = new HashMap<>();
		ChatHandler.salas = new HashMap<>();
		ChatHandler.numSala = 0;
		while (correr) {
			try {
				cliente = this.server.accept();
				ChatHandler c = new ChatHandler(cliente);
				c.start();
			} catch (IOException e) { }
		}
	}

	public void desconectar() throws IOException {
		correr = false;
		server.close();
	}

}
