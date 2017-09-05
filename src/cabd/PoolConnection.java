package cabd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PoolConnection {
	Pool pool_config = new Pool();
	ArrayList<Connection> aConnections = new ArrayList<Connection>();
	Connections cons = new Connections();
    Connection conn;
    static Scanner in = new Scanner (System.in);

	public static void main(String[] args) {	
		try {
			System.out.println("How many clients would you like to create?");
			int number_of_threads = in.nextInt();
			Thread[] threadList = new Thread[number_of_threads];
			for(int i=0; i<number_of_threads; i++) {
					threadList[i]= new ConnectionHandler();
					threadList[i].start();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	}
	

	public synchronized Connection ConnectionUsed() {
		conn = cons.Connect();
		return conn;
	}
	
	public void Query() throws SQLException, InterruptedException {
		cons.Query();
	} 
	
	public synchronized Connection DropConnection() {
			cons.Disconnect(conn);
		return null;
	}
}
    
	