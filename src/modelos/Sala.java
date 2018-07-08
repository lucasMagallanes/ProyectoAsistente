package modelos;

public class Sala {

	private int id;
	private String nombre;
	private String topico;
	private int usuarios; // debe ser List<Usuario>

	public Sala() {
		this.id = 1;
		this.nombre = "nombreSala";
		this.topico = "topicoSala";
	}

	public Sala(int id, String nombre, String topico) {
		this.id = id;
		this.nombre = nombre;
		this.topico = topico;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nombre=" + nombre + ", topico=" + topico + ", usuarios=" + usuarios + "]";
	}
	
}
