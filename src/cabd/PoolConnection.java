package cabd;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class PoolConnection {
	public static  ArrayList<Connection> aConnections = new ArrayList<Connection>();
	public static Connections cons = new Connections();
	static Scanner input = new Scanner (System.in);


	public static void main(String[] args) {
			try {
	 			for(int i=0; i<20; i++) {
					aConnections.add(cons.Connect());
				}
	 			System.out.println("POOL INITIALIZED");
	 			System.out.println("Connections available: "+aConnections.size()+"\n");
				System.out.println("How many clients would you like to create?");
				int NUM_OF_THREADS = input.nextInt();
				Thread[] threadList = new Thread[NUM_OF_THREADS];
				for (int i=0; i<NUM_OF_THREADS; i++) {
				threadList[i]= new ConnectionHandler();
				threadList[i].start();
				Thread.sleep(600);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}   	