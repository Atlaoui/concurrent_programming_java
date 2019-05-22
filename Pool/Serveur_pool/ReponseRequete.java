/* 3520765 HUYNH Caroline */
package Serveur_pool;



public class ReponseRequete {
	private int numero;
	private Client client;
	private int valeur;
	public ReponseRequete(Client clienc, int numero) {
		this.numero=numero;
		this.client = clienc;

		this.valeur = (int)(Math.random()*(50));
	}

	public String toString() {
		return "id :"+numero+" Client :"+client.toString()+" resultat :"+valeur;
	}
	
	public Client getC() {
		return client;
	}
	
}
