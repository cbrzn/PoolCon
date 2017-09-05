package cabd;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHandler extends Thread {
	PoolConnection pc = new PoolConnection();
	Pool pool_config = Pool.getInstance();
	int initial_slots = pool_config.getInitial_connections();
	int growing_number = pool_config.getConnections_grow();
	Connection con;

	public synchronized void run(){
		try{
			getConnection();
			System.out.println(pc.aConnections.size());
		}catch(Exception e){
			e.printStackTrace();
	 	}
	}
	
	public synchronized Connection getConnection() throws InterruptedException {
				con = pc.ConnectionUsed();
				pc.aConnections.add(con);
				return con;
	}
			
	public synchronized Connection growConnection() throws InterruptedException {
			for (int i=0; i<growing_number; i++) {
				 con = pc.aConnections.get(i);
				 pc.aConnections.add(con);
				 Thread.sleep(500);
		}		 return null;
	}

	public synchronized void returnConnection(Connection conn) throws InterruptedException {
		    int con = 0;
			conn = pc.aConnections.get(con);
			pc.aConnections.remove(con);
			System.out.println("Connection " + (con + 1) + " finished");
			Thread.sleep(500);
    }
}