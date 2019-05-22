package exam_2016;

import java.util.ArrayList;

public class Marcheur implements Runnable {
	private UnNoeud unNoeud;
	private final Integer  id;
	private static int cpt=1;
	/*Q12: non car le remove est la dernier instruction du marcheur et par consequent au mement il enleve sont id de l'array il n'est plus actif  */
	private static final Object mutex = new Object();
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	
	public Marcheur(UnNoeud unNoeud) {
		this.unNoeud=unNoeud;
		synchronized(mutex){
			id=new Integer(cpt++);
		list.add(id);
		}

	}

	/*Q10*/
	@Override
	public void run() {
		unNoeud.marquer(id);
		for(int i =0;i<unNoeud.nbSucc();i++)
			if(unNoeud.succ(i).marque()==0)
			new Thread (new Marcheur(unNoeud.succ(i))).start();
		/*pas sur que ce soit necessaire */
		synchronized(mutex){
			Marcheur.list.remove(id);
		}
	}
	/*Q9*/
	public static boolean encoreDesMarcheurs() {
		synchronized(mutex){
		return !(Marcheur.list.size()==0);
		}
	}
	
	
	@Override
	public String toString() {
		String S="Marcheur d'id "+id+" a visiter les Noeud :"+'\n';
		for(int i = 0 , len =list.size() ;i< len ;i++)
			S+=" "+list.get(i);	
		return S;
	}

}
