package imagen;

import java.util.HashMap;

public class Meme {

	private HashMap<String, String> listaDeMemes;
	
	public Meme() {
		listaDeMemes = new HashMap<String, String>();
		listaDeMemes.put("its a trap", "memes/itsatrap.jpg");		
		listaDeMemes.put("take my money", "memes/takemymoney.jpg");
	}
	
	public String getRuta(String memeAMostrar) {
		String ruta = listaDeMemes.get(memeAMostrar);
		return ruta;
	}	
}
 