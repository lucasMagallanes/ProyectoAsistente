package conversor;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Conversor {

	private ConversorClass primero;

	private ConversorMasa convMasa = new ConversorMasa();
	private ConversorTiempo convTiempo = new ConversorTiempo();
	private ConversorLongitud convLongitud = new ConversorLongitud();
	private ConversorCapacidad convCapacidad = new ConversorCapacidad();

	public Conversor() {
		convCapacidad.setSiguiente(new Default());
		convLongitud.setSiguiente(convCapacidad);
		convTiempo.setSiguiente(convLongitud);
		convMasa.setSiguiente(convTiempo);

		this.primero = convMasa;
	}

	public String convertir(String valor, String unidadInicial, String unidadFinal, String nombreUsuario) {
		String cambio;
		String respuesta = nombreUsuario + " ";

		cambio = this.primero.convertir(valor, plural(unidadInicial), unidadFinal); // unidadInicial tiene que estar en
																					// plural, unidadFinal siempre esta
																					// en plural

		if (cambio.contains(" "))
			return respuesta + cambio; // cambio es "Disculpa, no identifiqué alguna de las unidades"

		respuesta += valor + " " + unidadInicial + (valor.equals("1") ? " equivale a " : " equivalen a ");

		cambio = redondearNumero(cambio); // redondeo el resultado a dos decimales

		return respuesta + cambio + " " + (cambio.equals("1") ? singular(unidadFinal) : unidadFinal);
	}

	String plural(String singular) {
		
		String[] palabras = singular.split(" ");
		
		if( palabras.length > 1 )
			return plural(palabras[0]) + " " + plural(palabras[1]);
		
		if (!singular.endsWith("s") || singular.equals("mes"))
			return !singular.equals("galon") && !singular.equals("barril") && !singular.equals("ston") && !singular.equals("mes") ? singular.concat("s") : singular.concat("es");

		return singular;
	}

	String singular(String plural) {
		if (plural.endsWith("s"))
			return plural.endsWith("es") ? plural.substring(0, plural.length() - 2) : plural.substring(0, plural.length() - 1);

		return plural;
	}

	String redondearNumero(String valor) {

		BigDecimal numero = new BigDecimal(valor);
		BigDecimal parteEntera = new BigDecimal(numero.toBigInteger());
		BigDecimal parteDecimal = numero.remainder(BigDecimal.ONE);

		if (parteDecimal.equals(BigDecimal.valueOf(0.0)) || parteDecimal.equals(BigDecimal.valueOf(0)))
			return parteEntera.toString();
		else {
			String redondeo = numero.setScale(2, RoundingMode.HALF_DOWN).toString();

			if (redondeo.endsWith("0"))
				redondeo = numero.setScale(1, RoundingMode.HALF_DOWN).toString();

			if (redondeo.endsWith("0"))
				redondeo = numero.setScale(0, RoundingMode.HALF_DOWN).toString();

			return redondeo;
		}
	}
}
