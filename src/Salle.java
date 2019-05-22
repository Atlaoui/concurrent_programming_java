/* 3520765 HUYNH Caroline */
public class Salle {
	private boolean [][]placesLibres;
	private int nbRang;
	private int nbPlacesParRang;
	private int nbPlaceDispo;

	public Salle(int nbRang, int nbPlacesParRang) {
		this.nbRang=nbRang;
		this.nbPlacesParRang=nbPlacesParRang;
		this.placesLibres=new boolean [nbRang][nbPlacesParRang];
		this.nbPlaceDispo=nbRang*nbPlacesParRang;
		for(int i=0;i<nbRang;i++) 
			for(int j=0;j<nbPlacesParRang;j++)
				placesLibres[i][j]=true;
	}


	public  int nContiguesAuRangI(int n, int i) {	
		int cpt=0;
		int k;
		int j=0;

		while(j<nbPlacesParRang) {
			if(placesLibres[i][j]) {
				cpt=0;
				k=j;
				while(k<nbPlacesParRang &&  placesLibres[i][k]) {
					k++;						
					cpt++;
					if(cpt==n) {
						return j;
					}
				}
			}
			if(cpt!=0)
				j+=cpt;
			else
				j++;
		}
		return -1;
	}


	public  boolean reserverContigues(int n) {
		int contig;
		for(int i=0;i<nbRang;i++) {
			contig=nContiguesAuRangI(n,i);
			if(contig!=-1) {
				for(int j=contig;j<contig+n;j++) {
					placesLibres[i][j]=false;
					nbPlaceDispo--;
				}
				return true;
			}
		}
		return false;	
	}

	public synchronized boolean reserver(int n) {
		if(capaciteOK(n)){
			if(reserverContigues(n)) {
				return true;
			}else{
				int cpt=n;
				nbPlaceDispo=nbPlaceDispo-n;
				for(int i=0;i<nbRang;i++) 
					for(int j=0;j<nbPlacesParRang;j++)
						if(placesLibres[i][j]) {
							placesLibres[i][j]=false;
							cpt--;
							if(cpt==0)
								return true;
						}			
			}
		}
		return false;
	}
	
	public synchronized boolean annuler(int n) {	
		if(reserverOk(n)) {
			return false;
		}else{
			int cpt=n;
			nbPlaceDispo=nbPlaceDispo+n;
			for(int i=nbRang-1;i>=0;i--) 
				for(int j=nbPlacesParRang-1;j>=0;j--)
					if(placesLibres[i][j]==false) {
						placesLibres[i][j]=true;
						cpt--;
						if(cpt==0)
							return true;
					}			
		}
		return false;
	}

	
	public String toString() {
		String s="";
		for(int i=0;i<nbRang;i++){ 
			for(int j=0;j<nbPlacesParRang;j++) {
				if(placesLibres[i][j]){
					s+="-";
				}else {
					s+="*";
				}
			}
			s+="\n";
		}
		return s;
	}



	public boolean capaciteOK(int n) {
		if(n<=nbPlaceDispo)
			return true;
		return false;
	}
	
	public boolean reserverOk(int n) {
		if(n>=nbPlaceDispo)
			return true;
		return false;
	}
	

	public int getNbPlaceDispo() {
		return nbPlaceDispo;
	}


}

