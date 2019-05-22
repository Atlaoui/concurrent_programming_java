package traineau;

public class test_Noel {

	public static void main(String[] args) {
		System.out.println("****** DEBUT DU MAIN *******");
		int NB_TOTAL_RENNES=9,i;
		PereNoel p= new PereNoel(NB_TOTAL_RENNES);
		Thread tp = new Thread(p);
		tp.start();
		Thread [] rennes = new Thread[NB_TOTAL_RENNES];
		
		for(i=0;i<NB_TOTAL_RENNES;i++) {	
			rennes[i]=new Thread(new Renne(p));
			rennes[i].start();
		}
		try {
			tp.join();
			for(i=0;i<NB_TOTAL_RENNES;i++)
				rennes[i].join();
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("****** FIN DE MAIN *******");
	}

}
