package les_acenseur_individuel;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SystemeAppel {
	/*Q1*/
	private ArrayList<Requete> tab_requette;
	
	/*private ReentrantLock l ;
	private Condition cond_A;
	private Condition cond_U;
	*/
	
	public SystemeAppel() {
		this.tab_requette=new ArrayList<>();
		 /*l = new ReentrantLock();
		 cond_A= l.newCondition();
		 cond_U= l.newCondition();
		*/
	}
	
	/*Q2*/
	public synchronized void appelerAscenseur(int e, UnUsager u) {
			tab_requette.add(new Requete(u,e));
			notifyAll(); 
	}
	
	/*Q3*/
	public synchronized Requete recupererRequete() throws InterruptedException {
		while(tab_requette.size()==0)
			wait();
		return tab_requette.remove(0);
	}
}
