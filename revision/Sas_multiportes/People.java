package Sas_multiportes;

import java.util.Random;

public class People implements Runnable {
	private Random gen= new Random();
	private final int myId;
	private MultipleDoorsAirlock theAirlock;
	private static int autoNum =1;
	
	private static Object mutex = new Object(); // might be useful ;-)
	
	private int enteringBy;
	private int exitingBy;
	
	public People(MultipleDoorsAirlock a) {
		theAirlock=a;
		synchronized(mutex) {
			myId=autoNum++;
		}
		enteringBy=gen.nextInt(a.getMaxDoors());
		do {
			exitingBy=gen.nextInt(a.getMaxDoors());
		}while(enteringBy==exitingBy);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(gen.nextInt(1000));//to generate delays
			System.out.println("I am P"+myId+" who wats to enter by door"+enteringBy+" and exit by"+exitingBy);
			theAirlock.passAirlock(myId, enteringBy, exitingBy);
			System.out.println(myId+" has passed the airlock");
		} catch (BigProblem | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
