package conexionApi;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class URLConnectionReader {
    public static void main(String[] args) throws Exception {
        URL yahoo = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
        URLConnection yc = yahoo.openConnection();
        JsonNode rootNode = new ObjectMapper().readTree(new JsonFactory().createJsonParser(new InputStreamReader(
                yc.getInputStream())));
        JsonNode rootCondition = rootNode.path("query").path("results").path("channel").path("item").path("condition");
        Clima clima =  new ObjectMapper().readValue(rootCondition, Clima.class);
        System.out.println(clima.toString());    
    }
}