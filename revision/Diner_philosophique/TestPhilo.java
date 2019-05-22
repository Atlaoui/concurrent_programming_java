package Diner_philosophique;

public class TestPhilo {

	public static void main(String[] args) {
		int N = 5,i,cpt=0;
		ChopStick[] chopTab= new ChopStick[N];
		Philo[] philoTab = new Philo[N];
		Thread [] ThreadTab = new Thread[N];
		for(i=0;i<N;i++) 
			chopTab[i]=new ChopStick();
		for(i=0;i<N;i++) {
			philoTab[i]=new Philo(chopTab[cpt++%N],chopTab[cpt%N]);
			ThreadTab[i]=new Thread(philoTab[i]);
			ThreadTab[i].start();
		}
	}

}
