package asistenteTest;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class DatoTemporarioTest {

	private Calendar calendar;
	private String[] meses = { "enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre",
								"octubre", "noviembre", "diciebre" };
	private String[] dias = { "domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado" };

	public final static String USUARIO = "delucas";
	public final static int ELEGIDO = 12;

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
		calendar = Calendar.getInstance();
	}
	
	@Test
	public void hora() {
		String[] mensajes = {
				"¿qué hora es, @jenkins?",
				"@jenkins, la hora por favor",
				"¿me decís la hora @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas son las " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ((calendar.get(Calendar.AM_PM) == Calendar.PM) ? " PM":" AM"),
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void fecha() {
		String[] mensajes = {
				"¿qué día es, @jenkins?",
				"@jenkins, la fecha por favor",
				"¿me decís la fecha @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es " + calendar.get(Calendar.DAY_OF_MONTH) + " de " + meses[calendar.get(Calendar.MONTH)] + " de " + calendar.get(Calendar.YEAR),
					jenkins.escuchar(mensaje) 
			);
		}
	}
	
	@Test
	public void diaDeLaSemana() {
		String[] mensajes = {
				"¿qué día de la semana es hoy, @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es " + dias[calendar.get(Calendar.DAY_OF_WEEK) - 1],
					jenkins.escuchar(mensaje)
			);
		}
	}
	
}