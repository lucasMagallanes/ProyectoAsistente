package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class GagTest { 
	
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void imagenConRutaInexistente() {
		Assert.assertEquals("Disculpa... no entiendo el pedido, @delucas ¿podrías repetirlo?", jenkins.escuchar("@jenkins cocina"));
	}

	@Test
	public void queElMockFunciona() {
		Assert.assertEquals("Arguments over. House has settled it|img/a0KO8QL_460s.jpg", jenkins.escuchar("@jenkins inserta una imagen de 9gag"));
	}
}
