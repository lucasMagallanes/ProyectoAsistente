package modelos;

public class Usuario {
	private int id;
	private String alias;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasenia;
	
	public Usuario() {
		
	}
	
	public Usuario(String alias, String nombre, String apellido, String email, String contrasenia) {
		this.alias = alias;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.contrasenia = contrasenia;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + alias + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", contrasenia=" + contrasenia + "]";
	}
}
