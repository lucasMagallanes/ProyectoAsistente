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
		String r = engine.eval(expresion).toString();

		// Saco el .0
		return r.indexOf(".") < 0 ? r : r.replaceAll("0*$", "").replaceAll("\\.$", "");
	}

	public String resolverExpresionMatematica(String expresion) throws ScriptException {
		// reemplazo ^ por Math.pow()
		expresion = expresion.replaceAll(" ", "")
				.replaceAll("(\\w+)\\^(\\w+)", "Math.pow($1,$2)");

		String aux[] = expresion.split("%");

		if (aux.length > 1) {
			// Divido el porcentaje por 100 para después poder multiplicarlo por el total.
			String tanto = "(" + aux[0] + "/100) * (";

			String total = aux[1].replace("DE", "");

			expresion = resolver(tanto + total + ")");

			return resolver(expresion);
		}

		return resolver(expresion);
	}
}
