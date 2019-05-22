package optimisation_LEM;

public class Teste_LEM {
	private static final int NBI=3 ;
	private static final int MAX=5 ;
	private static final int MIN =1;
	private static final int NBOBJET =2;	
	/*sans syncronyzation les deux effaceur tente défois de suprimer la meme valeur du tab */
	public static void main(String[] args) {
		EnsembleDonnees ED=new EnsembleDonnees() ;
		Producteur p;
		Effaceur e;
		Lecteurs l;
		Thread tg;
		for(int i=0;i<NBOBJET;i++) {
			p=new Producteur(ED,Teste_LEM.MIN,Teste_LEM.MAX,Teste_LEM.NBI);
			tg= new Thread(p);
			tg.start();	
		}
		for(int i=0;i<NBOBJET;i++) {
			l= new Lecteurs(ED);
			tg= new Thread(l);
			tg.start();
		}

		for(int i=0;i<NBOBJET;i++) {
			e=new Effaceur(ED,Teste_LEM.MIN,Teste_LEM.MAX,Teste_LEM.NBI);
			tg= new Thread(e);
			tg.start();
		}

		System.out.println("* * * * * * *FIN DU MAIN* * * * * * *");
	}

}
