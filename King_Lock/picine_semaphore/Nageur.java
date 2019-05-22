package picine_semaphore;

public class Nageur implements Runnable{
	Piscine p;
	public Nageur(Piscine p) {
		this.p=p;
	}

	@Override
	public void run() {
		try {
			System.out.println("Le nageur arrive");
			p.prendre_panier_sem();
			p.prendre_cabine_sem();
			nager();
			System.out.println("Le nageur a fini de nager il part");
			p.rendre_cabine_sem();
			p.rendre_panier_sem();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void nager() throws InterruptedException {
		int temps=(int)(Math.random()*(500))+1;
		System.out.println("Le nageur nage pendant"+temps);
		Thread.sleep(temps);

	}
	
}
