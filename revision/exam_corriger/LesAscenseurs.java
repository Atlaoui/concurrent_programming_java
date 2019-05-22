package exam_corriger;

import java.util.Random;

public class LesAscenseurs {
	private static int nbAscenseurs;
	private static int nbUsagers;
	private static int nbEtages;
	private static Random generateur = new Random();
	
	public static void main(String [] args){
		nbAscenseurs=2;
		nbUsagers=3;
		nbEtages=14;
		SystemeAppel sa = new SystemeAppel();
		
		Thread [] ta =new Thread [nbAscenseurs];
		for(int i=0;i<nbAscenseurs;i++){
			ta[i] = new Thread(new UnAscenseur(sa));
			ta[i].start();
		}
	
		Thread [] tu =new Thread [nbUsagers];
		for(int i=0;i<nbUsagers;i++){
			int e1=generateur.nextInt(nbEtages)+1;
			int e2=generateur.nextInt(nbEtages)+1;
			while (e1==e2){
				e2 = generateur.nextInt(nbEtages) + 1;
			}
			
			tu[i] = new Thread(new UnUsager(e1, e2, sa));
			tu[i].start();
			System.out.println(e1+" lalal "+e2);
		}
		
		for(int i=0;i<nbUsagers;i++){
			try {
				tu[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<nbAscenseurs;i++){
			ta[i].interrupt();
		}
		
	}
}
