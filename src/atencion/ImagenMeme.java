package atencion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import imagen.Meme;

public class ImagenMeme implements Atencion {
	private Atencion siguiente;
		
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
	}
	
	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String consulta = mensaje.toLowerCase();
		String memeAMostrar;
		final String regex = "(@" + nombreAsistente + ")( meme)* (take my money|its a trap)";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(consulta);

		if (matcher.find()){
			memeAMostrar = matcher.group(3);
			Meme m = new Meme();
			return m.getRuta(memeAMostrar);
		}

		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
}
