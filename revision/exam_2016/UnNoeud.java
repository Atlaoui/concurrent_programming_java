package exam_2016;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class UnNoeud {
	private final int  ident;
	private static int cpt=1;
	private static final Object mutex = new Object();
	private int maMarque;
	private ArrayList<UnNoeud> successeurs;
	
	/*teste avec les try lock*/
	private ReentrantLock lock;
	
	
	
	/*Q1: cas et tes sont atomic */
	/*Q2: les deux peuve etre utiliser car CAS elle compare la valeur du new a 0 et agie en cons�quence
	 * et TES fait plus ou moins la meme chose car elle teste si la valeur est a 0 sinon elle la change */

	/*Q3-Q8*/
	public UnNoeud() {
		synchronized(mutex){
			ident=cpt++;
		}
		maMarque=0;
		successeurs = new ArrayList<>();
		lock = new ReentrantLock();
	}
	
	public synchronized void ajouteSucc(UnNoeud unNoeud) {
		successeurs.add(unNoeud);
	}
	public synchronized void marquer(int m) {
		if(maMarque==0)
			maMarque=m;
	}

	public String identifiant() {
		if(maMarque!=0)
			return ""+ident;
		else
			return ""+maMarque;	
	}

	public int nbSucc() {
		return successeurs.size();
	}

	public UnNoeud succ(int pos) {
		if(pos<nbSucc())
			return successeurs.get(pos);
		else
			return null;

	}
	public int marque() {
		return maMarque;
	}
	 public synchronized String maCouleur() {
	      
	            if (maMarque == 0) {
	                return "grey";
	            } else if (maMarque == 1) {
	                return "orange";
	            } else {
	                return "aquamarine";
	            }
	        
	    }
}
