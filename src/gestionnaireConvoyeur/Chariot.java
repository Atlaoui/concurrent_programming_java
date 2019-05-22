package gestionnaireConvoyeur;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chariot {
	private final Lock lock = new ReentrantLock();
	private final Condition cond_Charger= lock.newCondition();
	private final Condition cond_Decharger = lock.newCondition();
	private final int poidsMax;
	private final int nbMax;
	private int nbObjet;	
	private int poidsCharger;
	private boolean finChargement;
	private ArrayList<AleaObjet>stock;

	
	
	
	public Chariot(int poidsMax, int nbMax) {
		this.poidsMax=poidsMax;
		poidsCharger=0;
		nbObjet=0;
	    this.nbMax=nbMax;
		stock = new ArrayList<>();
		finChargement=false;
	}
	
	
	
	public void AElemChario(AleaObjet obj) {
		lock.lock();
		try {
			/*Cas ou tout ce passe bien*/
			if(nbObjet<= nbMax && poidsCharger+obj.getPoids()<=poidsMax) {		
				stock.add(obj);
				nbObjet++;
				poidsCharger+=obj.getPoids();
			}
			else {
				
				attendre();
				
			}
		}
		finally{
			lock.unlock();
		}
		
	}


	
	
	
	private void attendre() {
		/* tant que le poids ou le nombre de marchandises a atteint sa capacité maximale */
		while (finChargement){
			System.out.println("Le chargeur attend ");
			try {
				cond_Charger.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}		
		}

	}
	
	
	
	public int getNbMax() {
		return nbMax;
	}
	
	public int getPoidsMax() {
		return poidsMax;
	}	
	
}
