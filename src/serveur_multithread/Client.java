package serveur_multithread;

public class Client implements Runnable{
	private ReponseRequete repreq;
	private Serveur serveur;
	private boolean att_Traitement;
	private final int idc;
	private final int type;
	public Client(Serveur s,int id) {
		this.serveur=s;
		this.idc=id;
		att_Traitement=false;
		if(id%3!=0)
			this.type=1;
		else
			this.type=2;
	}

	public synchronized void requeteServie(ReponseRequete r) {
		att_Traitement=true;
		System.out.println("R: "+r.toString());
		notify();
	}

	private synchronized void att_rep() throws InterruptedException {
		while(att_Traitement==false) {
			System.out.println("Client " + idc + ": attend");
			wait();
		}
	}

	@Override
	public void run(){
		for(int i=0;i<5;i++)
			try {
				System.out.println("D : le Client"+idc+" a soumis ca requette num "+i+" de type "+type);
				serveur.soumettre(this, i, type);
				att_rep();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
