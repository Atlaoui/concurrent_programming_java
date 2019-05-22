package les_acenseur_individuel;
class Requete {
	private UnUsager unUsager;
	private int unEtage;

	Requete(UnUsager u, int e) {
		unUsager= u;
		unEtage = e;
	}

	public int etage() {
		return unEtage;
	}

	public UnUsager usager() {
		return unUsager;
	}

	@Override
	public String toString() {
		return "(U"+ unUsager + ", " + unEtage + ")";
	}
} 