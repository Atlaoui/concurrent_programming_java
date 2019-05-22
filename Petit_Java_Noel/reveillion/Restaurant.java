package reveillion;

public class Restaurant {
	private int nbtable;
	
	public Restaurant(int nbtable) {
		this.nbtable=nbtable;
	}
	
	public synchronized  Integer reserver(int nb_personnes) {
		if(nb_personnes>nbtable*2)
			return null;
		else {
			int num=nbtable;
			nbtable-=(nb_personnes/2)+(nb_personnes%2);
			return new Integer(num);
		}
	}
}
