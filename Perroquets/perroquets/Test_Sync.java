package perroquets;

public class Test_Sync {

	public static void main(String[] args) {
		int nbp=3;
		Perroquet p1,p2,p3;
		Thread t1,t2,t3;
		Synchroniseur s ;
		
		s= new Synchroniseur(nbp); 
		p1=new Perroquet("Coco",s);
		p2=new Perroquet("Rico",s);
		p3=new Perroquet("Jaco",s);
		
		t1=new Thread(p1);
		t1.start();
		
		t2=new Thread(p2);
		t2.start();
		
		t3=new Thread(p3);
		t3.start();
		
		
		try {			
			Thread.sleep(2000);
			s.premierReveil(0);
			
			
			//Thread.sleep(20000);
		//	t1.interrupt();
		//	t2.interrupt();
		//	t3.interrupt();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("****** FIN DU MAIN *****");
		
	}

}
