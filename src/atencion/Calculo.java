package atencion;

import javax.script.ScriptException;

import calculadora.Calculadora;

public class Calculo implements Atencion {
	
	private Atencion siguiente;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
		
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		String clave = "CUANTO ES";
		int encontrado = mensaje.indexOf(clave);
		if (encontrado != -1) {
			String expresion = mensaje.substring(mensaje.indexOf(clave) + clave.length(), mensaje.length());
			try {
				return nombreUsuario + " " + new Calculadora().resolverExpresionMatematica(expresion);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		} else {
			return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
		}
		return nombreUsuario + "no se puede realizar esa operación";
	}
	
}
