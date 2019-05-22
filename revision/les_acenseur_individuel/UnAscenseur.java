package les_acenseur_individuel;

public class UnAscenseur implements Runnable{
	private final int id;
	private static int cpt=1;
	private static final Object mutex = new Object();
	private int etage_actuel;
	private int etage_dest;
	private SystemeAppel sa;
	private boolean busy;

	public UnAscenseur(SystemeAppel sa) {
		synchronized(mutex){
			id=cpt++;
		}
		this.sa=sa;
		etage_actuel=0;
		etage_dest=0;
		busy=false;
	}

	public synchronized void allerEtage(int e) throws InterruptedException {
		while(busy)
				wait();
		etage_dest=e;
		System.out.println(toString()+"-> Portes se ferment");
		etage_actuel=etage_dest;
		System.out.println(toString()+"-> est a l'etage "+etage_dest);
		System.out.println(toString()+"-> Portes s'ouvre");		
	}


	private void deplacement() throws InterruptedException {
		Thread.sleep((int) (Math.random()*10)+1);
	}

	@Override
	public void run() {
		try {
			Requete re;
			while(true) {
				System.out.println(toString()+"-> est a l'etage "+etage_actuel);
				re=sa.recupererRequete();
				allerEtage(re.etage());
				deplacement();
				re.usager().ascenseurAlEtage(this);
				re.usager().ascenseurArrive();
				busy=false;
				System.out.println(toString()+" ->"+re.usager().toString()+" sort");
			}
		} catch (InterruptedException e) {
			System.out.println("L'ascenseur est arreter");
		}
	}
	
	@Override
	public String toString() {
		return "A"+id;
	}

	public boolean getState() {
		return busy;
	}
	public void setBusy(boolean val ) {
		busy = val;
	}
	
	
	
}
