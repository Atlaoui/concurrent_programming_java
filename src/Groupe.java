/* 3520765 HUYNH Caroline */
public class Groupe implements Runnable {
	private int nbP;
	private static int nbGroupe=0;
	private Salle s;
	private int nuGroupe;
	private int Max = 10;
	private int Min = 1; 
	public Groupe(Salle s) {
		this.nbP=5;            /*(int) (Math.random() * ( Max - Min ));*/
		this.nuGroupe=nbGroupe++;
		this.s=s;
	}
	@Override
	public void run() {
		System.out.println("Bonjour on est le groupe "+nuGroupe+" et on souhaite reserver pour "+nbP+" personnes");
		s.reserver(nbP);
		s.annuler(nbP);
	}
	
	
	public int getNbP() {
		return nbP;
	}
	
	public int getnbGroupe() {
		return nbGroupe;
	}
	
	public int getnuGroupe() {
		return nuGroupe;
	}
	
	
	
}
