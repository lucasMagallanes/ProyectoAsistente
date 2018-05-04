package asistente;

import calculadora.Calculadora;
import juegos.AdivinaNumero;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;

import java.text.Normalizer;

public class Asistente {

	private String nombre;

	private boolean estoyAdivinando = false;
	private int[] range = { 1, 100 };
	private boolean meEstanAdivinando = false;
	private int numeroElegido = 12;
	private int contadorDeIntentos = 0;

	private Map<String, String> saludosMap;
	public final static String USUARIO = "@delucas";

	public Asistente(String nombre) {
		this.nombre = nombre;
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
		saludosMap.put("SENTIDO", "Disculpa... no entiendo el pedido, " + USUARIO + " ¿podrias repetirlo?");
		saludosMap.put("JUGAMOS?", USUARIO + " ¡sale y vale! Pensá un número del 1 al 100");
		saludosMap.put("JUGAMOS? PENSA UN NUMERO DEL 1 AL 100", USUARIO + " ¡listo!");

	}

	public String escuchar(String mensaje) {

		mensaje = mensaje.toUpperCase();
		mensaje = Normalizer.normalize(mensaje, Normalizer.Form.NFD);
		mensaje = mensaje.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

		// Juego de adivinar tu número
		if (estoyAdivinando)
			return Asistente.USUARIO + adivinando(mensaje);
		
		// Juego de adivinar el número del asistente
		if (meEstanAdivinando)
			return Asistente.USUARIO + pensandoNumero(mensaje);

		for (String clave : saludosMap.keySet()) {
			int encontrado = mensaje.indexOf(clave);
			if (encontrado != -1) {

				switch (clave) {

				case "CUANTO ES": {
					String expresion = mensaje.substring(mensaje.indexOf(clave) + clave.length(), mensaje.length());
					try {
						return USUARIO + " " + new Calculadora().resolverExpresionMatematica(expresion);
					} catch (ScriptException e) {
						e.printStackTrace();
					}
				}

				case "JUGAMOS?":
					if (estoyAdivinando)
						break;
					estoyAdivinando = true;
					return saludosMap.get(clave);

				case "JUGAMOS? PENSA UN NUMERO DEL 1 AL 100": {
					meEstanAdivinando = true;
					return saludosMap.get(clave); // return MetodoDeJuego(mensaje);
				}

				default:
					return saludosMap.get(clave);

				}

			}

		}

		return "Disculpa... no entiendo el pedido, " + USUARIO + "�podr�as repetirlo?";
	}

	public String adivinando(String mensaje) {
		if (range[0] == 1 && range[1] == 100 && mensaje.equals("@" + this.nombre.toUpperCase() + " LISTO")) {
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}

		if (mensaje.equals("@" + this.nombre.toUpperCase() + " MAS GRANDE")) {
			range[0] = (range[0] + range[1]) / 2;
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}

		if (mensaje.equals("@" + this.nombre.toUpperCase() + " MAS CHICO")) {
			range[1] = (range[0] + range[1]) / 2;
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}
		if (mensaje.equals("@" + this.nombre.toUpperCase() + " SI!")) {
			range[0] = 1;
			range[1] = 100;
			estoyAdivinando = false;
			return " fue divertido :)";
		}
		return " No te entendí, ¿Es más grande o más chico?";
	}

	public String pensandoNumero(String mensaje) {
		mensaje = mensaje.replaceAll("[^0-9.]", ""); // Regex que remueve todo lo que no sea numero de la cadena.
		int guess = Integer.parseInt(mensaje);
		if (guess == numeroElegido) {
			contadorDeIntentos++;
			estoyAdivinando = false;
			return " ¡si! Adivinaste en " + contadorDeIntentos + " pasos...";
		}
		if ( guess < numeroElegido) {
			contadorDeIntentos++;
			return " más grande";
		}
		
		if (guess > numeroElegido) {
			contadorDeIntentos++;
			return " más chico";
		}
		return " No te entendí, decime un número del 1 al 100";
	}
}
