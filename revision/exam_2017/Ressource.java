package exam_2017;

import java.util.concurrent.Semaphore;

class Ressource {
	private String monNom;
	private Semaphore s;

	Ressource (String nom, int copies) {
		monNom = nom;
		s = new Semaphore(copies);
	}
	public void reserver (int nb, int pour) throws InterruptedException {
		System.out.println("Acquisition de "+nb+" copies de "+monNom+" pour "+pour);
		s.acquire(nb);
	}
	public void relacher (int par) {
		System.out.println("Relache d'un "+monNom+" par "+par);
		s.release();
	}
}