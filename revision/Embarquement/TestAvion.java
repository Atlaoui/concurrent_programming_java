package Embarquement;

/*Non fonctionnel doute etant sur l'une des deux 
 * dernier méthode qui non ar certain passager reste bloquer 
 * 
 * */
public class TestAvion{

	private static final int nbRangs=5;
	private static final int nbSieges=4;

	public static void main(String args[]){
		Avion avion = new Avion(nbRangs,nbSieges);
		ChefDeBord Chef = new ChefDeBord(avion);
		Thread TC = new Thread(Chef);
		Thread DebugT[] = new Thread[nbRangs*nbSieges];
		TC.start();
		
		for(int i=0;i<(nbRangs);i++) {
			for(int j=0;j<(nbSieges);j++) {
				DebugT[i]=new Thread(new Passager(avion,i,j));
				DebugT[i].start();
			}
		}
		
		try {
			for(int i = 0 ,len = DebugT.length;i<len ;i ++) {
				DebugT[i].join();
			}
			
			TC.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("***** FIN DE MAIN *****");
		
	}

}
