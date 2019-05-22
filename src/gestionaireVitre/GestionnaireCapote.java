package gestionaireVitre;

public class GestionnaireCapote {
	private MoteurVitre md,mg;
	private Thread Tmg,Tmd;
	
	public GestionnaireCapote(MoteurVitre md, MoteurVitre mg) {
		
		md = new MoteurVitre(Cote.DROITE);
		mg = new MoteurVitre(Cote.GAUCHE);
		
		this.md=md;
		this.mg=mg;
		
		Tmg=new Thread(mg);
		Tmd=new Thread(md);
		
		Tmg.start();
		Tmd.start();
	}

	public void DecendreCapoteSpider() {
		Decendre();
		boolean attendre=true;
		while(attendre) {
			if(mg.getPosition()==Position.BASSE && md.getPosition()==Position.BASSE) {
				System.out.println("capote baissé ");
				attendre=false;
				Monter();
				
			}
		}
		attendre=true;
		while(attendre)
			if(md.getOperation()==Operation.NIL && mg.getOperation()==Operation.NIL && mg.getPosition()==Position.HAUTE && md.getPosition()==Position.HAUTE) {
				eteindreTout();
				attendre=false;
			}
				
	}
	
	
	
	
	
	private void Decendre() {
			mg.demander(Operation.DESCENDRE);
			md.demander(Operation.DESCENDRE);
	}

	private void Monter() {
		if (mg.getPosition() == Position.BASSE){
			mg.demander(Operation.MONTER);
		}

		if (md.getPosition() == Position.BASSE){
			md.demander(Operation.MONTER);
		}
	}
	private void eteindreTout() {
		mg.eteindreM();
		md.eteindreM();
	}
	
}
