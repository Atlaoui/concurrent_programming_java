/* 3520765 HUYNH Caroline */
package Serveur_pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Client implements Runnable{
	private Serveur serveur;
	private final int id;
	private static int cpt=0;
	private static Object mutex = new Object();
	private  int nb_req;
	private ReponseRequete rep;

	private boolean requette_traiter;

	
	public Client(Serveur s,int numRequete) {
		synchronized(mutex){
			id=cpt++;
		}
		this.serveur=s;
		nb_req=5;
		requette_traiter=false;

	}

	public synchronized void  requeteServie(ReponseRequete r) throws InterruptedException, ExecutionException {
		rep=r;
		requette_traiter=true;
		notify();
	}


	@Override
	public void run(){
		try {
			for(int i=0;i<nb_req;i++) {
				System.out.println("E: Le Client"+id+" a emis la requette "+i+1 );
				serveur.soumettre(this,i+1);
				att_rep();					
				System.out.println("R: "+rep.toString());		
		
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void att_rep() throws InterruptedException {
		while(requette_traiter==false) {
			wait();
		}
	}
	
	@Override
	public String toString() {
		return "id  "+id;
	}
}


