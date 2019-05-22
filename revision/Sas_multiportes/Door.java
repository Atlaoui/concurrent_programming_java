package Sas_multiportes;

public class Door {
	private boolean closed = true;
	private final int id;
	public Door(int myId) {
		id=myId;
	}

	public void open() throws BigProblem {
		if(closed) {
			closed = false;
			System.out.println("Door "+this.id+" opened");
		}else {
			throw new BigProblem("### door "+this.id+" already closed");
		}
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}
}
