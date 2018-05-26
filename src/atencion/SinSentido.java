package atencion;

public class SinSentido implements Atencion {

	private Atencion siguiente;
	public final static String USUARIO = "@delucas";
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje) {
		return "Disculpa... no entiendo el pedido, " + USUARIO + " ¿podrías repetirlo?";
	}

}
