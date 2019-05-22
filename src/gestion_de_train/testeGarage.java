package gestion_de_train;

public class testeGarage {
	private static final int NBLOCO=5;
	public static void main(String[] args) {
		PoolHangars pH= new PoolHangars();
		SegAccueil sA = new SegAccueil();
		SegTournant sT = new SegTournant(pH);
		Thread ThsT = new Thread(sT);
		ThsT.start();
		
		for(int i=0;i<NBLOCO;i++) 
			((Thread) new Thread(new Loco(sA,sT,pH))).start();
		
		System.out.println("*** FIN DU MAIN ***");
		
		

	
		
	}
}