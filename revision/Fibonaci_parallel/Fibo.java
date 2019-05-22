package Fibonaci_parallel;

public class Fibo implements Runnable {
	private Cache cache;
	private int n;
	private int res;
	public Fibo (Cache c,int n){
		this.cache=c;
		this.n=n;
		res=0;
	}


	@Override
	public void run() {
		switch(n) {
		case 0:
			res=0;
			break;
		case 1:
			res=1;
			break;
		default :
			int answer;
			try {
				answer = cache.readFromCache(n);
				if(answer==-1) {
					Fibo f1 = new Fibo(cache,n-1);
	                Fibo f2 = new Fibo(cache,n-2);
	                Thread Tf1 = new Thread(f1);
	                Thread Tf2 = new Thread(f2);
	                Tf1.start();
	                Tf2.start();
	                Tf1.join();
	                Tf2.join();
	                res = f1.getRes() + f2.getRes();
	                cache.writeInCache(res,n);
				}else {
					res=answer;
				}
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getRes() {
		return res;
	}
}
