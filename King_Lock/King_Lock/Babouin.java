package King_Lock;

public class Babouin implements Runnable {
	protected Corde laCorde;
	protected Position position;
	private final int id;
	private static int cpt=1;
	private static Object mutex = new Object();
	public Babouin(Corde laCorde ,Position p) {
		synchronized(mutex){
			id=cpt++;
		}
		this.laCorde=laCorde;
		this.position=p;
	}
	@Override
	public void run() {
		try {
			//laCorde.acceder_sem(position);
			laCorde.acceder_lock(position);
			System.out.println(this.toString() + " a pris la corde.");
			traverser();
			System.out.println(this.toString() + " est arrive.");
			//laCorde.lacher_sem(position);
			laCorde.lacher_lock(position);
		}
		catch (InterruptedException e) {
			System.out.println("Pb babouin !");
		}
	}
	protected void traverser() throws InterruptedException {
		Thread.sleep((int)(Math.random()*(500))+1);
	}

	public String toString() {
		return "Le babouin "+id+" qui vien du "+position.toString();
	}

	public int getId() {
		return id;
	}
	
	public Position getPos() {
		return position;
	}
	
	public void setPos(Position position) {
		this.position=position;
	}
	
	
}
