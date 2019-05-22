package serveur_multithread;

public class TestServeur {

	public static void main(String[]args) {
		
		int NB_CLIENTS = 3;
		Thread[] clients = new Thread[NB_CLIENTS];
		Serveur s = new Serveur();
		Thread TS = new Thread(s);
		TS.start();
		for (int i=0; i<NB_CLIENTS; i++){
			clients[i] = new Thread(new Client(s, i));
			clients[i].start();
		}
		for (int i=0; i<NB_CLIENTS; i++){
			try {
				clients[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TS.interrupt();
		}
		
	}
}
