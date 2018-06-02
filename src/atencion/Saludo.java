package atencion;

public class Saludo implements Atencion {

	private Atencion siguiente;
	private String[] claves = {"HOLA", "BUEN DIA", "BUENAS TARDES", "HEY"};
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		for (int i = 0; i < claves.length; i++){
			int encontrado = mensaje.indexOf(claves[i]);
			if (encontrado != -1) {
				return "¡Hola, " + nombreUsuario + "!";
			}
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}

}
