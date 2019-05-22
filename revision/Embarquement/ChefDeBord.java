	// NE PAS MODIFIER CE FICHIER !!
package Embarquement;
import java.util.Random;

public class ChefDeBord implements Runnable{

	private Avion avion;
	

	public ChefDeBord(Avion avion){
		this.avion=avion;
	}

	public void run(){
		try{
			Thread.sleep(new Random().nextInt(3000));
			System.out.println("L'embarquement est ouvert");
			for(int i=0;i<avion.getNbRangees();i++){
				System.out.println("Le chef de bord attend l'enregistrement de la rang�e " + i);
				avion.attendreEnregistrement(i);
				System.out.println("L'embarquement de la rang�e " + i + " commence");
				avion.autoriserEmbarquement(i);
				avion.attendreEmbarquement(i);
				System.out.println("L'embarquement de la rang�e " + i + " est termin�");
			}
			System.out.println("L'embarquement est termin�");
		}catch(InterruptedException e){
			System.out.println("Thread interrompu (ne devrait pas arriver)");
		}
	}

}
