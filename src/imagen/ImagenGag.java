package imagen;

public class ImagenGag {
	
	private PostGetter pg;
	
	public ImagenGag() {
		this.pg = new ActualPost();
	}
	
	public ImagenGag(PostGetter pg) {
		this.pg = pg;
	}
	
	public String getPost() {
		Post p = pg.getPost();
		String respuesta = p.getTitulo() + "|" + p.getLink();
		//System.out.println(respuesta);
		
		return respuesta;
	}
	
}
