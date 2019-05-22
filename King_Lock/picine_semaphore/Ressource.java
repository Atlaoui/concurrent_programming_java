package picine_semaphore;

public abstract class Ressource {
	private boolean etat;
	public Ressource() {
		etat=false;
	}
	
	public boolean getEtat() {
		return etat;
	}
	
	public void setEtat(boolean val) {
		etat=val;
	}
	

}
