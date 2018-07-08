package modelos;

public class Usuario {
	private int id;
	private String alias;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String nombre) {
		this.alias = nombre;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + alias + "]";
	}
}
