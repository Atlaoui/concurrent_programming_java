package exam_corriger;
import java.util.ArrayList;
import java.util.List;

public class SystemeAppel {
	private List<Requete> re =new ArrayList<Requete>();
	
	public SystemeAppel(){}
	
	public void appelerAscenseur(int e,UnUsager u)throws InterruptedException{
		synchronized(this){
			re.add(new Requete(e,u));
			notifyAll();
		}
	}
	
	public Requete recupererRequete()throws InterruptedException{
		synchronized(this){
			Requete r=null;
			while(re.size()==0)wait();
			r=re.remove(0);
			return r;
		}
	}
}
