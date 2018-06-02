package atencion;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Clima implements Atencion {

	private Atencion siguiente;
//	private String[] clavesConCiudad = {"CLIMA DE", "TEMPERATURA HACE EN"};
	private String[] clavesConUbicacionActual = {"CLIMA", "TEMPERATURA HACE"};
	private static final String URL_YAHOO = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20"
			+ "(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22"+ "ciudadABuscar" +"%22)&format=json&env=store%3A%2F%2Fdatatables.org%2"
			+ "Falltableswithkeys"; 
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String consulta = mensaje.toLowerCase();
		final String regex = "(@" + nombreAsistente + ")(?: quiero saber el clima de )(\\w+ \\w+ \\w+|\\w+ \\w+|\\w+)";
		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			
			return obtenerClimaPorCiudad(matcher.group(2), nombreUsuario);
		}
		
		
		
//		for (int i = 0; i < clavesConCiudad.length; i++){
//			int encontrado = mensaje.indexOf(clavesConCiudad[i]);
//			if (encontrado != -1) {
//				return obtenerClimaPorCiudad(, nombreUsuario);
//			}
//		}
		for (int i = 0; i < clavesConUbicacionActual.length; i++){
			int encontrado = mensaje.indexOf(clavesConUbicacionActual[i]);
			if (encontrado != -1) {
				return obtenerClimaPorIp(nombreUsuario);
			}
		}
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
	
    private String ciudadPorIp() throws JsonParseException, JsonProcessingException, IOException {
    	URL ipapi = new URL("https://ipapi.co/json");
        URLConnection ic = ipapi.openConnection();
        JsonNode rootNode = new ObjectMapper().readTree(new JsonFactory().createJsonParser(new InputStreamReader(
                ic.getInputStream())));
        System.out.println(rootNode.path("city").getTextValue());
        return rootNode.path("city").getTextValue(); 
    }
    
    private String obtenerClimaPorIp(String nombreUsuario){
    	String ciudad = null;
		try {
			ciudad = ciudadPorIp();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	URL yahoo = null;
		try {
			yahoo = new URL(URL_YAHOO.replace("ciudadABuscar", ciudad.replaceAll(" ", "%20")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        URLConnection yc = null;
		try {
			yc = yahoo.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonNode rootNode = null;
		try {
			rootNode = new ObjectMapper().readTree(new JsonFactory().createJsonParser(new InputStreamReader(
			        yc.getInputStream())));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonNode rootCondition = rootNode.path("query").path("results").path("channel").path("item").path("condition");
        return getTemperatura(rootCondition, nombreUsuario);
    }
    
    private String obtenerClimaPorCiudad(String ciudad, String nombreUsuario){
    	URL yahoo = null;
		try {
			yahoo = new URL(URL_YAHOO.replace("ciudadABuscar", ciudad.replaceAll(" ", "%20")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
        URLConnection yc = null;
		try {
			yc = yahoo.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonNode rootNode = null;
		try {
			rootNode = new ObjectMapper().readTree(new JsonFactory().createJsonParser(new InputStreamReader(
			        yc.getInputStream())));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        JsonNode rootCondition = rootNode.path("query").path("results").path("channel").path("item").path("condition");
        return getTemperatura(rootCondition, nombreUsuario);
    }
    
    private String getTemperatura(JsonNode rootNode, String nombreUsuario) {
    	JsonNode rootCondition = rootNode.path("temp");
        String temperatura = rootCondition.getTextValue();
        if(temperatura != null) {
	        temperatura = String.valueOf((((Integer.parseInt(temperatura)-32) * 5) / 9));
	        return nombreUsuario + " la temperatura es de: " + temperatura + " grados.";
        }
        return nombreUsuario + " no se encontró la ciudad.";
    }

}
