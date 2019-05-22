package gestionnaireConvoyeur;

public class AleaStock {
	private AleaObjet []stock;
	private int taille;
	private int nbElem;
	private int Max,Min;
	
	public AleaStock(int taille) {
		Min=5;
		Max=10;
		this.taille=taille;
		nbElem=taille-1;
		stock=new AleaObjet[taille];
		
		for(int i=0;i<taille;i++)
			stock[i]=new AleaObjet(Min,Max);  
		
		
	}
	
	public AleaStock(int taille ,int min ,int max) {
		this.Max=max;
		this.Min=min;
		this.taille=taille;
		nbElem=taille-1;
		stock=new AleaObjet[taille];
		
		for(int i=0;i<taille;i++)
			stock[i]=new AleaObjet(Min,Max);  
		
	}
	
	
	public AleaObjet getElem(){
		if(nbElem>=0) {
			AleaObjet obj = stock[nbElem];
			if(nbElem!=0)
				nbElem--;
			return obj;
		}
		return null;
		}
	
	
	public boolean pasVide(){
		return (nbElem > 0);
	}
	
	
	public int getTaille() {
		return taille;
	}

}
