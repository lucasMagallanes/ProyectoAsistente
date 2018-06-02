package asistenteTest;

import asistente.Asistente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculoTest {
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void calculos() {
		Assert.assertEquals("@delucas 3",
				jenkins.escuchar("@jenkins cuánto es 1 + 2"));

		Assert.assertEquals("@delucas 1", 
				jenkins.escuchar("@jenkins cuánto es 5 - 2 * 2"));

		Assert.assertEquals("@delucas 10", 
				jenkins.escuchar("@jenkins cuánto es 10% de 100"));

		Assert.assertEquals("@delucas 3125",
				jenkins.escuchar("@jenkins cuánto es 5 ^ 5"));

		Assert.assertEquals("@delucas 42", 
				jenkins.escuchar("@jenkins cuánto es 17 + 5 ^ 2"));
		
		Assert.assertEquals("@delucas 10", 
				jenkins.escuchar("@jenkins cuánto es 1 + 2 + 3 + 4"));
		
		Assert.assertEquals("@delucas 0", 
				jenkins.escuchar("@jenkins cuánto es 1 - 1 + 1 - 1"));
		
		Assert.assertEquals("@delucas 9", 
				jenkins.escuchar("@jenkins cuánto es 1+1+1+1+1+1+1+1+1+1*0"));
		
		Assert.assertEquals("@delucas 65", 
				jenkins.escuchar("@jenkins cuánto es 90/2+20"));
		
		Assert.assertEquals("@delucas 3026.4576", 
				jenkins.escuchar("@jenkins cuánto es 30.12% de 10048"));
		
	}

	@Test
	public void calculosCompuestos() {
		Assert.assertEquals("@delucas -6", 
				jenkins.escuchar("@jenkins cuánto es (4-8)*2 + 4 / ( 1 + 1)"));

		Assert.assertEquals("@delucas 29287.36", 
				jenkins.escuchar("@jenkins cuánto es (7.5-45.5)*(-80*963.4)/100"));
		
		Assert.assertEquals("@delucas -2018", 
				jenkins.escuchar("@jenkins cuánto es -68+(((90^2)*4)/16)-4000+25"));
		
		Assert.assertEquals("@delucas -201.8", 
				jenkins.escuchar("@jenkins cuánto es 10% de -68+(((90^2)*4)/16)-4000+25"));
		
		Assert.assertEquals("@delucas 30.7783593753125", 
				jenkins.escuchar("@jenkins cuánto es (2011000004/4000+44540*2200)/20^5"));
		
		Assert.assertEquals("@delucas -5.3375", 
				jenkins.escuchar("@jenkins cuánto es ((((3*2)/4)+7)*(44*-3+5/5+8)-(45/3+7))/(10*20)"));

	}
}
