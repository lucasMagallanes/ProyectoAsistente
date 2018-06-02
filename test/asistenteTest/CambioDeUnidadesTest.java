package asistenteTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;


public class CambioDeUnidadesTest {
	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void unidadesDeMasa() {
		Assert.assertEquals(
				"@delucas 1 kilo equivale a 1000 gramos",
				jenkins.escuchar("@jenkins cuántos gramos son 1 kilo")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 1 kilo",
				jenkins.escuchar("@jenkins cuántos kilos son 1000 gramos")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 35.27 onzas",
				jenkins.escuchar("@jenkins cuántas onzas son 1000 gramos")
			);	
	}
	
	@Test
	public void unidadesDeCapacidad() {
		Assert.assertEquals(
				"@delucas 1 kilolitro equivale a 1000 litros",
				jenkins.escuchar("@jenkins cuántos litros son 1 kilolitro")
			);
		
		Assert.assertEquals(
				"@delucas 1000 litros equivalen a 1 kilolitro",
				jenkins.escuchar("@jenkins cuántos kilolitros son 1000 litros")
			);
		
		Assert.assertEquals(
				"@delucas 1000 pintas equivalen a 124.97 galones",
				jenkins.escuchar("@jenkins cuántos galones son 1000 pintas")
			);	
	}
	
	@Test
	public void unidadesDeLongitud() {
		Assert.assertEquals(
				"@delucas 1 kilometro equivale a 1000 metros",
				jenkins.escuchar("@jenkins cuántos metros son 1 kilometro")
			);
		
		Assert.assertEquals(
				"@delucas 1000 metros equivalen a 1 kilometro",
				jenkins.escuchar("@jenkins cuántos kilometros son 1000 metros")
			);
		
		Assert.assertEquals(
				"@delucas 100 pulgadas equivalen a 254 centimetros",
				jenkins.escuchar("@jenkins cuántos centimetros son 100 pulgadas")
			);	
	}
	
	@Test
	public void unidadesDeTiempo() {
		Assert.assertEquals(
				"@delucas 1 hora equivale a 60 minutos",
				jenkins.escuchar("@jenkins cuántos minutos son 1 hora")
			);
		
		Assert.assertEquals(
				"@delucas 120 minutos equivalen a 2 horas",
				jenkins.escuchar("@jenkins cuántas horas son 120 minutos")
			);
		
		Assert.assertEquals(
				"@delucas 1 hora equivale a 3600 segundos",
				jenkins.escuchar("@jenkins cuántos segundos son 1 hora")
			);	
	}
}
