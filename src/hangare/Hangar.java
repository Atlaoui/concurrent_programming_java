package hangare;

public class Hangar {
	private static final Object mutex = new Object();
	private final int idH;
	private static int cpt=0;
	public Hangar() {
		synchronized(mutex) {
			idH=cpt;
			cpt++;
		}
		
	}
	
	
	public int getIdH() {
		return idH;
	}

}
