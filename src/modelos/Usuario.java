package modelos;

import java.util.List;

public class Usuario {
	private int id;
	private String alias;
	private String nombre;
	private String apellido;
	private String email;
	private String contrasenia;
	private List<Contacto> contactos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlias() {
		if(alias==null)
			return "-";
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
	public List<Contacto> getContactos() {
		return contactos;
	}
	public void setContactos(List<Contacto> contactos) {
		this.contactos = contactos;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", alias=" + alias + ", nombre=" + nombre + ", apellido=" + apellido + ", email="
				+ email + ", contrasenia=" + contrasenia + "]";
	}
}
