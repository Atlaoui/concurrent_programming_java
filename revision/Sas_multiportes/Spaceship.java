package Sas_multiportes;

public class Spaceship {
	private static int nbDoors;
	private static int nbUsers;
	public static void main(String[] args) {
		nbDoors=5;
		nbUsers=10;
		System.out.println("===================================");
		System.out.println("Airlock with"+nbDoors+" doors");
		System.out.println("Airlock used by "+nbUsers+" people");
		System.out.println("===================================");
		MultipleDoorsAirlock a = new MultipleDoorsAirlock(nbDoors);
		People p;
		Thread t;
		for(int i=0;i<nbUsers;i++) {
			p = new People(a);
			t = new Thread(p);
			t.start();
		}
	}
}
