package serveur_multithread;

public class Esclave implements Runnable {
	private final int ids;
	private int type;
	private Client c;
	private static int cpt=1;
	private static Object mutex = new Object();
	private int idr;
	
	public Esclave(Client c,int idr,int type) {
		synchronized(mutex){
			ids = cpt++;
		}
		this.c=c;
		this.idr=idr;
		this.type=type;
	}

	@Override
	public void run() {
		if (type == 1){
			try {
				Thread.sleep((int)(Math.random()*(50)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			while (true) {
			}
		}
		c.requeteServie(new ReponseRequete(c,idr));
	}
	
	public int getId(){
		return ids;
	}
}
