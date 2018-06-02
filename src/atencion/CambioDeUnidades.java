package atencion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conversor.Conversor;

public class CambioDeUnidades implements Atencion {

	private Atencion siguiente;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String consulta = mensaje.toLowerCase();
		final String regex = "(@" + nombreAsistente + ")(?: cuantos | cuantas )(\\w+|\\w+ \\w+)(?: son )(\\d+) (\\w+ \\w+|\\w+)";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			
			return new Conversor().convertir(matcher.group(3), matcher.group(4), matcher.group(2), nombreUsuario);
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}

}