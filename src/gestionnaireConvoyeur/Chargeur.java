package gestionnaireConvoyeur;

public class Chargeur implements Runnable {
	 
	private Chariot chariot;
	private AleaStock stock;
	
	public Chargeur(Chariot chariot, AleaStock stock){
		this.chariot = chariot;
		this.stock = stock;
	}
	
	
	
	@Override
	public void run() {
		while(stock.pasVide()) {
			
		}
		
	}

}