
package gestionaireVitre;

public class MoteurVitre implements Runnable {
	private final Cote c;
	private Position position;
	private Operation operation;
	private boolean Demandedop;
	private boolean alumeM;
	
	public MoteurVitre(Cote c) {
		this.c = c;
		this.position = Position.HAUTE;
		this.operation = Operation.NIL;
		this.Demandedop=false;
		this.alumeM=true;
	}
	
	@Override
	public void run() {
	
		while(alumeM) {
			attendre();
				
			try{
			if(this.action()) {	
				synchronized(this) {
				wait(1000);
				System.out.println("L'opération a pris " + 1 + " s.");
				}
				}
			}catch(InterruptedException e){
				System.out.println(e.getMessage());	
			}
			
		}
	}
	
	private synchronized boolean  action(){
		if (operation == Operation.MONTER && position == Position.BASSE ){
			position = Position.HAUTE;
			System.out.println("La vitre " + this.afficheCote() + "est montée");
			this.Demandedop = false;
			this.operation = Operation.NIL;
			return true;
		}else{
			if (operation == Operation.DESCENDRE && position == Position.HAUTE){
				position = Position.BASSE;
				System.out.println("La vitre " + this.afficheCote() + " va être descendue");
				this.Demandedop = false;
				this.operation = Operation.NIL;
				return true;
			}
			else
				return false;
		}
	}
	
	
	private synchronized void attendre() {
		if (Demandedop!=true){
			try{
				System.out.println("Le moteur " + this.afficheCote() + " attend.");
				wait();
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
		}	
	}
	
	public synchronized void demander(Operation operation) {
		setOperation(operation);
		this.Demandedop = true;	
		notifyAll();		
	}
	
	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Cote getC() {
		return c;
	}
	
	public void eteindreM() {
		this.alumeM=false;
	}
	
	public boolean getAlum() {
		return this.alumeM;
	}
	
	public String afficheCote() {
		if(this.c==Cote.DROITE)
			return "Droite";
		else
			return "Gauche";	
	}
}
