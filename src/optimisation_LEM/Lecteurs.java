package optimisation_LEM;

public class Lecteurs implements Runnable {
	private	EnsembleDonnees ed;
	private final int id;
	private static final Object mutex=new Object();
	private static int cpt=1;
	public Lecteurs(EnsembleDonnees ed) {
		synchronized(mutex){
			id=cpt++;
		}
		this.ed=ed;
		
	}

	@Override
	public void run() {
		try {
		int cpt=1;
		System.out.println("Le lecteur "+id+" fait l'affichage "+cpt+":");
		System.out.println(ed.toString());
		cpt++;
		Thread.sleep(1000);
		System.out.println("Le lecteur "+id+" fait l'affichage "+cpt+":");
		System.out.println(ed.toString());
		cpt++;
		Thread.sleep(1000);
		System.out.println("Le lecteur "+id+" fait l'affichage "+cpt+":");
		System.out.println(ed.toString());	
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}

}
