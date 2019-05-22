import java.util.concurrent.atomic.AtomicInteger;

public class Ajoute1 implements Runnable {
	AtomicInteger i;
	public Ajoute1(AtomicInteger i) {
		this.i=i;
	}

	public void tab(int [] tab) {
		tab[0]=1000;
	}
	
	@Override
	public void run() {
	//	i.incrementAndGet();
	}
	
	

}
