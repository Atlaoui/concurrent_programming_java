package fractale_concuren;

import graphic.Window;
import java.awt.Point;

public class VonKochMono implements Runnable{
	private final static double LG_MIN = 8.0;
	private final int POS;
	private Window f;
	private Point a,b,c;

	public VonKochMono (Window f, Point a, Point b, Point c) {
		this.f = f;
		POS=-1;
		//new Cote(f, b, a).tracer();
		//new Cote(f, a, c).tracer();
		//new Cote(f, c, b).tracer();
	}
	
	public VonKochMono (Window f, Point a, Point b, Point c,int pos) {
		this.f = f;
		this.a=a;
		this.b=b;
		this.c=c;
		POS=pos;
	}

	@Override
	public void run() {
		if(POS==0)
			new Thread(new Cote(f, b, a)).start();
		else
			if(POS==1)
				new Thread(new Cote(f, a, c)).start();
			else
				new Thread(new Cote(f, c, b)).start();	
	}			
}