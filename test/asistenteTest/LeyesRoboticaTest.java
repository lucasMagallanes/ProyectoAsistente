package asistenteTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;

public class LeyesRoboticaTest {
	public final static String USUARIO = "delucas";
	public final static String LEYES = 
			"@delucas, las leyes de la robótica son:\n"
			+"1) Un robot no hará daño a un ser humano, ni permitirá con su inacción que sufra daño.\n"
			+"2) Un robot debe cumplir las órdenes dadas por los seres humanos, a excepción de aquellas que entrasen en conflicto con la primera ley\n"
			+"3) Un robot debe proteger su propia existencia en la medida en que esta protección no entre en conflicto con la primera o con la segunda ley.";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void leyesRobotica() {
		Assert.assertEquals(LEYES, jenkins.escuchar("@jenkins recordame las leyes de la robótica"));
		
		Assert.assertEquals(LEYES ,jenkins.escuchar("@jenkins cuáles son las leyes de la robótica?"));
	}
	
}
