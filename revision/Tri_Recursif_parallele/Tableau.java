package Tri_Recursif_parallele;

public class Tableau {
	private int [] tab;
	private boolean est_trier;
	private final int MAX = 50;
	private final int MIN = 1;
	
	
	
	public Tableau(int [] tab) {
		this.tab=tab;
	}
	
	public Tableau(int taille) {
		tab = new int[taille];
		est_trier=false;
	}

	public void chargeAleatoire() {
		for(int i=0,len=tab.length;i<len;i++)
			tab[i]=(int) ((Math.random()*MAX+MIN));
	}

	public int nbElements() {
		return tab.length;
	}

	public void echanger(int i, int j) {
		int temp = tab[i];
		tab[i]=tab[j];
		tab[j]=temp;
	}

	/*revoir si c'est inclus ou excule*/
	public synchronized Tableau extraire(int inf, int sup) {
		int [] tab_extrai = new int[Math.abs(sup-inf)+1];
		int cpt=0;
		System.out.print("inf :"+inf +" sup"+sup);
		for(int i=inf;i<sup;i++) {
			tab_extrai[cpt]=tab[i];
			cpt++;
			System.out.print("cpt "+cpt);
		}
		return new Tableau(tab_extrai);
	}
	
	/*faudra verifier les index*/
	public static void concatener (Tableau t1, Tableau t2, Tableau res) {
		int j= res.nbElements()/2,i, index_t1=0,index_t2=0;
		for(i=0;i<res.nbElements()/2;i++) {
			res.tab[i]=t1.tab[index_t1++];
			res.tab[j]=t2.tab[index_t2++];
		}
	}

	public void afficher() {
		System.out.print("Tableau [ ");
		for(int i=0,len=tab.length;i<len;i++)
			System.out.print(tab[i]+",");
		System.out.println(" ]");
	}
	
	/*proute*/
	public synchronized void attendre() throws InterruptedException {
		while(!est_trier)
			wait();
	}
	
	public synchronized void estPret() {
		notifyAll();
	}

	public int lire(int inf) {
		return tab[inf];
	}

}
