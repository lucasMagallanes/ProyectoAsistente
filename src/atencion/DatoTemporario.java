package atencion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import datosTemporarios.DiaDeLaSemana;
import datosTemporarios.Fecha;
import datosTemporarios.HoraYMinutos;

public class DatoTemporario implements Atencion {

	private Atencion siguiente;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String consulta = mensaje.toLowerCase();
		final String regex = "^(.*(dia).*(semana).*)|(.*(hora|dia|fecha).*)$";
		Pattern pattern = Pattern.compile(regex);
		final Matcher matcher = pattern.matcher(consulta);
		
		if (matcher.find()) {
			if(matcher.matches() && matcher.group(5) != null && matcher.group(5).equals("hora"))	{	
				HoraYMinutos hm = new HoraYMinutos();
				return nombreUsuario+ " son las " + hm.getHoraYMinutos();
			}
			
			else if(matcher.matches() && matcher.group(5) != null && (matcher.group(5).equals("fecha") || matcher.group(5).equals("dia"))) {
				Fecha f = new Fecha();
				return nombreUsuario + " hoy es " + f.getDia() + " de " + f.getMes() + " de " + f.getAnio();
			}
			
			else if(matcher.matches() && matcher.group(3) != null) {
				DiaDeLaSemana ds = new DiaDeLaSemana(); 
				return nombreUsuario + " hoy es " + ds.getDiaDeLaSemana();
				}
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}

}
