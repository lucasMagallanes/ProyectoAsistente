package atencion;

public class Saludo implements Atencion {

	private Atencion siguiente;
	private String[] claves = {"HOLA", "BUEN DIA", "BUENAS TARDES", "HEY"};
	public final static String USUARIO = "@delucas";
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
	}

	public String atender(String mensaje) {
		boolean atendido = false;
		String respuesta = null;
		for (int i = 0; i < claves.length; i++){
			int encontrado = mensaje.indexOf(claves[i]);
			if (encontrado != -1) {
				respuesta = "¡Hola, " + USUARIO + "!";
				atendido = true;
			}
		}
		if (atendido) {
			return respuesta;
		} else {
			return siguiente.atender(mensaje);
		}
	}

}
