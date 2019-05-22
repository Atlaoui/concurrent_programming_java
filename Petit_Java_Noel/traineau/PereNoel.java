package traineau;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PereNoel implements Runnable {
	private final int NB_TOTAL_RENNES; 
	private int nbRennesAtteles = 0;
	private boolean busy = false;

	/* Avec les lock */
	private final ReentrantLock lock;
	private Condition cond_att_R;
	private Condition cond_att_P;
	
	/* Avec les Sem */
	private Semaphore sem_R;
	private Semaphore sem_P;

	/* Barrier qui attent le papaNoel */
	private CyclicBarrier barrier;
	
	public PereNoel(int nb) {
		NB_TOTAL_RENNES = nb;
		lock = new ReentrantLock();
		cond_att_R=lock.newCondition();
		cond_att_P=lock.newCondition();
		
		sem_P = new Semaphore(0);
		sem_R = new Semaphore(0);
		
		barrier = new CyclicBarrier(NB_TOTAL_RENNES+1);
		
	}

	/*y a un cas pour le quelle cette méthode ne foncgtionne pas car le notify  */
	public synchronized void sayHello() throws InterruptedException {
		while (busy) {
			wait();
		}
		busy = true;
		notify();
	}

	private synchronized void attelerRenne() throws InterruptedException {
		while (! busy) {
			wait();
		}
		Thread.sleep(300); // Ca prend du temps d’attacher le bestiau !
		nbRennesAtteles++;
		System.out.printf("Encore un renne attele. ");
		System.out.printf("Il y en a maintenant %d\n", nbRennesAtteles); 
		busy = false;
		notifyAll();
	}



	/* Avec Lock */
	public void sayHello_lock() throws InterruptedException {
		try {
			lock.lock();
			while (busy) {
				cond_att_R.await();
			}
			busy=true;
			cond_att_P.signal();
		}finally {
			lock.unlock();
		}
	}

	private void attelerRenne_lock() throws InterruptedException {	
		try {
			lock.lock();
			while (! busy) {
				cond_att_P.await();
			}
			Thread.sleep(300); // Ca prend du temps d’attacher le bestiau !nbRennesAtteles++;
			nbRennesAtteles++;
			System.out.printf("Encore un renne attele. ");
			System.out.printf("Il y en a maintenant %d\n", nbRennesAtteles); 
			busy = false;
			cond_att_R.signal();
		}finally {
			lock.unlock();
		}
	}

	/* Avec semaphore */
	public void sayHello_sem() throws InterruptedException {
		while (busy) {
			sem_R.acquire();
		}
		busy = true;
		sem_P.release(1);
	}
	
	/*ca ne fonctionne pas commprend pas pourquoi*/
	private Semaphore sem_Rene=new Semaphore(0);
	private Semaphore sem_Pernoel=new Semaphore(0);

	public void sayHello_sem_td() throws InterruptedException {
		sem_Pernoel.release();
		sem_Rene.acquire();
	}
	
	public void attelerRenne_sem_td() throws InterruptedException {
		sem_Pernoel.acquire();
		nbRennesAtteles++;
		sem_Rene.release();
	}
	
	private void attelerRenne_sem() throws InterruptedException {
		while (! busy) {
			sem_P.acquire();
		}
		Thread.sleep(300); // Ca prend du temps d’attacher le bestiau !nbRennesAtteles++;
		nbRennesAtteles++;
		System.out.printf("Encore un renne attele. ");
		System.out.printf("Il y en a maintenant %d\n", nbRennesAtteles); 
		busy = false;
		sem_R.release(1);
	}
	
	public void att_hisse() throws InterruptedException, BrokenBarrierException {
		barrier.await();
	}

	@Override
	public void run() {
		try {
			while (nbRennesAtteles != NB_TOTAL_RENNES) 
				//attelerRenne();
				//attelerRenne_lock();
				attelerRenne_sem();
				//attelerRenne_sem_td();
			Thread.sleep(100);  // Le Pere Noel a du mal a monter !
			att_hisse();
			System.out.println(" Le pereNoel décol ");
		} catch (InterruptedException | BrokenBarrierException e) {
			System.out.println("Pere Noel interrompu en plein travail !");
		}finally {

		}
	}

}
