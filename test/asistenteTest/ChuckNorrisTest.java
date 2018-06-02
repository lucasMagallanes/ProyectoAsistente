package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class ChuckNorrisTest {

public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}
	
	@Test
	public void fraseChuckNorris() {
		String mensaje = "@jenkins decime una frase de Chuck Norris.";
		Assert.assertTrue(jenkins.escuchar(mensaje).indexOf("Frase de Chuck Norris: ") != -1);
	}

}
