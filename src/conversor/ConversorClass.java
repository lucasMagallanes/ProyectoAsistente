package conversor;

import java.util.Map;

public abstract class ConversorClass {

	public abstract void setSiguiente(ConversorClass siguiente);

	public abstract String convertir(String valor, String unidadInicial, String unidadFinal);

	public String cambioDeUnidad(Map<String, Double> unidades, double valor, String unidadInicial, String unidadFinal, ConversorClass siguiente) {
		
		if (unidades.containsKey(unidadInicial) && unidades.containsKey(unidadFinal)) 
			return String.valueOf(valor * (unidades.get(unidadInicial) / unidades.get(unidadFinal)));
		else
			return siguiente.convertir(String.valueOf(valor), unidadInicial, unidadFinal);
	}

}
