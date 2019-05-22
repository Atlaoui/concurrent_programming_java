package exam_corriger;

public class Requete {
	private int e;
	private UnUsager u;
	
	public Requete (int i, UnUsager s){
		this.e=i;
		this.u=s;
	}
	
	public int getE(){return e;}
	public UnUsager getU(){return u;}
}
