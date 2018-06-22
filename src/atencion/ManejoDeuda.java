package atencion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataAccess.DAUsuario;
import modelos.Deuda;
import modelos.Usuario;
import moduloDeudas.ModuloDeuda;

public class ManejoDeuda implements Atencion {
	private Atencion siguiente;

	@Override
	public void establecerSiguiente(Atencion siguiente) {
		this.siguiente = siguiente;
	}

	@Override
	public String atender(String mensaje, String nombreAsistente, String nombreUsuario) {
		final String consulta = mensaje.toLowerCase();
		ModuloDeuda md = new ModuloDeuda();

		String regex = "(@" + nombreAsistente + ") (@\\w+) (?:me debe) ([\\$])\\s*([0-9,\\s]*\\.?[0-9]{0,2})";

		Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			String prestamista = nombreUsuario.replace("@", "");
			String deudor = matcher.group(2).toString().replace("@", "");
			
			double monto = Double.parseDouble(matcher.group(4));

			md.ingresarDeuda(prestamista, deudor, monto);
			
			return nombreUsuario + " anotado.";
		}

		regex = "(@" + nombreAsistente + ") (?:cu[a|á]nto me debe) (@\\w+)[?]";

		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			String deudor = matcher.group(2).replace("@", "");
			String usuario = nombreUsuario.replace("@", "");
			
			double montoDeudor = md.mostrarDeudaImporte(usuario, deudor);
			double montoUsuario = md.mostrarDeudaImporte(deudor, usuario);
			
			DecimalFormat df = new DecimalFormat("###.#");
			
			String strDeudor ="";
			String strUsuario ="";
			
			if(montoDeudor > 0)
				strDeudor += nombreUsuario + " " + matcher.group(2) + " te debe $" + df.format(montoDeudor);
			else
				strDeudor += nombreUsuario + " " + matcher.group(2) + " no te debe nada.";
			
			if(montoUsuario >0) 
				strUsuario += " Vos le debés $" + df.format(montoUsuario);
			
			return strDeudor + strUsuario;
		}

		regex = "(@" + nombreAsistente + ")(?: le debo \\$)([0-9]+,[0-9]+|[0-9]+)(?: a )(@\\w+)";

		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			String prestamista = matcher.group(3).replace("@", "");
			String deudor = nombreUsuario.replace("@", "");
			double monto = Double.parseDouble(matcher.group(2));
			
			md.ingresarDeuda(prestamista, deudor, monto);
			
			return nombreUsuario + " anotado.";
		}

		regex = "(@" + nombreAsistente + ")(?: cu[a|á]l es mi estado de deudas)";

		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);

		if (matcher.find()) {
			String usuario = nombreUsuario.replace("@", "");
			
			List<Deuda> deudas = md.mostrarDeudas(usuario);
			List<Deuda> prestamos = md.mostrarTodosLosPrestamos(usuario);
			
			String salida=nombreUsuario + " ";
			DecimalFormat df = new DecimalFormat("###.#");
			
			for(Deuda deuda: deudas) {
				Usuario prestamista = new DAUsuario().obtenerUsuario(deuda.getPrestamistaID());
				salida+= "le debés $" + df.format(deuda.getImporte()) + " a @" + prestamista.getNombre() + ". ";
			}
			
			for(Deuda prestamo: prestamos) {
				Usuario deudor = new DAUsuario().obtenerUsuario(prestamo.getDeudorID());
				salida+= "@" + deudor.getNombre() + " te debe $" + df.format(prestamo.getImporte()) + ". ";
			}
			
			return salida;
		}
		
		regex = "(@" + nombreAsistente + ") simplificar deudas con (@\\w+) y (@\\w+)";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if (matcher.find()) {
			String usuarioDebe = matcher.group(2).replace("@", "");
			String usuarioPresto = matcher.group(3).replace("@", "");
			String usuario = nombreUsuario.replace("@", "");
			
			md.simplificarDeudas(usuario, usuarioDebe, usuarioPresto);
			
			return nombreUsuario + " bueno.";
		}
		
		regex = "(@" + nombreAsistente + ") (@\\w+) me pag[o|ó] ([\\$])\\s*([0-9,\\\\s]*\\.?[0-9]{0,2})";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if (matcher.find()) {
			String usuario = nombreUsuario.replace("@", "");
			String deudor = matcher.group(2).replace("@","");
			double importe = Double.parseDouble(matcher.group(4));
			
			md.pagarDeuda(usuario, deudor, importe);
			
			return nombreUsuario + " anotado.";
		}
		
		regex = "(@" + nombreAsistente + ") cu[a|á]nto le debo a (@\\w+)?";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if (matcher.find()) {
			String prestamista = matcher.group(2).replace("@","");
			String usuario = nombreUsuario.replace("@", "");
			
			double montoPrestamista = md.mostrarDeudaImporte(prestamista, usuario);
			double montoUsuario = md.mostrarDeudaImporte(usuario, prestamista);
			
			DecimalFormat df = new DecimalFormat("###.#");
			
			String strPrestamista="";
			String strUsuario = "";
			
			if (montoPrestamista > 0)
				strPrestamista += nombreUsuario + " debés $" + df.format(montoPrestamista) + " a @" + prestamista;
			else
				strPrestamista += nombreUsuario + " no le debés nada.";
			
			if (montoUsuario > 0)
				strUsuario += " @" + prestamista  +" te debe $" + df.format(montoUsuario);
			
			return strPrestamista + strUsuario;
		}
		
		regex = "(@" + nombreAsistente + ") le pagu[e|é] a (@\\w+) ([\\$])\\s*([0-9,\\\\s]*\\.?[0-9]{0,2})";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if (matcher.find()) {
			String prestamista = matcher.group(2).replace("@","");
			String usuario = nombreUsuario.replace("@", "");
			double importe = Double.parseDouble(matcher.group(4));
			
			md.pagarDeuda(prestamista, usuario, importe);
			return nombreUsuario + " anotado.";
		}
		
		regex = "(@" + nombreAsistente + ")(?: con )(.+)(?: gastamos \\$)(\\d+[,|.]\\d{0,2}|\\d+)(?: y pag[o|ó] )@(\\w+)";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if(matcher.find()) {
			double importeTotal = Double.parseDouble(matcher.group(3));
			String usuarioPago = matcher.group(4);
			String usuario = nombreUsuario.replace("@", "");
			List<String> usuariosParticipantes = new ArrayList<String>();
			
			final String regex2 = "@\\w+";
			
			final Pattern pattern2 = Pattern.compile(regex2, Pattern.MULTILINE);
			final Matcher matcher2 = pattern2.matcher(matcher.group(2));

			while (matcher2.find()) {
				String u = matcher2.group(0).replace("@","");
				if(!u.contains(usuarioPago))
					usuariosParticipantes.add(u);
			}
			
			double importeIndividual = importeTotal/(usuariosParticipantes.size() + 2);
			
			for(String p : usuariosParticipantes) {
				md.ingresarDeuda(usuarioPago, p, importeIndividual);
			}
			
			md.ingresarDeuda(usuarioPago, usuario, importeIndividual);
			
			return nombreUsuario + " anotado.";
		}
		
		regex = "(@" + nombreAsistente + ")(?: con )(.+)(?: gastamos \\$)(\\d+[,|.]\\d{0,2}|\\d+) y pagu[e|é] yo";
		pattern = Pattern.compile(regex, Pattern.MULTILINE);
		matcher = pattern.matcher(consulta);
		
		if(matcher.find()) {
			double importeTotal = Double.parseDouble(matcher.group(3));
			String usuarioPago = nombreUsuario.replace("@", "");
			List<String> usuariosParticipantes = new ArrayList<String>();
			
			final String regex2 = "@\\w+";
			
			final Pattern pattern2 = Pattern.compile(regex2, Pattern.MULTILINE);
			final Matcher matcher2 = pattern2.matcher(matcher.group(2));

			while (matcher2.find()) {
				String u = matcher2.group(0).replace("@","");
				usuariosParticipantes.add(u);
			}
			
			double importeIndividual = importeTotal/(usuariosParticipantes.size() + 1);
			
			for(String p : usuariosParticipantes) {
				md.ingresarDeuda(usuarioPago, p, importeIndividual);
			}
			
			return nombreUsuario + " anotado.";
		}
		
		return siguiente.atender(mensaje, nombreAsistente, nombreUsuario);
	}
}
