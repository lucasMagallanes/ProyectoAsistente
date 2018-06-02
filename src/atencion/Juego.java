package atencion;

public class Juego implements Atencion {

	private Atencion siguiente;
	private boolean estoyAdivinando = false;
	private int[] range = { 1, 100 };
	private boolean meEstanAdivinando = false;
	private int numeroElegido = 12;
	private int contadorDeIntentos = 0;
	
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;	
	}

	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		// Juego de adivinar tu número
		if (estoyAdivinando)
			return nombreUsuario + adivinando(mensaje, nombreAsistente);
		
		// Juego de adivinar el número del asistente
		if (meEstanAdivinando)
			return nombreUsuario + pensandoNumero(mensaje);
		
		int encontrado = mensaje.indexOf("JUGAMOS? PENSA UN NUMERO DEL 1 AL 100");
		if (encontrado != -1) {
			meEstanAdivinando = true;
			return nombreUsuario + " ¡listo!";
		} 
		encontrado = mensaje.indexOf("JUGAMOS?");
		if (encontrado != -1) {
			estoyAdivinando = true;
			return nombreUsuario + " ¡sale y vale! Pensá un número del 1 al 100";
		} 
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
	
	public String adivinando(String mensaje, String nombreAsistente) {
		if (range[0] == 1 && range[1] == 100 && mensaje.equals("@" + nombreAsistente.toUpperCase() + " LISTO")) {
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}

		if (mensaje.equals("@" + nombreAsistente.toUpperCase() + " MAS GRANDE")) {
			range[0] = (range[0] + range[1]) / 2;
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}

		if (mensaje.equals("@" + nombreAsistente.toUpperCase() + " MAS CHICO")) {
			range[1] = (range[0] + range[1]) / 2;
			return " ¿es el " + (range[0] + range[1]) / 2 + "?";
		}
		if (mensaje.equals("@" + nombreAsistente.toUpperCase() + " SI!")) {
			range[0] = 1;
			range[1] = 100;
			estoyAdivinando = false;
			return " fue divertido :)";
		}
		return " No te entendí, ¿Es más grande o más chico?";
	}

	public String pensandoNumero(String mensaje) {
		mensaje = mensaje.replaceAll("[^0-9.]", ""); // Regex que remueve todo lo que no sea numero de la cadena.
		int guess = Integer.parseInt(mensaje);
		if (guess == numeroElegido) {
			contadorDeIntentos++;
			meEstanAdivinando = false;
			int contadorAux = contadorDeIntentos;
			contadorDeIntentos = 0;
			return " ¡si! Adivinaste en " + contadorAux + " pasos...";
		}
		if ( guess < numeroElegido) {
			contadorDeIntentos++;
			return " más grande";
		}
		
		if (guess > numeroElegido) {
			contadorDeIntentos++;
			return " más chico";
		}
		return " No te entendí, decime un número del " + range[0] +" al " + range[1];
	}

}