package perroquets;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Synchroniseur {
	private Perroquet nid[];
	private final int TAILLE;
	private int nbp;
	private ReentrantLock lock;
	private Condition barrier;	
	private int rev;
	private static int ajusteur=0;
	private boolean pret_L=false;


	public Synchroniseur(int taille) {
		TAILLE=taille;
		nbp=0;
		rev=0;
		nid =new Perroquet[TAILLE];
		lock =new ReentrantLock();
		barrier=lock.newCondition();
	}

	public void enregistrer(Perroquet perroquet) throws InterruptedException {
		try {
			lock.lock();
			if(nbp<TAILLE) {
				nid[nbp]=perroquet;
				nbp++;
			}
		}finally {
			lock.unlock();
		}
	}

	public synchronized void barriere() throws InterruptedException {
		while(!(nbp==TAILLE))
			wait();
	}

	public void reveillerUnperroquet() {
		try {
			lock.lock();	
			nid[(rev)%TAILLE].rev();
			rev++;
		}finally {
			lock.unlock();
		}
	}
	synchronized public void premierReveil(int i) throws InterruptedException{
		nid[i].rev();
	}

	public Perroquet getPret(int id) {
		if(id-1>=0)
			return nid[(id-1)%TAILLE];
		else {
			id=2;
			return nid[(id)%TAILLE];
		}
	}
	public synchronized void revSuc(int id) {
		nid[(id+1)%TAILLE].rev();
	}
}


