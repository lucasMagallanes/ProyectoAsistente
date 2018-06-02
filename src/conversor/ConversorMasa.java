package conversor;

import java.util.Map;
import java.util.TreeMap;

public class ConversorMasa extends ConversorClass{
	
	private ConversorClass siguiente;
	private Map<String, Double> masa;
	
	public ConversorMasa() {
		masa = new TreeMap<String, Double>();
		masa.put("toneladas"	, 1E+6);
		masa.put("kilos" 		, 1E+3);
		masa.put("hectogramos" 	, 1E+2);
		masa.put("decagramos"	, 1E+1);
		masa.put("gramos"  		, 1E0);
		masa.put("decigramos" 	, 1E-1);
		masa.put("centigramos" 	, 1E-2);
		masa.put("miligramos" 	, 1E-3);
		masa.put("onzas" 		, 28.3495);
		masa.put("libras" 		, 453.5923);
		masa.put("stones" 		, 6350.0);
		masa.put("dracmas"		, 1.7718);
		
	}
	
	public String convertir(String valor, String unidadInicial, String unidadFinal) {
		return cambioDeUnidad(this.masa, Double.parseDouble(valor), unidadInicial, unidadFinal, this.siguiente);
	}

	@Override
	public void setSiguiente(ConversorClass siguiente) {
		this.siguiente = siguiente;		
	}
}
