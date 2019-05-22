package King_Lock;

public class BaboinKong extends Babouin {

	/*commen gerer l'accer de Kong*/
	public BaboinKong(Corde laCorde ,Position p) {
		super(laCorde,p);
	}

	@Override
	public void run() {
		try {
			super.laCorde.accederKong_lock();
			//super.laCorde.accederKong_sem(position);
			System.out.println(super.toString() + " a pris la corde et empeche les autre de monter.");
			super.traverser();
			setKongPos();
			System.out.println(this.toString() + " laisse les autre passer .");
			super.laCorde.lacherKong_lock();
			//super.laCorde.lacherKong_sem(position);
		}catch (InterruptedException e) {
			System.out.println("Pb Kong !");
		}

	}
	@Override
	public String toString() {
		return "le Roi Kong "+super.getId()+" est arriver au "+position.toString();
	}
	
	
	private void setKongPos() {
		if(this.position.index()==0)
			this.position=Position.SUD;
		else
			this.position=Position.NORD;
	}
}
