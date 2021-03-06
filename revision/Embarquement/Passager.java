	// NE PAS MODIFIER CE FICHIER !!
package Embarquement;
import java.util.Random;

public class Passager implements Runnable{

	private Avion avion;
	private int rangee;
	private int siege;
	private Random random;

	public Passager(Avion avion, int rangee, int siege){
		this.avion=avion;
		this.rangee=rangee;
		this.siege=siege;
		random=new Random();
	}

	public void run(){
		try{
			Thread.sleep(random.nextInt((rangee+1)*5000));
			avion.enregistrerPassager(rangee);
			System.out.println("   Passager " + siege + " de la rang�e " + rangee + " pr�t a� embarquer");
			avion.attendreAutorisation(rangee);
			System.out.println("   Passager " + siege + " de la rang�e " + rangee + " autoriser a� embarquer");
			Thread.sleep(random.nextInt(5000));
			System.out.println("   Passager " + siege + " de la rang�e " + rangee + " embarqu�");
			avion.terminerEmbarquement(rangee);
		}catch(InterruptedException e){
			System.out.println("Thread interrompu (ne devrait pas arriver)");
		}
	}

}
