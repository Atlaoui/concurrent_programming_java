package gestion_de_train;

public class PoolHangars {
	private final int NBH=17;
	private Hangar [] ph;
	public PoolHangars() {
		ph= new Hangar[NBH];
		for(int i=0;i<NBH;i++)
			ph[i]=new Hangar(i+1);
	}
	
	public Hangar getHangar(int id) {
		if(id>=0 && id<NBH)
			return ph[id];
		else
			return null;
	}

	public int libre() {
		for (int i = 0; i < NBH; i++) {
			if(ph[i].isLibre())
				return i;
		}
		return -1;
	}

}
