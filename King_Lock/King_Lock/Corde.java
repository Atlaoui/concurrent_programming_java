package King_Lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Corde {

	/*Avec lock*/
	private ReentrantLock lock;
	private boolean DEPLAC_S_N;
	private boolean DEPLAC_N_S;

	private Condition Cond_Sud;
	private Condition Cond_Nord;
	private Condition Cond_personne;
	
	
	private volatile int On_the_rope_cpt;
	private final int Max_On_the_rope=5;
	private boolean KingKong_on;
	private Position direction;

	
	
	/*Avec Semaphore*/
	private Semaphore sem_block;
	private Semaphore mutex;
	private int[] cpt;
	private int cptIn;
	private Semaphore[] semAcces;

	
	

	
	public Corde() {

		/*Pour la version avec les lock*/
		lock = new ReentrantLock();
		Cond_Sud=lock.newCondition();
		Cond_Nord=lock.newCondition();
		Cond_personne=lock.newCondition();
		
		
		DEPLAC_N_S=false;
		DEPLAC_S_N=false;
		/*Pour compter le nombre de Singe sur la Corde*/
		On_the_rope_cpt=0;

		KingKong_on=false;


		/*avec semaphore TD*/
		int i;
		semAcces=new Semaphore[2];
		cpt=new int[2];
		for(i=0;i<semAcces.length;i++) {
			semAcces[i]=new Semaphore(5);
			cpt[i]=0;
		}
		mutex = new Semaphore(1);
		
		sem_block=new Semaphore(0);
		direction=null;
		cptIn=0;
		
		
	}

	/*-------------------------------------------AVEC LOCK----------------------------------------------------------*/
	public void acceder_lock(Position position) throws InterruptedException {//OP
		try{
			lock.lock();
			/*Nord =0*/
			if(position.index()==0) {
				/*tanque y a des des singe sur la corde et que la direction de deplacement est S_N*/
				while( DEPLAC_S_N || On_the_rope_cpt>Max_On_the_rope || KingKong_on ) {
					Cond_Nord.await();
				}
				On_the_rope_cpt++;
				DEPLAC_N_S=true;

				/*Sud =0*/	
			}else if(position.index()==1) {
				while(DEPLAC_N_S || On_the_rope_cpt>Max_On_the_rope || KingKong_on) { 
					Cond_Sud.await();
				}
				On_the_rope_cpt++;
				DEPLAC_S_N=true;
			}
		}finally {
			lock.unlock();
		}
	}


	/*Ici les deux méthode son équivalente ,?????*/
	public void lacher_lock(Position position) {//OP
		try {
			lock.lock();
			/*On_the_rope_cpt--;
			if(On_the_rope_cpt==0) {
				DEPLAC_N_S=false;
				DEPLAC_S_N=false;
				Cond_Sud.signalAll();
				Cond_Nord.signalAll();
			}
			 */
			if(position.index()==0) {
				On_the_rope_cpt--;
				if(On_the_rope_cpt==0) {
					DEPLAC_N_S=false;
					Cond_Sud.signalAll();
					Cond_personne.signalAll();
				}
			}else if(position.index()==1) {
				On_the_rope_cpt--;
				if(On_the_rope_cpt==0) {
					DEPLAC_S_N=false;
					Cond_Nord.signalAll();
					Cond_personne.signalAll();
				}
			}
		}finally {
			lock.unlock();
		}
	}

	public void accederKong_lock() throws InterruptedException {//OP
		try {
			lock.lock();
			KingKong_on=true;
			while(On_the_rope_cpt>0)
				Cond_personne.await();
			On_the_rope_cpt++;
			DEPLAC_N_S=false;
			DEPLAC_S_N=false;
		}finally {
			lock.unlock();
		}
	}


	public void lacherKong_lock() {//OP
		try {
			lock.lock();
			On_the_rope_cpt--;
			KingKong_on=false;
			Cond_personne.signalAll();
			Cond_Sud.signalAll();
			Cond_Nord.signalAll();
		}finally {
			lock.unlock();
		}
	}



/*------------------------------------------------Avec Semaphore--------------------------------------------------------*/

	/*Avec semaphore fonctionel mais l'entrée du 1er baboin est determinante */
	public void acceder_sem(Position position) throws InterruptedException {
		semAcces[(position.index())].acquire();
		mutex.acquire();
		cptIn++;
		mutex.release();
		while(KingKong_on || (direction !=null && direction != position) ) 
			this.sem_block.acquire();
		
		mutex.acquire();
		if (direction == null){
			direction = position;
		}
		
		On_the_rope_cpt++;
		mutex.release();
		
	}
	public void lacher_sem(Position position) throws InterruptedException {
		try{
			mutex.acquire();
		
		On_the_rope_cpt--;
		if (On_the_rope_cpt == 0){
			direction = null;
			sem_block.release(cptIn);
			cptIn = 0;
		}
		semAcces[(position.index())].release();
		
		}finally {
			mutex.release();
		}
	}
	
	public void accederKong_sem(Position position) throws InterruptedException {
		while(On_the_rope_cpt>0) 
			sem_block.acquire();
		mutex.acquire();	
		On_the_rope_cpt++;
		cptIn++;
		KingKong_on=true;	
		mutex.release();	
		
	}
	
	public void lacherKong_sem(Position position) throws InterruptedException {
		try{
			mutex.acquire();
			KingKong_on=false;
			sem_block.release(cptIn);
			cptIn=0;
		}finally {
			mutex.release();
		}
	}
	
/*----------------------------------------------CODE VUE EN TD------------------------------------------------------*/
	/*avec synchronized juste pour un baboin a la fois */
	private boolean est_libre=true;
	public synchronized void acceder_sync() throws InterruptedException {
		while(!est_libre)
			wait();
		est_libre=false;
	}


	public synchronized void lacher_sync() {
		est_libre=true;
		notifyAll();
	}

	

	public void acceder_sem_td(Position p) throws InterruptedException {
		mutex.acquire();
		if (cptIn == 0) {
			semAcces[(p.index()+1)%2].acquire();
		}
		cptIn++;
		mutex.release();
		semAcces[p.index()].acquire();
		semAcces[p.index()].release();
	}

	public void acceder_sem_td2(Position p) throws InterruptedException {
		mutex.acquire();

		if (cpt[0] == 0 && cpt[1] == 0) {
			semAcces[(p.index()+1)%2].acquire();
		}
		cpt[p.index()]++;
		mutex.release();
		semAcces[p.index()].acquire();
		semAcces[p.index()].release();
	}	
}