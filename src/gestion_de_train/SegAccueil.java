package gestion_de_train;

public class SegAccueil {
	private boolean dispo;
	public SegAccueil() {
		dispo = true;
	}

	public synchronized void reserver() throws InterruptedException {
		while(dispo==false) {
			wait();
		}
		dispo = false;
	}

	public synchronized void liberer(int id) {
		// TODO Auto-generated method stub
		dispo = true;
		notifyAll();
		System.out.println("Loco "+id+" a libere le SegAccueil");
		
	}
}
