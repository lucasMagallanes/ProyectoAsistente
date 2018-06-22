package atencion;

import imagen.ActualPost;
import imagen.ImagenGag;
import imagen.PostMock;

@SuppressWarnings("unused")
public class Imagen9Gag implements Atencion {

	private Atencion siguiente;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;	
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String clave = "9GAG";
		int encontrado = mensaje.indexOf(clave);
		
		if(encontrado != -1) {
			ImagenGag ig = new ImagenGag(new PostMock());
			return ig.getPost();
		} else
			return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}

}
