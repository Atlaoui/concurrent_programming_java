package exam_corriger;
import java.util.Random;

public class UnAscenseur implements Runnable {
	private static int cpt=0;
	private int id;
	private SystemeAppel sa;
	private Object o=new Object();
	private int destination=0;
	private boolean attente=true;
	
	public UnAscenseur(SystemeAppel sa) {
		synchronized(o){
			cpt++;
			id=cpt;
			this.sa=sa;
		}
	}
	
	public void allerEtage(int e){
		try{
			destination = e;
			reveil();
		}catch(InterruptedException ee){}
	}
	
	synchronized public void attendre() throws InterruptedException{
		while(attente) wait();
	}
	
	synchronized public void reveil() throws InterruptedException{
		attente=false;
		notify();
	}

	@Override
	public void run() {
		try{
			while(true){
				Requete r= null;
				r=sa.recupererRequete();
				Thread.sleep((new Random()).nextInt(Math.abs((r.getE()-destination))*1000)+1);
				r.getU().ascenseurAlEtage(this);
				attendre();
				Thread.sleep((new Random()).nextInt(Math.abs((r.getE()-destination))*1000));
				r.getU().ascenseurArrive();
			}
		}catch(InterruptedException e){System.err.println(e);}
	}

}
