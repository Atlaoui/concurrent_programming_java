import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/* 3520765 HUYNH Caroline */

public class MatriceEntiere {
	private int[][] MAT;
	private int lignes,colonnes;
	
	
	public MatriceEntiere(int lignes, int colonnes) {
		 MAT =new int[lignes][colonnes];
		 this.colonnes=colonnes;
		 this.lignes=lignes;
		 
		 for(int i=0;i<this.lignes;i++) 
				for(int j=0;j<this.colonnes;j++)
					MAT[i][j]=0;						 
		 
	}
	
	public MatriceEntiere(File fichier) throws FileNotFoundException, nbLigneDifException {
		ArrayList<Integer> tab_Val = new ArrayList<Integer>(0);
		Scanner scanner = new Scanner(fichier);
		int cpt=0;
		/*Je supose ligne puis colonne*/
	    
	    if (scanner.hasNextInt()) {
            this.lignes = scanner.nextInt();
        }
	    if (scanner.hasNextInt()) {
            this.colonnes = scanner.nextInt();
        }	   
	    
	    /* Ont initialise la matrice*/
	    MAT =new int[lignes][colonnes];
		
	    
		/*Ont chope toutes les valeur de la matrice */
		while (scanner.hasNext()) {
			
	        if (scanner.hasNextInt()) {	  
	        	
	        	tab_Val.add(scanner.nextInt());
	           
	        } else {
	            scanner.next();
	        }
	    }
					
		/*Ici ont regarde si la matrice est de bonne taille en fonction du nb d'elements*/
		
		if(tab_Val.size()>(lignes*colonnes) || tab_Val.size()<(lignes*colonnes) )
			throw new nbLigneDifException(" Matrice difforme ");
		else	
			for(int i=0;i<this.lignes;i++) {
				for(int j=0;j<this.colonnes;j++) {						        	
						MAT[i][j]=tab_Val.get(cpt++);			           
											
				}
			
			}
	}
	
	/*Met la matrice passé en paramétre a zéro*/
	public void initMatriceZero() {		
		 for(int i=0;i<this.lignes;i++) 
			for(int j=0;j<this.colonnes;j++)
				this.MAT[i][j]=0;		
	}
	
	/*somme cette matrice avec la matrice m2*/
	public void SommeDeMatrice(MatriceEntiere M2) throws TaillesNonConcordantesException{
		if(this.lignes!=M2.lignes || this.colonnes!=M2.colonnes )
			throw new TaillesNonConcordantesException(" Matrice non Concordante ");
		else {
			int tmp;
			for(int i=0;i<this.lignes;i++) 
				for(int j=0;j<this.colonnes;j++) {
					tmp=this.MAT[i][j];
					this.MAT[i][j]=tmp+M2.MAT[i][j];				
				}
			}
	}

	
	/*Return la soustraction de cette matrice a M2 dans une nouvelle matrice M3*/
	public void SoustractionDeMatrice(MatriceEntiere M2) throws TaillesNonConcordantesException{
		if(this.lignes!=M2.lignes || this.colonnes!=M2.colonnes )
			throw new TaillesNonConcordantesException(" Matrice non Concordante ");
		else {
			int tmp;
			for(int i=0;i<this.lignes;i++) 
				for(int j=0;j<this.colonnes;j++) {
					tmp=this.MAT[i][j];
					this.MAT[i][j]=tmp-M2.MAT[i][j];				
				}
			}
	}
	
	public void ProduitDeMatrice(MatriceEntiere M2) throws TaillesNonConcordantesException{
		
		if(this.lignes!=M2.colonnes)
			throw new TaillesNonConcordantesException(" Matrice non Concordante ");
		else {
			MatriceEntiere M3;
			int l,c,calcul;
			/*Ont cr�e notre matrice en prenant la plus grande des deux */
			 if(this.lignes * this.colonnes < M2.lignes * M2.colonnes){
					 M3 = new MatriceEntiere(M2.lignes,M2.colonnes);	
				 }else{
					 M3 = new MatriceEntiere(this.lignes,this.colonnes);
				 }				 
			 l = 0;
		     for (int i = 0;i < this.lignes;i++){ // Ligne de M1
		    	 c = 0;
	            for (int j = 0;j < M2.colonnes;j++){ // colonne de  M2
	                calcul= 0;
	                for (int m = 0;m < this.lignes;m++)  /// colone de M1 et ligne de M2        
	                    calcul += this.MAT[i][m] * M2.MAT[m][j];
	                
	                M3.MAT[l][c] = calcul;
	                c++;
	            }
	            l++;
		     }						
			this.MAT= M3.MAT;
			this.colonnes=M3.colonnes;
			this.lignes=M3.lignes;
		}
				
	}
	
	/*Produit de matrice par un scalaire  */
	public void MatriceParScalaire ( int scalaire ) {	
		int tmp;
		for(int i=0;i<this.lignes;i++) 
			for(int j=0;j<this.colonnes;j++) {
				tmp=this.MAT[i][j];
				this.MAT[i][j]=tmp*scalaire;				
			}		
	}
	
	
	/* retourne le produit de ligne i de et colonne j de m2*/
	public static int produitLigneColonne(MatriceEntiere m1, int i, MatriceEntiere m2, int j) throws TaillesNonConcordantesException {
		if(m1.lignes!=m2.colonnes)
			throw new TaillesNonConcordantesException(" Matrice non Concordante ");
		else {
			int result=0;
			   for (int m = 0;m < m1.lignes;m++)  
				   result +=m2.MAT[m][j]*m1.MAT[i][m];
		return result;
		}
	}
		
	public int getElem(int i, int j) {		
		return MAT[i][j];
	}
	public void setElem(int i, int j, int val) {
		MAT[i][j]=val;
	}
	
	public int  getNbLignes() {
		return this.lignes;
	}
	public int  getNbColonnes() {
		return this.colonnes;
	}
	
	public void setMat(int ligne,int colonne) {
		this.lignes=ligne;
		this.colonnes=colonne;
		this.MAT =new int[lignes][colonnes];
	}
	
	public String toString() {
		String s ="";
		s+=this.colonnes+"\n";
		s+=this.lignes+"\n";
		for(int i=0;i<this.lignes;i++) {
			for(int j=0;j<this.colonnes;j++) {
				s+=MAT[i][j]+" ";
			}
			s+="\n";
		}
		return s;
	}
	
	
	/*Produit de matrice autre*/
	public MatriceEntiere ProduitDeMatrice(MatriceEntiere M1,MatriceEntiere M2) throws TaillesNonConcordantesException{
		
		if(M1.lignes!=M2.colonnes)
			throw new TaillesNonConcordantesException(" Matrice non Concordante ");
		else {
			MatriceEntiere M3;
			int l,c,calcul;
			/*Ont cr�e notre matrice en prenant la plus grande des deux */
			 if(M1.lignes * M1.colonnes < M2.lignes * M2.colonnes){
					 M3 = new MatriceEntiere(M2.lignes,M2.colonnes);	
				 }else{
					 M3 = new MatriceEntiere(M1.lignes,M1.colonnes);
				 }
				 
			 l = 0;
		     for (int i = 0;i < M1.lignes;i++){ // Ligne de M1
		    	 c = 0;
	            for (int j = 0;j < M2.colonnes;j++){ // colonne de  M2
	                calcul= 0;
	                for (int m = 0;m < M1.lignes;m++){  /// colone de M1 et ligne de M2
	        
	                    calcul += M1.MAT[i][m] * M2.MAT[m][j];
	                }

	                M3.MAT[l][c] = calcul;
	                c++;
	            }
	            l++;
		     }						
			return M3;
		}
				
	}	
}





