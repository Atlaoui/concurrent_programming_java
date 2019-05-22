package passage_a_niveau;

public class UneVoiture implements Runnable {
	
	/*Q4 : */
	private Passage passNiveau;
	private final int id;
	private static int cpt=1;
	private final static Object o = new Object();
	public UneVoiture(Passage passNiveau) {
		synchronized(o) {
			id=cpt++;
		}
		this.passNiveau=passNiveau;
	}
	
	/*Q3*/
	@Override
	public void run() {
		System.out.println(this.toString()+"j'arrive");
		passNiveau.voitureArrive(id);
		System.out.println(this.toString()+"je part");
	}
	
	
	@Override
	public String toString() {
		return "V"+id+" :";
	}

}
