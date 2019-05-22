package picine_semaphore;

public class Panier extends Ressource {
	private static int cpt=1;
	private final int id;
	public Panier() {
		super();
		id=cpt++;
	}
	
	public int getId() {
		return id;
	}

}
