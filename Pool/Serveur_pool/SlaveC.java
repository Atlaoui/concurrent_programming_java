/* 3520765 HUYNH Caroline */
package Serveur_pool;

import java.util.concurrent.Callable;


public class SlaveC implements Callable<ReponseRequete> {
	private Client clienc;
	private int idc;
	private int type;
	public SlaveC(Client clienc,int idc) {
		this.idc=idc;
		this.clienc=clienc;
	}

	@Override
	public ReponseRequete call() throws Exception {
		return new ReponseRequete(clienc,idc);
	}

}
