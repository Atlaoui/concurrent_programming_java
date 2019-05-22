package Crible_Eratosthene;

import java.util.ArrayList;

public class Test_Erato {
	public static boolean ARRET_TOTAL=false;
	public static void main(String[] args) {
		/*Methode d'arret num 2 seul qui fonctionne */
		ArrayList<Thread> ArrayErat=new ArrayList<>();
		
		int nb_calcul=100;
		
		Erato prem=new Erato(2,ArrayErat);
		Thread te=new Thread(prem);
		te.start();
		try {
			for(int i=2;i<nb_calcul;i++) {
				prem.forwarder(i);
				}
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(prem.toStringAll());
		
		try {
			Thread.sleep(20000);
			
			System.out.println(prem.toStringAll());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		for(int i=0;i<ArrayErat.size();i++) {
			ArrayErat.get(i).interrupt();
		}
		*/
		//Méthode 1 
		Erato.terminer(prem);
		te.interrupt();
		System.out.println("****** FIN MAIN *******");		
	}
}
