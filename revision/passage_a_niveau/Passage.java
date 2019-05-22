package passage_a_niveau;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*Q7 */

/*Q9 : méthode 1 changer le sinal du train lorsque il par par un signalAll 
 *   : méthode 2 mettre autan de train que de voiture ??*/


public class Passage {

	private final Lock l = new ReentrantLock();
	private final Condition unTrain = l.newCondition();
	
	/*Q10*/
	private final Semaphore Sem_maxTrain = new Semaphore(2);
	
	private volatile boolean reserve = false;

	public void voitureArrive(int id) {
		l.lock();
		try {
			//si un ou plusieur train ont acuire le permis et ont reserver les permis de passage/*Q10*/
			while (reserve || Sem_maxTrain.availablePermits()==1 || Sem_maxTrain.availablePermits()==0 ) {
				System.out.println("La voiture " + id + " attend qu’un train soit passe.");
				unTrain.await();
			}
			System.out.println("La voiture " + id + " traverse.");
		}catch(InterruptedException e) {
			System.err.println("Panique sur le passage a niveau " + e);
		} finally {
			l.unlock();
		}
	}
	
	//je ne sait pas si je doit garder les lock je pense que oui /*Q10*/
	public void trainArrive(int id) throws InterruptedException {
		l.lock();
		//Sem_maxTrain.acquire();/*Q10*/
		System.out.println("La barriere se baisse pour le train " + id + ".");
		reserve = true;
		l.unlock();
	}

	public void trainPart (int id) {
		l.lock();
		Sem_maxTrain.release();/*Q10*/
		System.out.println("La barriere se leve aprés le passage du train " + id + ".");
		reserve = false;
		unTrain.signalAll();
		l.unlock();
	}
}


