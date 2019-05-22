import java.io.File;
import java.io.FileNotFoundException;
/* 3520765 HUYNH Caroline */
public class Main_Affiche_Matrice {
	public static void main(String[]args) {
		
		File f = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_produit1");
		/*File f1 = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_produit2");
		File f2 = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_somme1");
		File f3 = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_somme2");
		File f4 = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_somme1");
		File f5 = new File ("/Infos/lmd/2018/licence/ue/3I001-2018oct/TME/TME1/donnees_somme2");
		*/
		File f1 = new File ("/users/Etu9/3524869/Téléchargements/3i001/Matrice1");
		File f2 = new File ("/users/Etu9/3524869/Téléchargements/3i001/Matrice2");
		
				
		try {
		
			MatriceEntiere m1=new MatriceEntiere(f1);
			MatriceEntiere m2=new MatriceEntiere(f2);
			MatriceEntiere m=new MatriceEntiere(f);
			try {
				m1.ProduitDeMatrice(m2);
			} catch (TaillesNonConcordantesException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(m1.toString());
			
		} catch (FileNotFoundException | nbLigneDifException e) {
		
			e.printStackTrace();
		}
		
		
		
	
	}

}