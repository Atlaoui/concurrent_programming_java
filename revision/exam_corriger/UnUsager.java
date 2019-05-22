package exam_corriger;

import java.util.Random;

public class UnUsager implements Runnable {
	private static int cpt=0;
	private int id,e1,e2;
	private SystemeAppel sa;
	private UnAscenseur ua;
	private Object o=new Object();
	private boolean attente=true;
	
	
	public UnUsager(int e1, int e2, SystemeAppel sa) {
		synchronized(o){
			cpt++;
			id=cpt;
			this.e1=e1;
			this.e2=e2;
			this.sa=sa;
		}
	}
	
	public void ascenseurAlEtage(UnAscenseur a) {
		try{
			this.ua=a;
			reveil();
		}catch(InterruptedException e){}
	}
	
	public void ascenseurArrive(){
		try{
			reveil();
		}catch(InterruptedException e){}
	}
	
	synchronized public void attendre() throws InterruptedException{
		while(attente) wait();
		attente=true;
	}
	
	synchronized public void reveil() throws InterruptedException{
		attente=false;
		notify();
	}

	@Override
	public void run() {
		try{
			sa.appelerAscenseur(e1, this);
			attendre();
			ua.allerEtage(e2);
			attendre();
		}catch(InterruptedException e){}
		finally{
			System.out.println("Usage "+this.id+" : A plus monsieur Ascenseur");
		}
	}

	

}
