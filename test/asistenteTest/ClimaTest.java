package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class ClimaTest {

public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void climaConCiudad() {
		String mensaje = "@jenkins quiero saber el clima de San Justo";
		Assert.assertTrue(jenkins.escuchar(mensaje).indexOf("@delucas la temperatura es de") != -1);
	}
	
	@Test
	public void climaConCiudadInexistente() {
		String mensaje = "@jenkins quiero saber el clima de lalalala";
		Assert.assertEquals("@delucas no se encontró la ciudad.", jenkins.escuchar(mensaje));
	}
	
	@Test
	public void climaConUbicacionActual() {
		String[] mensajes = {
				"@jenkins quiero saber el clima.",
				"@jenkins ¿qué temperatura hace?"
		};
		for (String mensaje : mensajes) {
			Assert.assertTrue(jenkins.escuchar(mensaje).indexOf("@delucas la temperatura es de") != -1);
		}
	}

}
