package gestionnaireConvoyeur;


public class AleaObjet {
	private final int poids;
	private final int id;
	private static int cpt=1;
	private static final Object mutex=new Object();
	
	public AleaObjet(int min,int max) {
		poids = (int) ((Math.random()*(max-min))+min);
		synchronized(mutex) {
			id=cpt++;
		}
	}
	
	public int getPoids() {
		return poids;
	}
	
	public int getId() {
		return id;
	}
	
	public String toString(){
		return "Marchandise "+ this.id + " de poids "+this.poids;
	}
}
