package asistenteTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistente.Asistente;
import dataAccess.DADeuda;

public class DeudasTest {

	public final static String USUARIO = "delucas";

	Asistente jenkins;
	DADeuda da;
	
	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
		da = new DADeuda();
	}
	
	@After
	public void limpiar() {
		da.eliminarTodo();
	}
	
	@Test
	public void transferenciaDeDeudas() {
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins @juan me debe $50")
			);
		Assert.assertEquals(
				"@delucas @juan te debe $50",
				jenkins.escuchar("@jenkins cuánto me debe @juan?")
			);
		
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins le debo $60 a @maria")
			);
		
		
		Assert.assertEquals(
				"@delucas le debés $60 a @maria. @juan te debe $50. ",
				jenkins.escuchar("@jenkins cual es mi estado de deudas?")
			);
			
		Assert.assertEquals(
				"@delucas bueno.",
				jenkins.escuchar("@jenkins simplificar deudas con @juan y @maria")
			);
		
		Assert.assertEquals(
				"@delucas le debés $10 a @maria. ",
				jenkins.escuchar("@jenkins cual es mi estado de deudas?")
			);
		// por detrás, ahora @juan le debe $50 a @maria. Podría probarse,
		// cambiando el interlocutor del asistente
		da.eliminarTodo();
	}
	
	@Test
	public void deudasGrupalesCasoUno() {
		
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins con @juan y @maria gastamos $300 y pagó @juan")
			);
		
		Assert.assertEquals(
				"@delucas le debés $100 a @juan. ",
				jenkins.escuchar("@jenkins cual es mi estado de deudas?")
			);
		// @maria le debe otros $100 a @juan
		da.eliminarTodo();
	}
	
	@Test
	public void deudasGrupalesCasoDos() {
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins con @juan y @maria gastamos $300 y pagué yo")
			);
		
		Assert.assertEquals(
				"@delucas @juan te debe $100. @maria te debe $100. ",
				jenkins.escuchar("@jenkins cual es mi estado de deudas?")
			);
		da.eliminarTodo();
	}
	
	@Test
	public void deudasSimples() {
		
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins @juan me debe $500")
			);
		Assert.assertEquals(
				"@delucas @juan te debe $500",
				jenkins.escuchar("@jenkins cuánto me debe @juan?")
			);
		
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins @juan me pagó $501")
			);
		
		Assert.assertEquals(
				"@delucas @juan no te debe nada. Vos le debés $1",
				jenkins.escuchar("@jenkins cuánto me debe @juan?")
			);
			
		Assert.assertEquals(
				"@delucas debés $1 a @juan",
				jenkins.escuchar("@jenkins cuánto le debo a @juan?")
			);
		
		Assert.assertEquals(
				"@delucas anotado.",
				jenkins.escuchar("@jenkins le pagué a @juan $10")
			);
		
		Assert.assertEquals(
				"@delucas @juan te debe $9",
				jenkins.escuchar("@jenkins cuánto me debe @juan?")
			);
		
		Assert.assertEquals(
				"@delucas no le debés nada. @juan te debe $9",
				jenkins.escuchar("@jenkins cuánto le debo a @juan?")
			);
		
		da.eliminarTodo();
	}
	
}