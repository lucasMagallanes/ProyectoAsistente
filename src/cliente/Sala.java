package cliente;

import server.Mensaje;

public class Sala {
	private int ID;
	private String topico;
	
	public Sala (int sala, String topico) {
		this.ID = sala;
		this.topico = topico;
	}
	
	public int getID() {
		return ID;
	}

	public void recibirMensaje(Mensaje msg) {
		// Imprimir mensaje en el chat
	}
	
	public String getTopico() {
		return topico;
	}
}