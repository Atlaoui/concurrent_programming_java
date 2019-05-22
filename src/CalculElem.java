/* 3520765 HUYNH Caroline */
public class CalculElem implements Runnable {
	
	private MatriceEntiere m1;
	private MatriceEntiere m2;
	private MatriceEntiere m3;
	private int iL,jL,l,c;
	
	public CalculElem(MatriceEntiere m1, MatriceEntiere m2, MatriceEntiere m3,int iL ,int jL,int l ,int c) {
		this.m1=m1;
		this.m2=m2;
		this.m3=m3;
		this.iL=iL;
		this.jL=jL;
		this.l=l;
		this.c=c;		
	}
	
	@Override
	public void run() {		
		try {
			m3.setElem(l, c, MatriceEntiere.produitLigneColonne(m1, iL, m2, jL));
		} catch (TaillesNonConcordantesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
