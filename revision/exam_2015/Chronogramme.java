package exam_2015;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chronogramme {

	public static void main(String []args) {

		final int cstMaxThreads = 4;
		final int cstMaxPool = 2;

		ExecutorService exec = Executors.newFixedThreadPool(cstMaxPool);
		CyclicBarrier barriere = new CyclicBarrier(cstMaxPool);
		for (int i = 0; i < cstMaxThreads; i++) {
			Runnable t = new UneThread("T"+(i+1), barriere);
			exec.execute(t);
		}
		exec.shutdown();
	}
}