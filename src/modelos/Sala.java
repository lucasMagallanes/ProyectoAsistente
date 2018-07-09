package modelos;

import java.util.List;

public class Sala {

	private int id;
	private Usuario administrador;
	private String nombre;
	private String topico;
	private List<Usuario> usuarios;

	public Sala() {
		this.id = 1;
		this.administrador = new Usuario();
		this.administrador.setAlias("-");
		this.nombre = "nombreSala";
		this.topico = "topicoSala";
	}

	public Sala(int id,Usuario administrador, String nombre, String topico) {
		this.id = id;
		this.administrador = administrador;
		this.nombre = nombre;
		this.topico = topico;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ",Administrador=" + administrador.getAlias() + ", nombre=" + nombre + ", topico=" + topico + ", usuarios=" + usuarios + "]";
	}
	
}
