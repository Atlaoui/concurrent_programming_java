package passage_a_niveau;

import java.util.Random;

/*Q1 : soit elle extend thread soir elle implements Runnable*/
/*Q2 : unTrain et uneVoiture*/



public class LePassage0 {
	private static int nbVoitures;
	private static int nbTrains;
	private static Random generateur = new Random();

	public static void main (String[] args) {
		
			nbVoitures = 5;
			nbTrains = 3;
			
		Passage passNiveau = new Passage();
		// Creation du train
		Thread t;
		
		for(int i=0;i<nbTrains;i++) {
			UnTrain tr = new UnTrain(passNiveau);
			t = new Thread(tr);
			t.start();	
		}
		
		
		// Boucle de creation des voitures
		for (int i = 0 ; i < nbVoitures ; i++) {
			UneVoiture v = new UneVoiture(passNiveau);
			t = new Thread(v);
			t.start();
		}
	}
}



