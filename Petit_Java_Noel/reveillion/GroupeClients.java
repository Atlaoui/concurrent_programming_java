package reveillion;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class GroupeClients {
	private Restaurant r;
	private final int id;
	private static int cpt=1;
	private final static  Object  mutex = new Object();
	private int nbclien;
	private Thread [] TC;
	private CyclicBarrier barrier;

	private int num_table;

	public GroupeClients(Restaurant r,int nbclien) {
		synchronized(mutex){
			id=cpt++;
		}
		this.nbclien=nbclien;
		this.r=r;
		num_table=-1;
		TC= new Thread[nbclien];
		barrier = new CyclicBarrier(nbclien);

		for(int i=0;i<nbclien;i++) {
			TC[i]=new Thread(new Client(this,i+1));
			TC[i].start();
		}
	}


	public synchronized void reserver(Client c) {
		if(num_table==-1) {
			Integer val = r.reserver(nbclien);
			if(val==null){
				System.out.println(c.toString()+" na pas pus reserver il parte");
				for(int i=0;i<nbclien;i++) 
					TC[i].interrupt();
			}
			else {
				System.out.println(c.toString()+" a effectuer la reservation pour "+this.nbclien);
				num_table=val.intValue();
			}
		}
	}


	public int att_tlm() throws InterruptedException, BrokenBarrierException {
		return barrier.await();
	}


	public String toString() {
		return "GroupeClien : "+id+" et est composer de : "+nbclien+" de clients";
	}

	public int getNbClient() {
		return nbclien;
	}

	public int getId() {
		return id;
	}
	
	
	public boolean tlm_a_table() {
		return barrier.getNumberWaiting()==nbclien-1;
		
	}
	
	public CyclicBarrier getBarrier() {
		return barrier;
	}

}
