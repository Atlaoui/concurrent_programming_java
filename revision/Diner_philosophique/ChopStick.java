package Diner_philosophique;

import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
	private ReentrantLock lock;
	public ChopStick() {
		lock= new ReentrantLock(); 
	}
	
	public boolean take() {
		return lock.tryLock();
	}
	
	public void relase() {
		lock.unlock();
	}

}
