package Crible_Eratosthene;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Erato implements Runnable{

	/*Le numero pos*/
	private final int num;


	private final int id;
	private static int cpt=1;
	private boolean work_to_do;
	private ReentrantLock lock;
	private  Thread  nextT;

	/*Condition si il est deja entraint de travailler*/
	private Condition cond_trav;

	/*Condition je ne sait pas pk */
	private Condition cond_pret;


	/*valeur a traiter*/
	private int val;

	/*Erato suivant*/
	private Erato next;

	private ArrayList<Thread> arrayErat;
	private static Object mutex = new Object();
	
	private boolean plus_rien_a_fair;

	public Erato(int p) {
		synchronized(mutex){
			num=cpt++;
			lock = new ReentrantLock();
			cond_trav = lock.newCondition();
			cond_pret = lock.newCondition();	
			work_to_do=false;
			this.id=p;
			plus_rien_a_fair=true;
		}
	}
		
	/*Constructeur pour la méthode de terminaison 2 */
	public Erato(int p, ArrayList<Thread> arrayErat) {
		synchronized(mutex){
			num=cpt++;
			lock = new ReentrantLock();
			cond_trav = lock.newCondition();
			cond_pret = lock.newCondition();	
			work_to_do=false;
			this.id=p;
			this.arrayErat=arrayErat;
		}
	}



	@Override
	public void run() {
		try {
			while(true) {
				attendre();
				traiter();
			} 	
		}catch (InterruptedException e) {
			System.out.println("Je suis l'Erato "+id+" Je MEUUUUR!!!");
		}

	}

	public void attendre() throws InterruptedException {
		lock.lock();
		try {
			while(!work_to_do) {
				plus_rien_a_fair=true;
				cond_trav.await();
			}
		}finally {
			lock.unlock();
		}

	}



	public void forwarder(int v) throws InterruptedException {
		lock.lock();
		try {
			while(work_to_do)
				cond_pret.await();
			val=v;
			work_to_do=true;
			cond_trav.signalAll();
		}finally {
			lock.unlock();
		}
	}

	public void traiter() throws InterruptedException {
		lock.lock();
		try {
			if(val%id!=0)
				if(next!=null)
					next.forwarder(val);
				else {
					next=new Erato(val,arrayErat);
					nextT =new Thread(next);
					nextT.start();
					if(arrayErat!=null)
						arrayErat.add(nextT);
				}
			work_to_do=false;
			cond_pret.signalAll();
		}finally {
			lock.unlock();
		}
	}
	
	public String toString() {
		return " Erato de "+id+" mon numero est :"+ num;
	}

	public String toStringAll() {
		Erato tmp=getNextErato();
		Erato tmp2;
		String S="";
		S+=this.toString();
		S+='\n';
		while(tmp!=null) {
			S+=tmp.toString();
			S+='\n';
			tmp2=tmp.getNextErato();
			tmp=tmp2;
		}
		return S;
	}


	public Erato getNextErato() {
		return this.next;
	}

	/*méthode fonctionnel 
	 * elle tu les erato de 2 a n 
	 * apres il faut tuer le erato 1
	 * */
	public static void terminer(Erato e) {
		if(e!=null) {
			terminer(e.next);
			if(e.nextT!=null)
				e.nextT.interrupt();
		}
	}

	public boolean getIsActive() {
		return plus_rien_a_fair;
	}
}
