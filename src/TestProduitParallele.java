import java.io.File;
import java.io.FileNotFoundException;
/* 3520765 HUYNH Caroline */
public class TestProduitParallele {

	public static void main(String[] args) {
		
		File f1 = new File ("/users/Etu9/3524869/Téléchargements/3i001/Matrice1");
		File f2 = new File ("/users/Etu9/3524869/Téléchargements/3i001/Matrice2");
		int l,c;
		
				
		try {
			MatriceEntiere m1=new MatriceEntiere(f1);
			MatriceEntiere m2=new MatriceEntiere(f2);
			MatriceEntiere m3=new MatriceEntiere(m1.getNbLignes(),m2.getNbColonnes());
			
			l=0;
			for (int i = 0;i < m1.getNbLignes();i++) {
				 c = 0;
		            for (int j = 0;j < m2.getNbColonnes();j++)
		            	 ((Thread) new Thread(new CalculElem(m1,m2,m3,j,i,c++,l))).start();
		       l++;
			}
			System.out.println(m3.toString());
			
		} catch (FileNotFoundException | nbLigneDifException e) {
		
			e.printStackTrace();
		}
	}
}
