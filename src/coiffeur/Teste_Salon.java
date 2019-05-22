package coiffeur;

public class Teste_Salon {

	public static void main(String[] args) {
		
		Salon s=new Salon(10);
		Thread[] t=new Thread[10]; 
		for(int i=0;i<4;i++) {
			t[i]=new Thread(new Client(s));
			t[i].start();
			
		}
		for(int i=0;i<4;i++) {
			try {
				t[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		s.BarbierGoHome();
	}

}
