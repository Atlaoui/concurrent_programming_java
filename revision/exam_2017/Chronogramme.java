package exam_2017;
public class Chronogramme {
	private static int taille;

	public static void main (String []args) {
		try {
			taille =3;//Integer.parseInt(args[0]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.err.println ("un parametre est requis");
			return;
		}
		Ressource[] tr = new Ressource[taille];
		for (int i =0 ; i < taille ; i++) {
			tr[i] = new Ressource("R"+i, taille);
		}
		for (int i=0 ; i < taille ; i++) {
			new Thread(new Utilisateur(tr)).start();
		}
	}
}