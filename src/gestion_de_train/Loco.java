package gestion_de_train;

public class Loco implements Runnable{
	private final int id;
	private static int cpt=1;
	private SegAccueil sa;
	private SegTournant st;
	private PoolHangars pH;
	private static final Object mutex = new Object(); 

	public Loco(SegAccueil sa, SegTournant st,PoolHangars pH) {
		synchronized(mutex) {
			id=cpt++;
		}
		this.sa =sa;
		this.st =st;
		this.pH=pH;
	}

	@Override
	public void run() {
		try {
			sa.reserver();
			st.appeler(0);
			st.attendrePositionOK();
			st.entrer(id);
			sa.liberer(id);
			st.attendrePositionOK();
			pH.getHangar( st.getPosition() ).entrer(id);
			st.sortir(id);
		}
		catch (InterruptedException e) {
			System.out.println("Loco " + id + " interrompue (ne devrait pas arriver)");
		}

	}


}
