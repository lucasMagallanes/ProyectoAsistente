package atencion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ChuckNorris implements Atencion {

	private Atencion siguiente;
	private static final String URL_CHUCK_NORRIS= "https://api.chucknorris.io/jokes/random";
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		int encontrado = mensaje.indexOf("CHUCK NORRIS");
		if (encontrado != -1) {
			return "Frase de Chuck Norris: " + obtenerChuckNorrisFact();
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
	
	private String obtenerChuckNorrisFact() {
    	URL chuckUrl = null;
		try {
			chuckUrl = new URL(URL_CHUCK_NORRIS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        URLConnection chuckConnection = null;
		try {
			chuckConnection = chuckUrl.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
        chuckConnection.addRequestProperty("User-Agent", 
        		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        JsonNode rootNode = null;
		try {
			rootNode = new ObjectMapper().readTree(new JsonFactory().createJsonParser(new InputStreamReader(
					chuckConnection.getInputStream())));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonNode rootCondition = rootNode.path("value");
        return rootCondition.getTextValue();
    }

}
