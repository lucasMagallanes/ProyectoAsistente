package imagen;

import java.util.HashMap;

public class Meme {

	private HashMap<String, String> listaDeMemes;
	
	public Meme() {
		listaDeMemes = new HashMap<String, String>();
		listaDeMemes.put("its a trap", "memes/itsatrap.jpg");		
		listaDeMemes.put("take my money", "memes/takemymoney.jpg");
		listaDeMemes.put("argentina", "memes/argentina.jpg");
		listaDeMemes.put("bad luck brian", "memes/badLuckBrian.jpg");
		listaDeMemes.put("caballero", "memes/caballero.jpg");
		listaDeMemes.put("einstein", "memes/einstein.jpg");
		listaDeMemes.put("estudio", "memes/estudio.jpg");
		listaDeMemes.put("facultad", "memes/facultad.jpg");
		listaDeMemes.put("higuain", "memes/higuain.jpg");
		listaDeMemes.put("macritip", "memes/macri.jpg");
		listaDeMemes.put("ojos", "memes/ojos.jpg");
		listaDeMemes.put("parciales", "memes/parciales.jpg");
		listaDeMemes.put("pensando en otra", "memes/pensandoenotra.jpg");
		listaDeMemes.put("piensa", "memes/piensa.jpg");
		listaDeMemes.put("sampaoli", "memes/sampaoli.jpg");
		listaDeMemes.put("traeme alfajores", "memes/traemealfajores.jpg");
		listaDeMemes.put("facultad", "memes/facultad.jpg");
	}
	
	public String getRuta(String memeAMostrar) {
		String ruta = listaDeMemes.get(memeAMostrar);
		return ruta;
	}	
}
 