package Sas_multiportes;

public class BigProblem extends Exception {
	private String msg ="<no message>";
	
	BigProblem(String s){
		msg = s;
	}
	
	@Override
	public String toString() {
		return "BigProblem: "+msg;	
	}

}
