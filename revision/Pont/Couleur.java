package Pont;

public enum Couleur {
	ROUGE(0), VERT(1);

	private Couleur(int index) {
		this.index = index;
	}
	private int index;
	
	public int index() {return index;};
	
	public String toString() {
		if(index==0)
			return "ROUGE";
		else
			return "ROUGE";
	}

}
