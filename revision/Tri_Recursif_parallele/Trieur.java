package Tri_Recursif_parallele;

public class Trieur implements Runnable {

	private Tableau monTab;
	private final int id;
	private static int cpt=1;
	private final static Object o = new Object();
	

	public Trieur(Tableau t) {
		synchronized(o) {
			id=cpt++;
		}
		monTab=t;
	}

	/*Q6*/
	@Override
	public void run() {
		try {
			// Chercher le pivot
			int pivot = monTab.lire((monTab.nbElements() - 1) / 2);
			// arranger les elements autour du pivot
			int inf = -1;
			int sup = monTab.nbElements();
			while (inf < sup) {
				do {
					inf++;
				} while (monTab.lire(inf) < pivot);
				do {
					sup--;
				} while (monTab.lire(sup) > pivot);
				if (inf < sup) {
					monTab.echanger(inf, sup);
				}
			}
			Trieur t_droite = null,t_gauche = null;
			if(pivot-inf>1) {
				synchronized(o) {	
				t_gauche = new Trieur(monTab.extraire(inf, pivot));
				}
				Thread t = new Thread(t_gauche);
				t.start();
				t.join();

			}
			/*pas sur ici*/
			if(sup-(pivot+1)>1) {
				synchronized(o) {
				t_droite=new Trieur(monTab.extraire(pivot+1,sup));
				}
				Thread t = new Thread(t_droite);
				t.start();
				t.join();
			}
			/*le probleme vien si l'une des deux  est null */
			Tableau.concatener(t_gauche.monTab, t_droite.monTab, monTab);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
