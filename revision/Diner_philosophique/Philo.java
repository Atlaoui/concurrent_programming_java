package Diner_philosophique;

public class Philo implements Runnable {
	private ChopStick left,right;
	private boolean leftlock,rightlock;
	private final int id;
	private static int cpt=1;
	private final static  Object  mutex = new Object();
	public Philo( ChopStick left, ChopStick right) {
		synchronized(mutex) {
			id=cpt++;
		}
		this.left=left;
		this.right=right;
	}

	@Override
	public void run() {
		do {

			try{
				leftlock=left.take();
				att_entre_baton();
				rightlock=right.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				if(!leftlock || !rightlock) {
					if(leftlock)
						left.relase();
					if(rightlock)
						right.relase();
				}
			}

		}while(!leftlock || !rightlock);
		
		try {
			mange();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			right.relase();
			left.relase();
		}
	}
	
	private void mange() throws InterruptedException {
		Thread.sleep((int)(Math.random()*(500))+1);
		System.out.println(toString()+" a enfin manger");
	}
	
	private void  att_entre_baton() throws InterruptedException {
		Thread.sleep((int)(Math.random()*(50))+1);
	}
	
	
	@Override
	public String toString() {
		return "P"+id;
	}
	

}
