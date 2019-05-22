package Fibonaci_parallel;

public class Fibonacci {
	private static int n=8;
	public Fibonacci() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main (String []Jargs) {
		Cache c =new Cache();
		Thread t = new Thread(new Fibo(c,n));
		t.start();
		try {
			t.join();
			System.out.println(c.affiche());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
