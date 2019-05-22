package exam_2016;
import java.util.Random;

public class ParcoursGraphes {
	static int nbNoeuds;
	static Random generateur = new Random();
	//Marcheur []tab;
	public static void main(String []args) {
		nbNoeuds = 12;
		// Creation et sauvegarde du graphe (avant le parcours)
		UnGraphe g = new UnGraphe(nbNoeuds);
		g.sauveEndDot("graphe-avant");
		//g.Graphe_affiche();
		int noeudDepart = generateur.nextInt(nbNoeuds);
		System.out.println("Noeud de depart :"+noeudDepart+'\n'+'\n'+'\n');
		
		new Thread(new Marcheur(g.noeud(noeudDepart))).start();
		System.out.println ("main : le premier marcheur a ete lance");
		while (Marcheur.encoreDesMarcheurs()) {
			try {
				Thread.sleep(100);
				System.out.println ("main : attente de la fin des marcheurs");
			} catch (Exception e) {
				System.err.println ("main : la cata!!");
			}
		}
		System.out.println ("main : les marcheurs sont termines");
		// Sauvegarde du graphe apres le parcours (voir les noeuds marques)
		g.sauveEndDot("graphe-apres");
		//g.Graphe_affiche();
	}
}