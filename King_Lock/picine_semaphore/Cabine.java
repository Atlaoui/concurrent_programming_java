package picine_semaphore;

public class Cabine extends Ressource {
	private static int cpt=1;
	private final int id;
	public Cabine() {
		super();
		id=cpt++;
	}
	public int getId() {
		return id;
	}

}
