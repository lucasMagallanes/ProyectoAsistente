package conversor;

import java.util.Map;
import java.util.TreeMap;

public class ConversorTiempo extends ConversorClass {

	private ConversorClass siguiente;
	private Map<String, Double> tiempo;

	public ConversorTiempo() {
		tiempo = new TreeMap<String, Double>();
		tiempo.put("dias", 86400.0);
		tiempo.put("horas", 3600.0);
		tiempo.put("minutos", 60.0);
		tiempo.put("segundos", 1E0);
		tiempo.put("milisegundos", 1E-3);
		tiempo.put("microsegundos", 1E-6);
		tiempo.put("nanosegundos", 1E-9);
		tiempo.put("picosegundos", 1E-12);

	}

	@Override
	public String convertir(String valor, String unidadInicial, String unidadFinal) {
		return cambioDeUnidad(this.tiempo, Double.parseDouble(valor), unidadInicial, unidadFinal, this.siguiente);
	}

	@Override
	public void setSiguiente(ConversorClass siguiente) {
		this.siguiente = siguiente;
	}
}
