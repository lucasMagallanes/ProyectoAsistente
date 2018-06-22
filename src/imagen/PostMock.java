package imagen;

import java.util.ArrayList;

public class PostMock extends PostGetter {
	
	public PostMock() {
		posteos = new ArrayList<Post>();
		getListaPosts();
	}
	
	@Override
	protected void getListaPosts() {
		//posteos.add(new Post("Arguments over. House has settled it", "https://img-9gag-fun.9cache.com/photo/a0KO8QL_460s.jpg"));
		posteos.add(new Post("Arguments over. House has settled it", "img/a0KO8QL_460s.jpg"));
	}
	
	@Override
	public Post getPost() {
		return posteos.get(0);
	}

}
