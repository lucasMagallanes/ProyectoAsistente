package asistente;

import calculadora.Calculadora;

import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptException;

import atencion.Agradecimiento;
import atencion.Atencion;
import atencion.Calculo;
import atencion.Saludo;
import atencion.SinSentido;

import java.text.Normalizer;

public class Asistente {

	private String nombre;

	private boolean estoyAdivinando = false;
	private int[] range = { 1, 100 };
	private boolean meEstanAdivinando = false;
	private int numeroElegido = 12;
	private int contadorDeIntentos = 0;
	private Atencion[] atenciones;

	private Map<String, String> saludosMap;
	public final static String USUARIO = "@delucas";

	public Asistente(String nombre) {
		this.nombre = nombre;
		asignarCadenaDeAtencion();
	}
	
	public void asignarCadenaDeAtencion() {
		atenciones = new Atencion[4];
		atenciones[0] = new Saludo();
		atenciones[1] = new Agradecimiento();
		atenciones[2] = new Calculo();
		atenciones[3] = new SinSentido();
		
		atenciones[0].establecerSiguiente(atenciones[1]);
		atenciones[1].establecerSiguiente(atenciones[2]);
		atenciones[2].establecerSiguiente(atenciones[3]);
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

		int encontrado = mensaje.indexOf("JUGAMOS? PENSA UN NUMERO DEL 1 AL 100");
		if (encontrado != -1) {
			meEstanAdivinando = true;
			return USUARIO + " ¡listo!";
		}
		encontrado = mensaje.indexOf("JUGAMOS?");
		if (encontrado != -1) {
			estoyAdivinando = true;
			return USUARIO + " ¡sale y vale! Pensá un número del 1 al 100";
		}
		
		return atenciones[0].atender(mensaje);
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
			int contadorAux = contadorDeIntentos;
			contadorDeIntentos = 0;
			return " ¡si! Adivinaste en " + contadorAux + " pasos...";
		}
		if ( guess < numeroElegido) {
			contadorDeIntentos++;
			return " más grande";
		}
		
		if (guess > numeroElegido) {
			contadorDeIntentos++;
			return " más chico";
		}
		return " No te entendá, decime un número del " + range[0] +" al " + range[1];
	}
}
