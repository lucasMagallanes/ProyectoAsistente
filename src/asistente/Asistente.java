package asistente;

import atencion.Agradecimiento;
import atencion.Atencion;
import atencion.Calculo;
import atencion.CambioDeUnidades;
import atencion.ChuckNorris;
import atencion.Clima;
import atencion.DatoTemporario;
import atencion.Imagen9Gag;
import atencion.ImagenMeme;
import atencion.Juego;
import atencion.LeyesRobotica;
import atencion.Saludo;
import atencion.SinSentido;
import imagen.ClienteGUI;

import java.text.Normalizer;

public class Asistente {

	private String nombre;
	private Atencion[] atenciones;
	private ClienteGUI ventana;

	public final static String USUARIO = "@delucas";

	public Asistente(String nombre) {
		this.nombre = nombre;
		this.ventana = new ClienteGUI("Test");
		asignarCadenaDeAtencion();
	}
	
	public void asignarCadenaDeAtencion() {
		atenciones = new Atencion[12];
		atenciones[0] = new Saludo();
		atenciones[1] = new Agradecimiento();
		atenciones[2] = new Calculo();
		atenciones[3] = new Juego();
		atenciones[4] = new CambioDeUnidades();
		atenciones[5] = new LeyesRobotica();
		atenciones[6] = ChuckNorris.getSingletonInstance();
		atenciones[7] = Clima.getSingletonInstance();
		atenciones[8] = new DatoTemporario();
		atenciones[9] = new Imagen9Gag();
		atenciones[10] = new ImagenMeme();
		atenciones[11] = new SinSentido();
		
		atenciones[0].establecerSiguiente(atenciones[1]);
		atenciones[1].establecerSiguiente(atenciones[2]);
		atenciones[2].establecerSiguiente(atenciones[3]);
		atenciones[3].establecerSiguiente(atenciones[4]);
		atenciones[4].establecerSiguiente(atenciones[5]);
		atenciones[5].establecerSiguiente(atenciones[6]);
		atenciones[6].establecerSiguiente(atenciones[7]);
		atenciones[7].establecerSiguiente(atenciones[8]);
		atenciones[8].establecerSiguiente(atenciones[9]);
		atenciones[9].establecerSiguiente(atenciones[10]);
		atenciones[10].establecerSiguiente(atenciones[11]);
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
		asistente.ventana.imprimir(respuesta);
		
//		Clima.getSingletonInstance().setModoTest();
		respuesta = asistente.escuchar("@jenkins quiero saber el clima de San Justo");
		asistente.ventana.imprimir(respuesta);
		
		respuesta = asistente.escuchar("@jenkins quiero saber el clima de Seattle");
		asistente.ventana.imprimir(respuesta);
		
		respuesta = asistente.escuchar("@jenkins quiero saber el clima de Berlin");
		asistente.ventana.imprimir(respuesta);
		
		respuesta = asistente.escuchar("@jenkins take my money");
		asistente.ventana.imprimirImagen(respuesta);
		
		respuesta = asistente.escuchar("@jenkins quiero una imagen de 9gag");
		String[] respuestas = respuesta.split("\\|");
		asistente.ventana.imprimirImagen(respuestas[1]);
		asistente.ventana.imprimir(respuestas[0]);
	}
}
