/* 3520765 HUYNH Caroline */
package Serveur_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServeurPool {

	public static void main(String[] args) {
		int nbClients=5;
		int nbServeur=1;
		int nbthread=5;
		ExecutorService pool = Executors.newFixedThreadPool(nbServeur);
		Serveur s=new Serveur(nbthread,nbClients*5);
		pool.execute(s);

		Thread[] t_clients = new Thread[nbClients];

		for (int i=0; i<nbClients; i++){
			t_clients[i] = new Thread(new Client(s,i));
			t_clients[i].start();
		}
		for (int i=0; i<nbClients; i++){
			try {
				t_clients[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("*******FIN DU MAIN*******");	
	}
}