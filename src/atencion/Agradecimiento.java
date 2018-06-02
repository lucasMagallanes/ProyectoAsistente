package atencion;

public class Agradecimiento implements Atencion {

	private Atencion siguiente;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		int encontrado = mensaje.indexOf("GRACIAS");
		if (encontrado != -1) {
			return "No es nada, " + nombreUsuario;
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}

}
