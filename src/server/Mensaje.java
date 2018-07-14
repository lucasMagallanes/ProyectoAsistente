package server;

import com.google.gson.*;

public class Mensaje {
	public final static int LOGGEAR = 0;
	public final static int ACTUALIZAR = 1;
	public final static int ACTUALIZAR_SALAS = 2;
	public final static int MENSAJE_PRIVADO = 3;
	public final static int BROADCAST = 4;
	public final static int DESCONECTAR = 5;
	public final static int NUEVA_SALA = 6;
	public final static int USUARIO_EN_USO = 7;
	
	private int tipoMensaje;
	private int sala;
	private String destino;
	private String contenido;
	private String origen;
	
	public Mensaje(String mensaje) {
		System.out.println(mensaje);
		Gson gson = new Gson();
		copy((Mensaje)gson.fromJson(mensaje, Mensaje.class));
	}
	
	public Mensaje() { };
	
	public Mensaje(String contenido, int tipo) {
		if(tipo == ACTUALIZAR) {
			this.origen = "server";
			this.contenido = contenido;
			this.tipoMensaje = tipo;
			this.destino = null;
			this.sala = -1;
		}
		if(tipo == ACTUALIZAR_SALAS) {
			this.origen = "server";
			this.contenido = contenido;
			this.tipoMensaje = tipo;
			this.destino = null;
			this.sala = -1;
		}
	};

	private void copy(Mensaje mensaje) {
		this.tipoMensaje = mensaje.tipoMensaje;
		this.destino = mensaje.destino;
		this.contenido = mensaje.contenido;
		this.sala = mensaje.sala;
		this.origen = mensaje.origen;
	}

	public void setOrigen(String usuario) {
		this.origen = usuario;
	}
	
	public String getDestino() {
		return this.destino;
	}
	
	public void setTipo(int tipo) {
		this.tipoMensaje = tipo;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public int getTipo() {
		return this.tipoMensaje;
	}
	
	String getOrigen() {
		return this.origen;
	}
	
	public String getContenido() {
		return this.contenido;
	}
	
	public int getSala() {
		return sala;
	}
	
	@Override
	public String toString() {
		return this.origen + this.destino + this.sala + this.contenido + this.tipoMensaje;
	}
}
