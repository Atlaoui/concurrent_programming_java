/* 3520765 HUYNH Caroline */
package Serveur_pool;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Serveur implements Runnable {
	private ExecutorService pool;
	private final int nbThread;
	private ReentrantLock lock;
	private boolean S_traite;
	
	private Condition cond_attC;
	
	private boolean S_occupe;
	private Condition cond_attS;
	private CompletionService<ReponseRequete> CS;
	
	
	private final int nbtotalR;
	private int nbrT;
	
	
	
	private int idc;
	private Client clienc;
	
	public Serveur(int nb_threads, int nbRm) {
		nbThread=nb_threads;
		nbtotalR =nbRm;
		nbrT=0;
		pool = Executors.newFixedThreadPool(nbThread);
		lock = new ReentrantLock();
		cond_attS= lock.newCondition();
		cond_attC= lock.newCondition();
		S_traite=false;
		S_occupe=false;
		pool =Executors.newFixedThreadPool(nbThread);
		CS = new ExecutorCompletionService<ReponseRequete>(pool);
		
	}
	
	
	//OP
	public void soumettre(Client c, int id) throws InterruptedException {
		lock.lock();
		try {
			//si le serveur est occuper en fait attendre le client 
			while(S_occupe)
				cond_attC.await();
			clienc=c;
			idc=id;
			this.S_occupe=true;
			this. S_traite=true;
			cond_attS.signalAll();	
		}finally {
				lock.unlock();
		}
	}



	private void traiterRequete() throws InterruptedException, ExecutionException {
		lock.lock();
		try {
			
			CS.submit(new SlaveC(clienc,idc));
			ReponseRequete rep=CS.take().get();
			Client c =rep.getC();
			c.requeteServie(rep);
			 S_occupe=false;
			this.S_traite=false;
			cond_attC.signalAll();
			nbrT++;
		}finally {
			lock.unlock();
		}
	}

	//OP
	private void attendreRequete() throws InterruptedException {
		lock.lock();
		try {
			while(S_traite==false)
				cond_attS.await();
		}finally {
			lock.unlock();
		}	
	}
	
	@Override
	public void run() {
		try {
			boolean BOUCLE_T=true;
			while (BOUCLE_T) {
				if(this.nbrT>=nbtotalR)
					BOUCLE_T=false;
				attendreRequete();
				traiterRequete();
			}
			pool.shutdown();
		}
		catch (InterruptedException e) {
			System.out.println("Serveur interrompu");
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}