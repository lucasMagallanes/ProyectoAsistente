package asistente;

import java.util.HashMap;
import java.util.Map;
import java.text.Normalizer;

public class Asistente {

	private String nombre;
	private Boolean estoyJugando;
	private Map<String, String> saludosMap;
	public final static String USUARIO = "@delucas"; 
	
	public Asistente(String nombre) {
		this.nombre = nombre;
		this.estoyJugando = false;
		cargarSaludosMap();
	}
	
	public void cargarSaludosMap() {
		
		saludosMap = new HashMap<>();
		saludosMap.put("HOLA", "¡Hola, " + USUARIO + "!");
		saludosMap.put("BUEN DIA", "¡Hola, " + USUARIO + "!");
		saludosMap.put("BUENAS TARDES", "¡Hola, " + USUARIO + "!");
		saludosMap.put("HEY", "¡Hola, " + USUARIO + "!");
		saludosMap.put("GRACIAS", "No es nada, " + USUARIO);
		saludosMap.put("CUANTO ES", USUARIO + " 3");
		saludosMap.put("SENTIDO", "Disculpa... no entiendo el pedido, " + USUARIO + " ¿podrías repetirlo?");
		saludosMap.put("JUGAMOS? PENSA", USUARIO + " ¡sale y vale! Pensá un número del 1 al 100");
		saludosMap.put("JUGAMOS?", USUARIO + " ¡listo!");  //Habría que ver una manera de diferenciar ambos "JUGAMOS"
		
	}
	
	public String escuchar(String mensaje) {
		
		mensaje = mensaje.toUpperCase();
		mensaje = Normalizer.normalize(mensaje, Normalizer.Form.NFD);
		mensaje = mensaje.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		
		if(estoyJugando) {
			//return MetodoDeJuego(mensaje); 
			//setear la variable "estoyJugando" en false cuando el juego finaliza.
		}

		for (String clave : saludosMap.keySet()) {
			int encontrado = mensaje.indexOf(clave);
			if (encontrado != -1) {
				
				switch (clave) {
									
					case "CUANTO ES": return saludosMap.get(clave); //return MetodoQueDevuelveResultado(mensaje);
					
					case "JUGAMOS?":
					case "JUGAMOS? PENSA": 	{
						estoyJugando = true;
						return saludosMap.get(clave); //return MetodoDeJuego(mensaje); 
					}
					
					default : return saludosMap.get(clave);
				
				}
				
			}
			
		}
		
		return "Disculpa... no entiendo el pedido, " + USUARIO + "¿podrías repetirlo?";
	}
	
}
