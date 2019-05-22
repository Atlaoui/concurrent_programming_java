package exam_2015;

import java.util.concurrent.CyclicBarrier;

public class UneThread implements Runnable {
	private String id;
	private CyclicBarrier b;

	UneThread(String s, CyclicBarrier b){
		this.id = s;
		this.b = b;
	}

	private void trace (String m) {
		System.out.println (id + " : " + m);
	}

	@Override
	public void run() {
		this.trace("demarre");
		try {
			b.await();
		}
		catch (Exception e){
			System.err.println("probleme global quelque part " + e);
		}
		this.trace("bye bye");
	}
}
