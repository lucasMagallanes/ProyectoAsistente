package imagen;

import java.util.List;

public abstract class PostGetter {
	protected List<Post> posteos;
	
	protected abstract void getListaPosts(); 
	public abstract Post getPost();
}
