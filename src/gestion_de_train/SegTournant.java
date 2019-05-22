package gestion_de_train;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SegTournant implements Runnable {
	private PoolHangars ph;
	private boolean dispo;
	private boolean est_appele;
	private int posActuelle;
	private int destination;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition cond_occuper = lock.newCondition();
	private final Condition cond_appeler = lock.newCondition();
	private final Condition cond_entre = lock.newCondition(); 
	private final Condition cond_position = lock.newCondition();
	private final Condition cond_vide = lock.newCondition();
	private  static  int ETAT=1; //justpour simuler  le déplacement de du segement


	public SegTournant(PoolHangars ph) {//OP
		this.ph=ph;
		est_appele=false;
		dispo = true;
		posActuelle = 0;
		destination = 0;
	
	}

	private void attendreAppel() throws InterruptedException{
		lock.lock();
		try{
			while(!est_appele){
				System.out.println("Personne n'appelle le segment tournant attend");
				cond_appeler.await();
			}

		}finally {
			lock.unlock();
		}

	}

	public void appeler(int position) throws InterruptedException {//Probleme pour faire bouger le sÃ©gement avec une seul fonction 
		lock.lock();
		try{

			while(!dispo){
				System.out.println("La locomotive attend le segement");
				cond_occuper.await();
			}
			System.out.println("le segTournant est appeler a la position "+position);
			cond_appeler.signalAll();
			if(posActuelle!=0)
				destination=position;
			else
				destination=ph.libre();
			dispo = false;
			est_appele=true;
			cond_appeler.signal();
		


		}finally {
			lock.unlock();
		}

	}

	private void seDeplacer() throws InterruptedException {//OP
		lock.lock();
		try {
			if(ETAT==1) {
				System.out.println("Le segment tournant tourne");
				Thread.sleep(Math.abs(posActuelle-destination)*100);
				System.out.println("Le segment tournant est arriver");
				System.out.println("Le segtournant va de la position "+posActuelle+" a la position "+destination);
				posActuelle=destination;
				ETAT++;
				cond_position.signalAll();
			}
			if(ETAT==2) {
				System.out.println("Le segment tournant tourne");
				Thread.sleep(Math.abs(posActuelle-destination)*100);
				System.out.println("Le segment tournant est arriver");
				destination=ph.libre();
				System.out.println("Le segtournant va de la position "+posActuelle+" a la position "+destination);
				posActuelle=destination;
				ETAT--;
				cond_position.signalAll();
			}

		}finally {
			lock.unlock();
		}
	}


	public void attendrePositionOK() throws InterruptedException {//OP
		lock.lock();
		try {
			while(posActuelle != destination){
				System.out.println("le segement att D'ariver");
				cond_position.await();		
			}
		}finally {
			lock.unlock();
		}	
	}


	private void attendreEntree() throws InterruptedException {//OP
		lock.lock();
		try {
			while(dispo) {
				System.out.println("le Segement Tournant att une loco");
				cond_entre.await();
			}
		}finally {
			lock.unlock();
		}

	}


	public void entrer(int id) {//OP
		lock.lock();
		try {
			dispo = false;
			System.out.println("la locomotive "+id+" Entree");
			cond_entre.signalAll();
		}finally {
			lock.unlock();
		}
	}


	private void attendreVide() throws InterruptedException {//OP
		lock.lock();
		try {
			while(!dispo){
				System.out.println("Le segment tournant attend que la locomotive se casse");
				cond_vide.await();
			}
			cond_occuper.signalAll();
		}finally {
			lock.unlock();
		}
	}




	public void sortir(int id) {//OP
		lock.lock();
		try {

			dispo = true;
			est_appele = false;
			System.out.println("la loco d'id"+id+" est sortie");
			cond_vide.signalAll();
		}finally {
			lock.unlock();
		}

	}

	@Override
	public void run() {
		try {
			while (true) {
				attendreAppel();
				seDeplacer();
				attendreEntree();
				seDeplacer();
				attendreVide();
			}
		}
		catch (InterruptedException e) {
			System.out.println("Terminaison par interruption du segment tournant");
		}
	}

	public int getPosition(){
		return ph.libre();
	}

	public int getETAT() {
		return ETAT;
	}

	public void setETAT(int eTAT) {
		ETAT = eTAT;
	}

}


