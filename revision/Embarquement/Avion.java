package Embarquement;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// Donnez la r√©ponse √† la question 2 ici

public class Avion{

	private int nbRangees;
	private int nbSieges;
	private int etat[];


	/*Q2)
	 * Ici ont va utiliser un Reantrant lock pour auptimiser le paraleleisme
	 * ainsi plusieur thread pourant c'executer entre condition 
	 * en utilisra un tableau de condition pour les passager ainsi en reveille que une 
	 * partie et non la totaliter */





	private ReentrantLock[] tab_lock;
	private Condition cond_Enregistrement[];
	private Condition cond_Autorisation[];
	private Condition cond_Embarquement[];


	public Avion(int nbRangees, int nbSieges){
		
		tab_lock = new ReentrantLock[nbRangees];
		
		cond_Embarquement = new Condition[nbRangees];
		cond_Enregistrement =new Condition[nbRangees];
		cond_Autorisation = new Condition[nbRangees];
		
		for(int i=0;i<nbRangees;i++) {
			tab_lock[i] = new ReentrantLock();
			cond_Autorisation[i]= tab_lock[i].newCondition();
			cond_Enregistrement[i]= tab_lock[i].newCondition();
			cond_Embarquement[i]= tab_lock[i].newCondition();
		}
		
		this.nbRangees=nbRangees;
		this.nbSieges=nbSieges;
		etat=new int[nbRangees];
		for(int i=0;i<nbRangees;i++){
			etat[i]=-nbSieges-1;
		}	
	}

	public int getNbRangees(){
		return nbRangees;
	}

	/*ont att tout les passager de la ranger*/
	public void attendreEnregistrement(int rangee) throws InterruptedException{
		try {tab_lock[rangee].lock();
		while(etat[rangee]!=-1)
			cond_Enregistrement[rangee].await();
		}finally {
			tab_lock[rangee].unlock();
		}

	}

	/*Ont s'enregistre et en reveille le Chef*/
	public void enregistrerPassager(int rangee){
		try {tab_lock[rangee].lock();
		etat[rangee]++;
		cond_Enregistrement[rangee].signalAll();
		}finally {
			tab_lock[rangee].unlock();
		}
	}

	/* Ici les passager  att l'autorisation */
	public void attendreAutorisation(int rangee) throws InterruptedException{
		try {tab_lock[rangee].lock();
		while(etat[rangee]<1)
			cond_Autorisation[rangee].await();
		}finally {
			tab_lock[rangee].unlock();
		}
	}
	
	/*ici en cr√©e des ranger */
	public void autoriserEmbarquement(int rangee){
		try {tab_lock[rangee].lock();
		
		cond_Autorisation[rangee].signalAll();
		}finally {
			tab_lock[rangee].unlock();
		}

	}

	public void attendreEmbarquement(int rangee) throws InterruptedException{
		try {tab_lock[rangee].lock();
		
		while(etat[rangee]<-1) {
			System.out.println(" -------- la rangÈe " + rangee + " il reste "+etat[rangee] );
			cond_Embarquement[rangee].await();
		}
		}finally {
			tab_lock[rangee].unlock();
		}

	}

	public void terminerEmbarquement(int rangee){
		try {tab_lock[rangee].lock();
		etat[rangee]++;
		System.out.println(" -------- la rangÈe " + rangee + " il reste "+etat[rangee] );
		cond_Embarquement[rangee].signalAll();
		}finally {
			tab_lock[rangee].unlock();
		}
	}

}
