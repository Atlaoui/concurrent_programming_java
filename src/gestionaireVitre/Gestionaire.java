package gestionaireVitre;

public class Gestionaire {
	private MoteurVitre md,mg;
	/*Pour informer le gestionnaire ont l'équipe de deux variable  de type enum*/
	private Position posG,posD;
	public Thread Tmg,Tmd;
	
	public Gestionaire(MoteurVitre md, MoteurVitre mg) {	
		md = new MoteurVitre(Cote.DROITE);
		mg = new MoteurVitre(Cote.GAUCHE);
		
		this.md=md;
		this.mg=mg;
		
		Tmg=new Thread(mg);
		Tmd=new Thread(md);
		
		Tmg.start();
		Tmd.start();
		
		posG=mg.getPosition();
		posD=md.getPosition();
		
		
	}
	
	
	public MoteurVitre getMg() {
		return mg;
	}
	public MoteurVitre getMd() {
		return md;
	}
}
