package asistente;

import atencion.Agradecimiento;
import atencion.Atencion;
import atencion.Calculo;
import atencion.CambioDeUnidades;
import atencion.ChuckNorris;
import atencion.Clima;
import atencion.DatoTemporario;
import atencion.Juego;
import atencion.LeyesRobotica;
import atencion.Saludo;
import atencion.SinSentido;

import java.text.Normalizer;

public class Asistente {

	private String nombre;
	private Atencion[] atenciones;

	public final static String USUARIO = "@delucas";

	public Asistente(String nombre) {
		this.nombre = nombre;
		asignarCadenaDeAtencion();
	}
	
	public void asignarCadenaDeAtencion() {
		atenciones = new Atencion[10];
		atenciones[0] = new Saludo();
		atenciones[1] = new Agradecimiento();
		atenciones[2] = new Calculo();
		atenciones[3] = new Juego();
		atenciones[4] = new CambioDeUnidades();
		atenciones[5] = new LeyesRobotica();
		atenciones[6] = new ChuckNorris();
		atenciones[7] = new Clima();
		atenciones[8] = new DatoTemporario();
		atenciones[9] = new SinSentido();
		
		atenciones[0].establecerSiguiente(atenciones[1]);
		atenciones[1].establecerSiguiente(atenciones[2]);
		atenciones[2].establecerSiguiente(atenciones[3]);
		atenciones[3].establecerSiguiente(atenciones[4]);
		atenciones[4].establecerSiguiente(atenciones[5]);
		atenciones[5].establecerSiguiente(atenciones[6]);
		atenciones[6].establecerSiguiente(atenciones[7]);
		atenciones[7].establecerSiguiente(atenciones[8]);
		atenciones[8].establecerSiguiente(atenciones[9]);
	}

	public String escuchar(String mensaje) {
		mensaje = mensaje.toUpperCase();
		mensaje = Normalizer.normalize(mensaje, Normalizer.Form.NFD);
		mensaje = mensaje.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		
		return atenciones[0].atender(mensaje, nombre, USUARIO);
	}
	
	public static void main(String[] args) {
		Asistente asistente = new Asistente("jenkins");
		String respuesta = asistente.escuchar("@jenkins decime una frase de Chuck Norris.");
		System.out.println(respuesta);
		
		respuesta = asistente.escuchar("@jenkins quiero saber el clima de San Justo");
		System.out.println(respuesta);
	}
}
