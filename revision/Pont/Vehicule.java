package Pont;

public class Vehicule implements Runnable {
	private static int cptNS=1;
	private static int cptSN=1;
	private static final  Object mutex = new Object();
	private final int id;
	private final Feu f;
	private Pont p;
	public Vehicule(Pont p,Direction d) {
		synchronized(mutex) {
			if(d==Direction.NORD_SUD) {
				id=cptNS++;
				f= p.getF_droite();
			}else {
				id=cptSN++;
				f=p.getF_gauche();
			}
		}
		this.p=p;
	}

	@Override
	public void run() {
		
		p.traverser();

	}

}
