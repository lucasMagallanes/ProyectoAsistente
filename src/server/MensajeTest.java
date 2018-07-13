package server;

import static org.junit.Assert.*;

import org.junit.Test;

public class MensajeTest {

	@Test
	public void test() {
		String json = "{\"tipoMensaje\":0,\"sala\":0,\"contenido\":\"asd\"}";
		Mensaje mensaje = new Mensaje(json);
		System.out.println(mensaje);
	}

}
