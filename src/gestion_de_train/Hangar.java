package gestion_de_train;

public class Hangar {
	private final int id;
	private boolean libre;
	public Hangar(int id) {
		this.id=id;
		setLibre(true);
	}
	public int getId() {
		return id;
	}
	public boolean isLibre() {
		return libre;
	}
	public void setLibre(boolean libre) {
		this.libre = libre;
	}
	public void entrer(int idloco) {
		libre = false;
		System.out.println("la loco "+idloco+" est dans le hangar d'id "+id);
	}

}
