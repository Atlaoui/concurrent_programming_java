package exam_2017;

import java.util.Random;

/*Q1:*/
/*Q2: le wait lache le moniteur alors que le sleep ne lache pas le moniteur et par conséquent aucun 
 * thread ne poura s'executer au meme moment*/

public class Utilisateur implements Runnable {
	private Random generateur = new Random();
	private static int cpt = 1;
	private static Object o = new Object();
	private int monId;
	private Ressource[] mesRessources;

	Utilisateur(Ressource[] tab) {
		synchronized (o) {
			monId = cpt++;
		}
		mesRessources = tab;
	}
	private void travaillerAvecRessources () {
		// non detaille ici
	}
	public void run() {
		System.out.println("Utilisateur "+monId+" est lance");
		try {
			int nbRess = generateur.nextInt(mesRessources.length) + 1;
			if (monId % 2 == 0) {
				for (int i=0; i < mesRessources.length ; i++) {
					mesRessources[i].reserver(nbRess, monId);
				}
			} else {
				for (int i=mesRessources.length - 1; i >= 0 ; i--) {
					mesRessources[i].reserver(nbRess, monId);
					Thread.sleep(generateur.nextInt(100));
				}
			}
			travaillerAvecRessources();
			for (int i=0; i < mesRessources.length ; i++) {
				for (int j=0; j < nbRess; j++) {
					mesRessources[i].relacher(monId);
					Thread.sleep(generateur.nextInt(100));
				}
			}
		} catch (Exception e) {
			System.err.println("dans "+monId);
			e.printStackTrace();
		}
	}
}