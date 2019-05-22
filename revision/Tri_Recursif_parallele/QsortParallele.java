package Tri_Recursif_parallele;

public class QsortParallele {
	private static int taille;

	public static void main (String []args) {
		try {
			taille = 10;//Integer.parseInt(args[0]);
			if (taille < 2) {
				System.err.println ("Pas assez d’elements, cela n’a pas de sens");
				return;
			}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println ("Arguments requis, taille du tableau");
			return;
		}

		// Initialisation et affichage du tableau initial
		Tableau aTrier = new Tableau(taille);
		aTrier.chargeAleatoire();
		System.out.println("========== avant ==========");
		aTrier.afficher();

		// Lancer le tri et recuperer le resultat
		Trieur t = new Trieur(aTrier);
		new Thread (t).start();
		
		System.out.println("========== apres ==========");
		aTrier.afficher();
	}
}