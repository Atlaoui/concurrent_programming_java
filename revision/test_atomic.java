import java.util.concurrent.atomic.AtomicInteger;

public class test_atomic {


	public static void main(String[] args) {
		int []nb_ajout=new int[2];
		nb_ajout[0]=0;
		nb_ajout[1]=0; 
		Ajoute1 aba = new Ajoute1(null);
		
		aba.tab(nb_ajout);
		
		System.out.println(nb_ajout[0]);
		
	/*	AtomicInteger i = new AtomicInteger(0);
		Ajoute1 [] tab = new Ajoute1[nb_ajout];
		Thread [] tab2 = new Thread[nb_ajout];

		for(int j =0;j<100;j++) {
			tab[j] = new Ajoute1(i);
			tab2[j] =new Thread(tab[j]);
			tab2[j].start();
		}

		try {
			for(int j =0;j<100;j++)
				tab2[j].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Atomic intger = "+i);
	}/*/
		
		
	}
}
