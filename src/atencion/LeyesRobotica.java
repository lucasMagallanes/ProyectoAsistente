package atencion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LeyesRobotica implements Atencion {

	private Atencion siguiente;
	private String leyes = 
					"1) Un robot no hará daño a un ser humano, ni permitirá con su inacción que sufra daño.\n"+
					"2) Un robot debe cumplir las órdenes dadas por los seres humanos, a excepción de aquellas que entrasen en conflicto con la primera ley\n"+
					"3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la primera o con la segunda ley.";
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String consulta = mensaje.toLowerCase();
		final String regex = "(@" + nombreAsistente + ")(?: cu[á|a]les son | recordame )(las leyes de la rob[o|á]tica)";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(consulta);

		if (matcher.find()){
			
			return mostrarLeyesRobotica(nombreUsuario);
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
	
	public String mostrarLeyesRobotica(String nombreUsuario) {
		String respuesta = nombreUsuario + ", las leyes de la robótica son:\n";

		return respuesta + leyes;
	}

}
