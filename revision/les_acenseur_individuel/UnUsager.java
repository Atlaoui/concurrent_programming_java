package les_acenseur_individuel;

public class UnUsager implements Runnable {
	private SystemeAppel sa;
	private final int id;
	private static int cpt=1;
	private static final Object mutex = new Object();
	private int etage_actuel;
	private int etage_dest;
	private boolean attendre;
	public UnUsager(int e1, int e2, SystemeAppel sa) {
		synchronized(mutex){
			id=cpt++;
		}
		this.sa=sa;
		etage_actuel=e1;
		etage_dest=e2;
		
	}
	
	public  void ascenseurAlEtage(UnAscenseur a) throws InterruptedException {
		System.out.println(toString()+"-> l'acenseur "+a.toString());
		a.allerEtage(etage_dest);
		attendre= true;
		attendre();
	}

	public synchronized void ascenseurArrive() {
		System.out.println(toString()+"-> Je suis arriver ");	
		attendre= false;
		notify();
	}
	
	@Override
	public void run() {
		System.out.println(toString()+"-> Je suis a l'etage "+etage_actuel+" en partance pour le "+etage_dest);
		System.out.println(toString()+"-> j'appelle un acenseur");
		sa.appelerAscenseur(etage_actuel, this);
	}
	
	
	private synchronized void attendre() throws InterruptedException {
		while(attendre)
			wait();
	}
	
	
	@Override
	public String toString() {
		return "U"+id;	
	}
}
