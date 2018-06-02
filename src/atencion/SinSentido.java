package atencion;

public class SinSentido implements Atencion {
	
	public void establecerSiguiente(Atencion siguiente) {
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		return "Disculpa... no entiendo el pedido, " + nombreUsuario + " ¿podrías repetirlo?";
	}

}
