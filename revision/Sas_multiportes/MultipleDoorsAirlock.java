package Sas_multiportes;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MultipleDoorsAirlock {
	private final int numberOfDoors;//number of doors in the airlock
	private Door[] theDoors;//array of doors
	private boolean occupied=false;//true if somebody is in the airlock
	private ReentrantLock l = new ReentrantLock();
	private Condition[] waitEnter;
	private int[] waitToEnter;
	
	public MultipleDoorsAirlock(int nbDoors) {
		numberOfDoors=nbDoors;
		theDoors = new Door[nbDoors];
		waitEnter= new Condition[nbDoors];
		waitToEnter = new int[nbDoors];
		for(int i=0;i<nbDoors;i++) {
			theDoors[i]= new Door(i);
			waitEnter[i] = l.newCondition();
			waitToEnter[i] = 0;
		}
	}

	public void passAirlock(int who , int enteringDoor , int exitingDoor) throws BigProblem{
		
	}
	
	private void enterAirLock(int who,int byDoor) {
		l.lock();
		try {
			//I must wait until no door is opend , or, if one is opend it no mine , and the airlock is empty
			waitToEnter[byDoor]++;//I am potentially waitting in front of door
			while(occupied) {//the airlock is not empty
				System.out.println("cannot enter (occupied = "+occupied+")");
				waitEnter[byDoor].await();
			}
			// Open the entry door that was closed
			theDoors[byDoor].open();
			System.out.println(displayWho(who)+" opended entry door "+byDoor);
			occupied=true;//the airlock is now filled
			waitToEnter[byDoor]--;// I no more wait in front of the door
			theDoors[byDoor].close();// i now close the door
			System.out.println(displayWho(who)+" closed entry door "+byDoor);
			
		} catch (InterruptedException | BigProblem e) {
			e.printStackTrace();
		}finally {
			l.unlock();
		}
	}
	

	private void exitAirLock(int who ,int byDoor, int enteredBy) {
		l.lock();
		try {
			//Open the exit door that was closed
			theDoors[byDoor].open();
			System.out.println(displayWho(who)+" opended exit door "+byDoor);
			occupied=false;
			theDoors[byDoor].close();
			System.out.println(displayWho(who)+" closed exit door "+byDoor);
			System.out.println("exited (occupied = "+occupied+")");
			
			//-------------------------------------
			//Since we are out, we must wake up potential people waiting to enter (round robbing strategy)
			for(int i =1; i<= numberOfDoors ;i++) {
				if(waitToEnter[(enteredBy+i)%numberOfDoors]>0) {
					waitEnter[(enteredBy+i)%numberOfDoors].signalAll();
					break;//Stop exploration since we woke up peoples
				}
			}
			
		} catch (BigProblem e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			l.unlock();
		}
	}
	
	private String displayWho(int who) {
		return "People "+who;
	}
	
	public int getMaxDoors() {
		return numberOfDoors;
	}
}
