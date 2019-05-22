package chrono_exam_2016;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chronogramme_2016 {
	/*Q1 : deux thread s'execute car la pool a deux thread*/
	/*Q3 : le problemme vien de la taille du pool qui est trop petite et donc ne permet que deux tache en simultaner 
	 * une solution serrait que le max_pool soit égale au maxtaches*/
	
	private final static int MAX_POOL = 2;
	private final static int MAX_TACHES = 3;
	
	public static void main(String []args) {
		ExecutorService executor = Executors.newFixedThreadPool(MAX_POOL);
		CyclicBarrier barriere = new CyclicBarrier(MAX_TACHES);
		System.out.println("main demarre");
		for (int i = 0 ; i < MAX_TACHES ; i++) {
			executor.execute(new UneTache(barriere));
		}
		executor.shutdown();
		System.out.println("main se termine");
	}
}

