package chrono_exam_2016;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class UneTache implements Runnable {
	private int monId;
	private static int cpt = 1;
	private static final Object mutex = new Object();
	private CyclicBarrier maBarriere;

	UneTache(CyclicBarrier b) {
		maBarriere = b;
		synchronized(mutex) {
			monId = cpt++;
		}
	}

	public void run() {
		System.out.println(monId + " demarre ");
		try {
			maBarriere.await();
		} catch (Exception e) {
			System.err.println("Exception levee : " + e);
		}
		System.out.println(monId + " se termine ");
	}
}
