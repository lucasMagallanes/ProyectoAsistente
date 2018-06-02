package atencion;

public interface Atencion {

	public void establecerSiguiente(Atencion siguiente);
	public String atender(String mensaje, String nombreAsistente, String nombreUsuario);
	
}
