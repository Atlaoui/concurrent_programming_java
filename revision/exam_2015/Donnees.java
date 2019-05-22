package exam_2015;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Donnees {

	/*Q4: les deux doive etre protégé car les deux sont soliciter de manier concurente */

	private int lesDonnees[];
	private boolean valide[];

	/*Reponse a la question 5*/
	private ReentrantLock lock;
	private Condition Cond_ecriture;
	private Condition Cond_lecture;
	private boolean lectue;
	private boolean ecriture;

	Donnees (int taille) {
		lock =new ReentrantLock();
		lesDonnees = new int[taille];
		valide= new boolean[taille];
		Cond_ecriture= lock.newCondition();
		Cond_lecture= lock.newCondition();
		for (int x = 0 ; x < valide.length ; x++) {
			valide[x] = false;
		}

	}

	public boolean ecrire (int val) throws InterruptedException {
		try {
			lock.lock();
			while(ecriture)
				Cond_ecriture.await();
			ecriture=true;
			while(lectue)
				Cond_ecriture.await();
			lectue=true;
			
			for (int i = 0 ; i < valide.length ; i++) {
				if (!valide[i]) {
					valide[i] = true;
					lesDonnees[i] = val;
					return true;
				}
			}
			return false;
		}finally {
			Cond_ecriture.signalAll();
			Cond_lecture.signalAll();
			ecriture=false;
			lectue=false;
			lock.unlock();
		}
	}

	public int lire () throws PasDeDonneesError, InterruptedException {
		try {
			lock.lock();
			lectue=true;
			while(ecriture)
				Cond_lecture.await();
			
			int j = 0;
			for (; j < valide.length ; j++) {
				if (valide[j]) {
					valide[j] = false;
					return lesDonnees[j];
				}
			}
			throw new PasDeDonneesError();
		}
		finally {
			Cond_ecriture.signalAll();
			lectue=false;
			lock.unlock();
		}
	}
}