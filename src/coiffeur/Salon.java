package coiffeur;

public class Salon {
	private Client sall[];
	private int nbClient;
	private Barbier b;
	private Thread tb;
	private boolean barbierCoupe;
	private static final Object mutex = new Object();
	public Salon(int tailledelasalle) {
		sall=new Client[tailledelasalle];
		nbClient=0;
		b= new Barbier(this);// on lance le barbier
		tb = new Thread(b);
		tb.start();
		barbierCoupe=false;
	}
	

	public synchronized void ajoutClient(Client c){
		if(nbClient != sall.length) {
			sall[nbClient]=c;
			nbClient++;
		
		if(nbClient == 1) {
				System.out.println("Le client " + c.getId() + " réveille le barbier");
				/* réveille le barbier qui est sur le moniteur du salon */
				notifyAll();
			
		}
		}
	}

	public Client retirClient() {
		if(nbClient>0) {
			Client res = sall[0];
			nbClient--;
			int i=0;
			while( i<sall.length-1 && sall[i]!=null) {
				sall[i]=sall[i+1];
				i++;
			}
			return res;
		}
		return null;
	}
	
	public synchronized void Razage () {
		System.out.println("Le client " + sall[0].getId() + " ce fait coupé la barbe");
		synchronized(sall[0]) {	
		sall[0].notify();
		}
		barbierCoupe = true;
		this.retirClient();
	}

	public int getNbClient() {
		return nbClient;
	}

	public int getSalLength() {
		return sall.length;
	}
	
	public void BarbierGoHome() {
		this.tb.interrupt();
		System.out.println("Le barbier rentre chez lui");
	}
	
	
	public void setBarber(Barbier b) {
		this.b=b;

	}
	
	public Barbier getBarber() {
		return this.b;
	}
	
	/*Vrai l'orsque le barbier est occuper*/
	public boolean getBarberSituation() {
		return this.barbierCoupe;
	}
	
	public void barbierAtt() throws InterruptedException {
		synchronized(this) {
			wait();
		}
	}
	
}
