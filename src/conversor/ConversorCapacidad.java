package conversor;

import java.util.Map;
import java.util.TreeMap;

public class ConversorCapacidad extends ConversorClass{
	
	private ConversorClass siguiente;
	private Map<String, Double> capacidad;
	
	public ConversorCapacidad() {
		capacidad = new TreeMap<String, Double>();
		capacidad.put("kilolitros" 	, 1E3);
		capacidad.put("hectolitros" , 1E2);
		capacidad.put("decalitros"	, 1E1 );
		capacidad.put("litros"  	, 1E0);
		capacidad.put("decilitros" 	, 1E-1);
		capacidad.put("centilitros" , 1E-2);
		capacidad.put("mililitros" 	, 1E-3);
		capacidad.put("pintas" 		, 0.473);
		capacidad.put("galones"		, 3.785);
		capacidad.put("barriles"	, 158.987);
		capacidad.put("kilometros cubicos"	, 1E+12);
		capacidad.put("metros cubicos" 		, 1E+3 );
		capacidad.put("decimetros cubicos"	, 1E0 );
		capacidad.put("centimetros cubicos"	, 1E-3 );
		capacidad.put("milimetros cubicos"	, 1E-6 );
		
	}

	@Override
	public String convertir(String valor, String unidadInicial, String unidadFinal) {
		return cambioDeUnidad(this.capacidad, Double.parseDouble(valor), unidadInicial, unidadFinal, this.siguiente);
	}

	@Override
	public void setSiguiente(ConversorClass siguiente) {
		this.siguiente = siguiente;
	}
}
