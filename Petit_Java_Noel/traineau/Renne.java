package traineau;

import java.util.concurrent.BrokenBarrierException;

public class Renne implements Runnable{
	private PereNoel p;
	private final int id;
	private static final Object mutex = new Object();
	private static int cpt=1;
	
	public Renne( PereNoel papa) {
		synchronized(mutex) {
			id=cpt++;
		}
		this.p=papa;
		
	}

	@Override
	public void run() {
		try {
			System.out.println(toString()+" est revenue.");
			//p.sayHello();
			//p.sayHello_lock();
			p.sayHello_sem();
			//p.sayHello_sem_td();
			
			p.att_hisse();
			
			System.out.println(toString()+" courre.");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Le reine "+id;
	}

}
