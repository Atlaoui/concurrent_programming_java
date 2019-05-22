package Fibonaci_parallel;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Cache {
	ArrayList<Integer> parameters = new ArrayList<Integer>(); // contient les clefs 
	ArrayList<Integer> values = new ArrayList<Integer>(); // contient les valeurs associees aux clefs 
	private ReentrantLock l= new ReentrantLock(); // verrou et 
	private Condition threadQueue = l.newCondition(); // condition pour bloquer les threads 
	private int reading = 0; // lectures en cours 
	private int writeDemanded = 0; // ecritures demandees /elles sont prioritaires) 
	private boolean writing = false; // ecriture en cours 


	/*Q1: non car les valeur sont initialiser et ont a pas besoin de reférence externe*/
	public void writeInCache(int val, int key) throws InterruptedException {
		try {
			l.lock();
			writeDemanded++;
			while(writing || reading !=0)
				threadQueue.await();
			writing=true;
			// je ne sait pas si c'est pas mieux de le metre a l'exterieur du lock
			if(!parameters.contains(key)) {//pas sur pour l'argument
				parameters.add(new Integer(key));
				values.add(new Integer(val));
			}
			writing=false;
			writeDemanded--;
			threadQueue.signalAll();
		}finally {
			l.unlock();
		}
	}

	public int readFromCache (int key) throws InterruptedException {
		try {
			l.lock();
			reading++;
			while(writeDemanded!=0)
				threadQueue.await();
			if(!parameters.contains(key)) 
				return -1;
			return values.get(parameters.indexOf(key));

		}finally {
			
			reading--;
			l.unlock();
		}

	}

	public String affiche() {
		String S="";
		for(int i =0 , len = parameters.size();i<len;i++ ) {
			S+="n: "+parameters.get(i);
			S+=" val: "+values.get(parameters.indexOf(parameters.get(i)));
			S+='\n';
		}
		return S;
	}
}
