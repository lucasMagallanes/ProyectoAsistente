package conexionApi;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

public class Clima {

	String code;
	String date;
	String temp;
	String text;
	
	@JsonCreator
	public Clima(@JsonProperty("code") String code, @JsonProperty("date") String date,
			 @JsonProperty("temp") String temp, @JsonProperty("text") String text) {
		this.code = code;
		this.date = date;
		this.temp = temp;
		this.text = text;
	}

	@Override
	public String toString() {
		return "Clima [code=" + code + ", date=" + date + ", temp=" + temp + ", text=" + text + "]";
	}

}
