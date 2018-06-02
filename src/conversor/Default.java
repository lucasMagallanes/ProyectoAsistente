package conversor;

public class Default extends ConversorClass{

	@Override
	public String convertir(String valor, String unidadInicial, String unidadFinal) {
		return "Disculpa, no identifique alguna de las unidades";
	}

	@Override
	public void setSiguiente(ConversorClass siguiente) {
		
	}
}
