package asistenteTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;
import atencion.ChuckNorris;

public class ChuckNorrisTest {

public final static String USUARIO = "delucas"; 
	
	Asistente jenkins;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
		ChuckNorris.getSingletonInstance().setModoTest();
	}
	
	@Test
	public void fraseChuckNorris() {
		String mensaje = "@jenkins decime una frase de Chuck Norris.";
		Assert.assertEquals("Frase de Chuck Norris: Chuck mató dos tiros de un solo pájaro.",
				jenkins.escuchar(mensaje));
	}

}
