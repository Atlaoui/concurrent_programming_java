package exam_2015;
public class Partage {
	
	/*Q2-1 : je dirait que l'execution est correct*
	 * Q2-2 : lordre d'appel des fonctionne est systematique mais lordre d'affichage peux changer
	 * car les thread s'execute en meme temps*/
	public static void main(String []args) {
		final int cstMaxClients=2;
		final int cstTaille=6;

		Donnees donnees = new Donnees(cstTaille);

		for (int i = 0; i < cstMaxClients ; i++) {
			Clients c = new Clients(i+1, donnees);
			Thread t = new Thread(c);
			t.start();
		}
	}
}




/*
try {
	cstMaxClients = Integer.parseInt(args[0]);
	cstTaille = Integer.parseInt(args[1]);
}
catch (ArrayIndexOutOfBoundsException e) {
	System.err.println ("Arguments requis, nombre de clients et taille des donnees partagees.");
	return;
}
*/