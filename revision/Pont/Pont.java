package Pont;


import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Pont {
	private Feu f_gauche;
	private Feu f_droite;
	private Direction direct_actuel;
	/*teste avec ReentrantReadWriteLock*/
	private ReentrantReadWriteLock lock;

	public Pont(Feu fdroite,Feu fgauche) {
		this.f_gauche=fgauche;
		this.f_droite=fdroite;
		lock =new ReentrantReadWriteLock();
	}

	public void changement_feu() {
		try {
			lock.writeLock().lock();
			if(f_gauche.index()%2==0) {
				f_gauche.set_vert();
				f_droite.set_rouge();
			}else {
				f_gauche.set_rouge();
				f_droite.set_vert();
			}
		}finally {
			lock.writeLock().unlock();
		}
	}
	
	public void traverser() {
		try {
			lock.readLock().lock();
			
			
			
		}finally {
			lock.readLock().unlock();
		}
	}
	
	public Feu getF_gauche() {
		return f_gauche;
	}

	public void setF_gauche(Feu f_gauche) {
		this.f_gauche = f_gauche;
	}

	public Feu getF_droite() {
		return f_droite;
	}

	public void setF_droite(Feu f_droite) {
		this.f_droite = f_droite;
	}
}
