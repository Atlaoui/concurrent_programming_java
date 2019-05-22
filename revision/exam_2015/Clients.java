package exam_2015;

import java.util.Random;

class Clients implements Runnable {
	//Q3 : en ne doit protéger aucune de c'est donner car aucune n'est acceder de manier concurente de ce coter seul donner est accedr par plusieur thread mais elle doit etre protéger de l'interieur
	private int id;
	private Donnees d;
	private Random generateur = new Random();
	//Q1 :attente est static  partager par toutes les istance de la classe donner
	private static final int attente = 1000;

	Clients(int id, Donnees d) {
		this.id = id;
		this.d = d;
	}

	private void trace (String m) {
		System.out.println (id + " : " + m);
	}

	public void run() {
		this.trace("demarre");
		for (int i = 1; i <= 3 ; i++) {
			int valeur = generateur.nextInt(1000);
			boolean ok = false;
			while (!ok) {
				try {
					ok = d.ecrire(valeur);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (ok) {
					this.trace(valeur + " ecrit");
				} else {
					this.trace(valeur + " non ecrit");
				}
				try {
					Thread.sleep(attente);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for (int i = 1; i <= 3 ; i++) {
			boolean continuer = true;
			while (continuer) {
				try {
					int v = d.lire();
					this.trace("lu " + v);
					continuer = false;
				}
				catch (Exception e) {
					this.trace("lecture impossible " + e);
					try {
						Thread.sleep(attente);
					}
					catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		this.trace("adieu monde cruel...");
	}
}