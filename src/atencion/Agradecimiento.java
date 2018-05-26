package atencion;

public class Agradecimiento implements Atencion {

	private Atencion siguiente;
	public final static String USUARIO = "@delucas";
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje) {
		int encontrado = mensaje.indexOf("GRACIAS");
		if (encontrado != -1) {
			return "No es nada, " + USUARIO;
		} else {
			return siguiente.atender(mensaje);
		}
	}

}
