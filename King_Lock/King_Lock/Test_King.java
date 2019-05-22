package King_Lock;

public class Test_King {

	public static void main(String[] args) {
		System.out.println("****** DEBUT DU MAIN ******");
		Corde c =new Corde();
		int i ,nbBaboins=50;
		Thread tabT[] = new Thread[nbBaboins];
		BaboinKong KingKong =new BaboinKong(c,Position.NORD);
		Thread TR= new Thread(KingKong);
		
		
		for( i=0;i<nbBaboins;i++) {
			if(i%2==0) {
				tabT[i]=new Thread(new Babouin(c,Position.NORD));
				tabT[i].start();
			}else {
				tabT[i]=new Thread(new Babouin(c,Position.SUD));
				tabT[i].start();
			}
			
		}

		TR.start();
		for( i=0;i<nbBaboins;i++)
			try {
				tabT[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		try {
			TR.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("****** FIN DU MAIN ******");
	}
}
