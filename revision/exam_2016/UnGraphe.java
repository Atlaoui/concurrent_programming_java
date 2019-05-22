package exam_2016;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class UnGraphe {
	
	/*Q11 : oui car dans la construction d'un graphe il n ya rien de de partager touts est interne a la l'objet*/
	
	private ArrayList<UnNoeud> mesNoeuds = new ArrayList<UnNoeud>();
	private Random generateur = new Random();

	UnGraphe(int taille) {
		for (int i = 0; i < taille ; i++) { // Generer les noeuds
			mesNoeuds.add(new UnNoeud());
		}
		int max = 12; // Generer les successeurs des noeuds
		if (taille < 10) {
			max = 3;
		} else if (taille < 20) {
			max = 5;
		} else if (taille < 30) {
			max = 8;
		} else if (taille < 40) {
			max = 10;
		}
		for (int i = 0; i < taille ; i++) {
			UnNoeud n = mesNoeuds.get(i);
			int ns = generateur.nextInt(max);
			for (int j = 0; j < ns ; j++) {
				int n2;
				do { // un noeud n’est pas son propre successeur
					n2 = generateur.nextInt(taille);
				} while (n2 == i);
				n.ajouteSucc(mesNoeuds.get(n2));//cette ligne pas compris 
			}
		}
	}
	
	
	public void Graphe_affiche() {
		System.out.println("Graphe :");
		int j,i;
		for(i=0;i<mesNoeuds.size();i++) {
			System.out.print("Noeud :"+mesNoeuds.get(i).identifiant());
			System.out.println(" avec :"+mesNoeuds.get(i).nbSucc()+" succeseurs : ");
			for(j=0;j<mesNoeuds.get(i).nbSucc();j++) {
				System.out.print(( mesNoeuds.get(i) ==null  || mesNoeuds.get(i).succ(j) ==null) ? "N/A" : mesNoeuds.get(i).succ(j).identifiant() );
			}
			System.out.println("");
		}
	}
	
	
	
	public UnNoeud noeud(int pos) {
		return mesNoeuds.get(pos);
	}

	public void sauveEndDot (String fichier) {
	       try {
	            PrintWriter p = new PrintWriter(fichier + ".dot");
	            p.println("digraph {");
	            p.println(" fontsize=9;");
	            p.println(" node [shape=circle,width=.5,height=.5,fixedsize=true,style=filled];");
	            for (int i = 0; i < mesNoeuds.size() ; i++) {
	                String label = mesNoeuds.get(i).identifiant();
	                if (mesNoeuds.get(i).marque() == 1) {
	                    label= "<B>" + label + "</B>";
	                }
	                p.println(" \"" + mesNoeuds.get(i).identifiant() + "\" [color=\"" + mesNoeuds.get(i).maCouleur() + "\",label = \"" + mesNoeuds.get(i).identifiant() + "\",style=filled];");
	                for (int j = 0; j < mesNoeuds.get(i).nbSucc(); j++) {
	                    p.println("   \"" + mesNoeuds.get(i).identifiant() + "\" -> \"" + mesNoeuds.get(i).succ(j).identifiant() + "\"");
	                }
	            }
	            p.println("}");
	            p.close();
	        } catch (IOException e) {
	            System.err.println("UnGraphe raised an IOException :" + e);
	            e.printStackTrace();
	        }
		
	} // Vous avez juste besoin de connaitre son existence
}