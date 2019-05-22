package perroquets;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Perroquet implements Runnable{
	private final String prenom;
	private Synchroniseur sync;
	private Random gen;
	private final int id;
	private boolean a_affiche;
	private static int cpt=0;
	private static int BlockA=0;
	private boolean is_waiting;
	public Perroquet(String nom ,Synchroniseur sync) {
		id=cpt++;
		this.prenom=nom;
		this.sync=sync;
		gen=new Random();
		a_affiche=false;
	}


	public String toString() {
		return "Peroquet :"+prenom;
	}


	public synchronized void rev() {
		notifyAll();
	}

	@Override
	public void run() {
		try {
			sync.enregistrer(this);
			System.out.println(prenom+" avant barrière");
			sync.barriere();
			System.out.println(prenom+" après barrière");
			while(true){
				a_affiche=false;
				if(id==0)
					System.out.println("---------------------------------------------");
				attendre();
				System.out.println(prenom);
				a_affiche=true;
				sync.revSuc(id);
				Thread.sleep((gen.nextInt(3)+1)*1000);
				sync.reveillerUnperroquet();
			}
		} catch (InterruptedException e) {}
	}


	public synchronized void attendre() throws InterruptedException {	
		while(sync.getPret(id).A_Affiche())	
			wait();
	}
	
	public boolean A_Affiche() {
		return a_affiche;
	}
	
	public synchronized void att_av() throws InterruptedException {
		wait();
	}

	
}

