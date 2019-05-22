package King_Lock;


public enum Position {
	NORD(0), SUD(1);
	private Position(int index) {
		this.index = index;
	}
	private final int index;
	public int index() {return index;};
	
	public String toString() {
		if(index==0)
			return "NORD";
		else
			return "SUD";
	}

}
