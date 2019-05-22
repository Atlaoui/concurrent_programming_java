package les_acenseur_individuel;

import java.util.Random;

public class LesAscenseurs {

	private static int nbAscenseurs=1;
	private static int nbUsagers=4;
	private static int nbEtages=5;
	private static Random generateur = new Random();

	public static void main (String[] args) {
		
		SystemeAppel sa = new SystemeAppel();
		Thread tabTU[]= new  Thread[nbUsagers];
		Thread[] tabTA =new  Thread[nbAscenseurs];
		
		// Boucle de creation des ascenseurs (chaque ascenseur dispose de sa propre thread)
		for (int i = 0 ; i < nbAscenseurs ; i++) {
			UnAscenseur a = new UnAscenseur(sa);
			tabTA[i] = new Thread(a);
			tabTA[i].start();
		}

		// Boucle de creation des usagers (l’etage de depart et de destination doivent Ãłtre differents)
		for (int i = 0 ; i < nbUsagers ; i++) {
			int e1 = generateur.nextInt(nbEtages) + 1;
			int e2 = generateur.nextInt(nbEtages) + 1;
			while (e1 == e2) {
				e2 = generateur.nextInt(nbEtages) + 1;
			}
			UnUsager u = new UnUsager(e1, e2, sa);
			tabTU[i]= new Thread(u);
			tabTU[i].start();
		}
		
		// terminaison
		for (int i = 0 ; i < nbUsagers ; i++)
			try {
				tabTU[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		for (int i = 0 ; i < nbAscenseurs ; i++)
			tabTA[i].interrupt();
	}
}









/*try {
nbAscenseurs = Integer.parseInt(args[0]);
nbUsagers = Integer.parseInt(args[1]);
nbEtages = Integer.parseInt(args[2]);
}
catch (ArrayIndexOutOfBoundsException e) {
System.err.println ("Arguments requis, nombre d’ascenseurs, nombre d’usagers, nombre d’etages.");
return;
}*/
