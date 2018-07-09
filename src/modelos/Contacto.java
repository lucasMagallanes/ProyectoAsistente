package modelos;

public class Contacto {

	private Usuario usuario;
	private Usuario contacto;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Usuario getContacto() {
		return contacto;
	}
	public void setContacto(Usuario contacto) {
		this.contacto = contacto;
	}
	
	@Override
	public String toString() {
		return "Contacto [Usuario_id=" + this.usuario.getId() + ", contacto_id=" + this.contacto.getId() + "]";
	}
	
}
