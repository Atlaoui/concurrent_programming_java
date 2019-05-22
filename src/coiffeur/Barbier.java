
package coiffeur;

public class Barbier implements Runnable {
	private Salon s;
	

	public Barbier(Salon s) {
		this.s=s;
	}

	@Override
	public void run() {
		synchronized(s){
			try{
				while(true){
					while (s.getNbClient()==0) {
						System.out.println("Le barbier ce fait poussé la barbe");
						s.barbierAtt();
					}
					if(s.getNbClient()>=1)
						s.Razage();
				}
			}catch(InterruptedException e){
				System.out.println("Le barbier a trop attendu");
			}

		}

	}
}