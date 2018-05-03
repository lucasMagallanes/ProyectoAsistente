package calculadora;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculadora {

	ScriptEngineManager mgr;
	ScriptEngine engine;
	
	public Calculadora() {
		mgr = new ScriptEngineManager();
		engine = mgr.getEngineByName("JavaScript");
	}
	
	
	private String resolver(String expresion) throws ScriptException {
		String result = engine.eval(expresion).toString();
		//Saco el .0
		return result.indexOf(".") < 0 ? result : result.replaceAll("0*$", "")
				.replaceAll("\\.$", "");
	}


	public String resolverExpresionMatematica(String expresion) throws ScriptException {
		expresion = expresion.replaceAll(" ", "").replaceAll("(\\w+)\\^(\\w+)", "Math.pow($1,$2)");
		String aux[] = expresion.split("%");
		
		if(aux.length > 1) {
			String tanto = "(" + aux[0] + "/100) *";
			
			String total = aux[1].replace("DE", "");
			
			expresion = resolver(tanto + total);
						
			return resolver(expresion);
		}
		
	    return resolver(expresion);
	}
}
