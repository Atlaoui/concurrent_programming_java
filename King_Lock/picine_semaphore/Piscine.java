package picine_semaphore;

import java.util.concurrent.Semaphore;

public class Piscine {
	private Panier tabP[];
	private Cabine tabC[];

	/*Avec semaphore*/
	private final Semaphore sem_Panier;
	private final Semaphore sem_Cabine;

	public Piscine(int nbCabine,int nbPanier) {
		tabP=new Panier[nbPanier];
		tabC=new Cabine[nbCabine];
		int i;
		for(i=0;i<nbPanier;i++)
			tabP[i]=new Panier();

		for(i=0;i<nbCabine;i++)
			tabC[i]=new Cabine();

		
		/*Pour le trueont ajoute une équitable */
		sem_Panier=new Semaphore(nbPanier,true);
		sem_Cabine= new Semaphore(nbCabine,true);
	
		
	}


	public void prendre_panier_sem() throws InterruptedException {
		sem_Panier.acquire();
	}

	public void rendre_panier_sem() {
		sem_Panier.release();
	}

	public void prendre_cabine_sem() throws InterruptedException {
		sem_Cabine.acquire();
	}

	public void rendre_cabine_sem() {
		sem_Cabine.release();
	}
}
