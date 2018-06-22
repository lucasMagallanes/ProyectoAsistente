package moduloDeudas;
import java.util.List;

import dataAccess.DADeuda;
import dataAccess.DAUsuario;
import modelos.Deuda;
import modelos.Usuario;

public class ModuloDeuda {
	public void ingresarDeuda(String prestamistaNombre, String deudorNombre, double monto) {
		Usuario prestamista = new DAUsuario().obtenerUsuario(prestamistaNombre);
		Usuario deudor = new DAUsuario().obtenerUsuario(deudorNombre);
		
		Deuda deuda = new Deuda();
		
		deuda.setDeudorID(deudor.getId());
		deuda.setPrestamistaID(prestamista.getId());
		deuda.setImporte(monto);
		
		DADeuda dad = new DADeuda();
		dad.ingresarDeuda(deuda);
	}
	
	public Double mostrarDeudaImporte(String prestamistaNombre, String deudorNombre) {
		List<Deuda> listaDeudas = this.mostrarDeudas(prestamistaNombre, deudorNombre);
		
		Double montoTotal = 0.0;
		for(Deuda d:listaDeudas) {
			montoTotal +=d.getImporte();
		}
		
		return montoTotal;
	}
	
	public List<Deuda> mostrarDeudas(String prestamistaNombre, String deudorNombre){
		Usuario deudor =new DAUsuario().obtenerUsuario(deudorNombre);
		Usuario prestamista =new DAUsuario().obtenerUsuario(prestamistaNombre);
				
		return new DADeuda().mostrarDeudas(prestamista.getId(),deudor.getId());
	}
	
	public List<Deuda> mostrarDeudas(String deudorNombre){
		Usuario deudor = new DAUsuario().obtenerUsuario(deudorNombre);
		
		List<Deuda> listaDeudas = new DADeuda().listarDeudas(deudor.getId());
		
		return listaDeudas;
	}
	
	public List<Deuda> mostrarTodosLosPrestamos(String prestamistaNombre){
		Usuario prestamista = new DAUsuario().obtenerUsuario(prestamistaNombre);
		
		List<Deuda> prestamos = new DADeuda().listarPrestamosRealizados(prestamista.getId());
		
		return prestamos;
	}
	
	public void simplificarDeudas(String usuario, String deudorNombre, String prestamistaNombre) {
		Usuario usr = new DAUsuario().obtenerUsuario(usuario);
		Usuario deudor = new DAUsuario().obtenerUsuario(deudorNombre);
		Usuario prestamista = new DAUsuario().obtenerUsuario(prestamistaNombre);
		
		Deuda loQueDebe = new DADeuda().mostrarDeudas(prestamista.getId(), usr.getId()).get(0);
		Deuda loQuePresto = new DADeuda().mostrarDeudas(usr.getId(), deudor.getId()).get(0);
		
		Double importeLoQueDebe = loQueDebe.getImporte();
		Double importeLoQuePresto = loQuePresto.getImporte();
		
		DADeuda da = new DADeuda();
		
		if (importeLoQueDebe > importeLoQuePresto) {
			loQueDebe.setImporte(importeLoQueDebe - importeLoQuePresto);
			
			
			
			da.actualizarDeuda(loQueDebe);
			da.eliminarDeuda(loQuePresto);
		} else if (importeLoQuePresto > importeLoQueDebe) {
			loQuePresto.setImporte(importeLoQuePresto - importeLoQueDebe);
				
			da.actualizarDeuda(loQuePresto);
			da.eliminarDeuda(loQueDebe);
		} else {
			da.eliminarDeuda(loQuePresto);
			da.eliminarDeuda(loQueDebe);
		}
	}
	
	public void pagarDeuda(String prestamistaNombre, String deudorNombre, double importePagado) {
		List<Deuda> deudas = mostrarDeudas(prestamistaNombre, deudorNombre);
		Deuda deuda = deudas.get(0);
		
		double loQueDebe = deuda.getImporte();
		DADeuda da = new DADeuda();
		
		if(loQueDebe > importePagado) {
			deuda.setImporte(loQueDebe - importePagado);
			
			da.actualizarDeuda(deuda);
		} else if (importePagado > loQueDebe) {
			Deuda d = new Deuda();
			d.setDeudorID(new DAUsuario().obtenerUsuario(prestamistaNombre).getId());
			d.setPrestamistaID(new DAUsuario().obtenerUsuario(deudorNombre).getId());
			d.setImporte(importePagado - loQueDebe);
			
			da.eliminarDeuda(deuda);
			da.ingresarDeuda(d);
		} else {
			da.eliminarDeuda(deuda);
		}
	}
}
