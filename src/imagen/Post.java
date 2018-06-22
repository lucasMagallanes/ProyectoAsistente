package imagen;

public class Post {
	private String titulo;
	private String link;
	
	public Post(String titulo, String link) {
		this.titulo = titulo;
		this.link = link;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getLink() {
		return link;
	}
	
}
