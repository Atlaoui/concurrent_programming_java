package passage_a_niveau;

public class UnTrain implements Runnable {
	
	/*Q5*/
	/*Q6 : */
	private Passage passNiveau;
	private final int id;
	private static int cpt=1;
	private final static Object o = new Object();
	public UnTrain(Passage passNiveau) {
		synchronized(o) {
			id=cpt++;
		}
		this.passNiveau=passNiveau;
	}

	

	@Override
	public void run() {
		try {
			System.out.println(this.toString()+"j'arrive");
			passNiveau.trainArrive(id);
			passage();
			passNiveau.trainPart(id);
			System.out.println(this.toString()+"je part");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public String toString() {
		return "T"+id+" :";
	}
	
	private void passage() throws InterruptedException {
		Thread.sleep(3000);
	}

}
