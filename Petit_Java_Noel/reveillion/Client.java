package reveillion;

import java.util.concurrent.BrokenBarrierException;

public class Client implements Runnable {
	private GroupeClients groupe;
	private final int id;

	
	public Client(GroupeClients groupe,int id) {
		this.id=id;
		this.groupe=groupe;
	}

	@Override
	public void run() {
		try {
			groupe.reserver(this);
			va_au_resto();
			if(groupe.att_tlm()==0)
				System.out.println(toString()+" groupe est au complet et pret a passer la commande");
		} catch (InterruptedException e) {
			System.out.println(toString()+" rentre chez lui.");
		} catch (BrokenBarrierException e) {
			System.out.println(toString()+" a casser la barrier.");
		}
	}

	private void va_au_resto() throws InterruptedException {
		Thread.sleep((int)(Math.random()*(500))+1);
	}
	
	public String toString() {
		return "Client :"+id+" du groupe : "+groupe.getId();
	}
}
