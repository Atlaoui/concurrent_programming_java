package gestionaireVitre;

public class Teste_Vitre {

	public static void main(String[] args) {
		MoteurVitre mg = new MoteurVitre(Cote.GAUCHE);
		MoteurVitre md = new MoteurVitre(Cote.DROITE);
		//Gestionaire g = new Gestionaire(mg, md);
		GestionnaireCapote gc = new GestionnaireCapote(mg, md);
		
		gc.DecendreCapoteSpider();
		
		
			
		//for(int i=0;i<10;i++) {
			//			}		
		
		
		//System.out.println("merde "+g.getMd().getAlum());
		//g.getMd().eteindreM();
		//g.getMg().eteindreM();
		
	}
}
