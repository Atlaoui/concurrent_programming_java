package optimisation_LEM;

public class Producteur implements Runnable {
	private	EnsembleDonnees ed;

	/*nombre d'inserstion et la valeur min et max et le nombre d'inserstion total*/
	private final int NBI;
	private final int MAX;
	private final int MIN;
	private int NBIT;
	
	private final int id;
	private static final Object mutex=new Object();
	private static int cpt=1;
	
	/*Constructeur pour gerer plus simplement les creation*/
	public Producteur(EnsembleDonnees ed,int min,int max,int nbelem) {
		synchronized(mutex){
			id=cpt++;
		}
		NBI=nbelem;
		MAX=max;
		MIN=min;
		this.ed=ed;
	}

	
	public Producteur(EnsembleDonnees ed) {
		synchronized(mutex){
			id=cpt++;
		}
		this.ed=ed;
		 NBI =5;
		 MAX =100;
		 MIN =1;
	}


	@Override
	public void run() {
		NBIT =3;
		while(NBIT-->=0) {//sal mais ca marche
			for(int i=0;i<NBI;i++)
				ed.add_in(new Integer((int)((Math.random()*(MAX-MIN))+MIN)));
			this.att_sans_tempo();
		}
	}

	private void att_sans_tempo() {

	}

}
