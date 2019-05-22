package optimisation_LEM;

import java.util.ArrayList;
import java.util.Iterator;

public class EnsembleDonnees {
	private ArrayList<Integer> tab_donnees;

	public EnsembleDonnees() {

		tab_donnees= new ArrayList<>();
	}


	/*Pour que le producteur puisse ajouter */
	public void add_in(Integer i) {
		tab_donnees.add(i);
	}

	/*fonction de supression de lelem*/
	public void delete_elem(Integer e)throws PasLelemExeption {

		for(int i=tab_donnees.size()-1;i>=0;i--)
			if(tab_donnees.get(i)==e) {
				tab_donnees.remove(i);
				return;
			}

		throw new PasLelemExeption("pas d'eleme");

	}

	public String toString() {
		Iterator<Integer> itr = tab_donnees.iterator();
		String S="( ";
		while (itr.hasNext()) 
			S += " "+itr.next();

		S+=" )";
		return S;

	}
	
	/* Fonction qui va donner un elemen aléatoir du tableau d'elem*/
	public int tir_alea_obj() {
		return (int)((Math.random()*(tab_donnees.size())));
	}
	
	
	public Integer getElmbi(int i) {
		if(i>=0 && i<tab_donnees.size())
			return tab_donnees.get(i);
		else
			return -1;
	}

}



