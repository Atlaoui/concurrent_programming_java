package Pont;

public class  Feu {
	private Couleur lum;
	
	public Feu() {
		lum=Couleur.ROUGE;
	}
	
	public void set_rouge() {
		lum=Couleur.ROUGE;
	}

	public void set_vert() {
		lum=Couleur.VERT;
		
	}

	public int index() {
		
		return lum.index();
	}

	
	
}
