package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;
import atencion.Clima;

public class ClimaTest {

public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
		Clima.getSingletonInstance().setModoTest();
	}
	
	@Test
	public void climaConCiudad() {
		String mensaje = "@jenkins quiero saber el clima de San Justo";
		Assert.assertEquals("@delucas la temperatura es de: 32 grados. Está Soleado.", jenkins.escuchar(mensaje));
	}
	
//	@Test
//	public void climaConCiudadInexistente() {
//		String mensaje = "@jenkins quiero saber el clima de lalalala";
//		Assert.assertEquals("@delucas la temperatura es de: 32 grados.", jenkins.escuchar(mensaje));
//	}
	
	@Test
	public void climaConUbicacionActual() {
		String[] mensajes = {
				"@jenkins quiero saber el clima.",
				"@jenkins ¿qué temperatura hace?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals("@delucas la temperatura es de: 32 grados. Está Soleado.", jenkins.escuchar(mensaje));
		}
	}

}
