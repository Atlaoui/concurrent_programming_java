package serveur_multithread;

public class ReponseRequete {
	private int numero;
	private Client client;
	private int valeur;
	public ReponseRequete(Client client, int numero) {
		this.numero=numero;
		this.client = client;
		this.valeur = (int)(Math.random()*(50));
	}
	
	public String toString() {
		return "id :"+numero+" Client :"+client.toString()+" resultat :"+valeur;
	}
}
