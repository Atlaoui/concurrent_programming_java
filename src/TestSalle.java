/* 3520765 HUYNH Caroline */
public class TestSalle {

	public static void main(String[] args) {
		int nbRang=10;
		int nbPlacesParRang=10;
		Salle s=new Salle(nbRang,nbPlacesParRang);
		Thread []t;
		t= new Thread[10];
		/*
		for (int j = 0;j < 8;j++)
			s.reserver(6);
		

		for (int j = 0;j < 8;j++)
			s.annuler(6);
		*/
		
		for (int i = 0;i < 10;i++) {
			t[i]=new Thread(new Groupe(s));
			t[i].start();
	
		}
		
		for (int i = 0;i < 10;i++)
		try {
			t[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			//((Thread) new Thread(new Groupe(s))).start();
		System.out.println(s.toString());
		System.out.println(s.getNbPlaceDispo());
		
	}


}
