package coiffeur;

public class Client implements Runnable {
	private Salon s;
	private static int numCLient=0;
	private static final Object mutex = new Object();
	private final int id;
	public Client(Salon s) {
		this.s=s;	
		synchronized(mutex){
			id = numCLient++;
		}
	}
	public void couper() {
		synchronized(this) {
		notifyAll();
		}
	}
	
	
	 private synchronized void attendre(){
		while(s.getBarberSituation()){ /* attente tant que le barbier est occupé */
			try{
				System.out.println("Le client numéro " + id + " att le barbier ");
				wait();
			}catch(InterruptedException e){
				System.out.println("");
			}
		}
	}

	@Override
	public void run() {
		if(s.getNbClient()==s.getSalLength()) {
			System.out.println("A+ plus de place");
		}
		else{
			s.ajoutClient(this);
			this.attendre();
			System.out.println("C'est bon j'ai eu une coupe");
		}
		
	}
	
	public int getId() {
		return id;
	}

}
