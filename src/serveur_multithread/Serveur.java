package serveur_multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private ReponseRequete requetEncours;
	/*Condition General*/
	private ReentrantLock lock;
	/*Condition d'att du serveur*/
	private boolean s_dispo;
	private Condition cond_attS;


	/*Condition de traitement*/
	private Condition cond_attC;
	private boolean req_t;

	private Client clienc;
	private int idc;
	private int type;

	public Serveur() {
		lock = new ReentrantLock();
		cond_attC = lock.newCondition();
		cond_attS =lock.newCondition();
		this.s_dispo=true;
		req_t=false;
	}

	/*Le serveur envois la requette a des Esclave*/
	private void traiterRequete() {
		lock.lock();
		try {
			Esclave es=new Esclave(clienc,idc,type);
			new Thread(es).start();
			s_dispo=true;
			req_t=false;
			cond_attC.signalAll();
		}finally {
			if(lock.isLocked())
				lock.unlock();
		}
	}


	private void attendreRequete() throws InterruptedException {
		lock.lock();
		try {
			while (req_t==false)
				this.cond_attS.await();
			
			s_dispo=false;
		}finally {
			lock.unlock();
		}
	}
	
	
	public void soumettre(Client c, int id, int type) throws InterruptedException {
		lock.lock();
		try {
			while(s_dispo==false)
				cond_attC.await();

			clienc=c;
			idc=id;
			this.type=type;
			this.s_dispo=false;
			this.req_t=true;
			this.cond_attS.signalAll();//reveilier S

		}finally {
			lock.unlock();
		}

	}
	
	@Override
	public void run() {
		try {
			while (true) {
				attendreRequete();
				traiterRequete();
			}
		}
		catch (InterruptedException e) {
			System.out.println("Serveur interrompu");
		}
	}

}