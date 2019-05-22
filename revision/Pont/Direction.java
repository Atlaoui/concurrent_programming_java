package Pont;

public enum Direction {
	NORD_SUD(0), SUD_NORD(1);
	private Direction(int index) {
		this.index = index;
	}
	private int index;
	
	public int index() {return index;};
	
	public String toString() {
		if(index==0)
			return "NORD_SUD";
		else
			return "SUD_NORD";
	}
}



