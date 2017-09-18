package cabd;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionHandler extends Thread {
	Connections cons = new Connections();
	Connection con = cons.Connect();
	PoolConnection pc = new PoolConnection();
	Pool pool_config = Pool.getInstance();
	int initial_slots = pool_config.getInitial_connections();
	int growing_number = pool_config.getConnections_grow();
	int max_slots = pool_config.getMax_connections();
	
	public synchronized void run() {
		try {		
			long start = System.currentTimeMillis();
			getConnection();
			cons.Query();
			cons.Disconnect();
			long end = System.currentTimeMillis();
			long total = end - start;
			System.out.println("Connection time "+total+" ms\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public synchronized Connection getConnection() throws InterruptedException {
		if(pc.aConnections.size() < max_slots) {
			if (!(pc.aConnections.size() == 0))	 {
				pc.aConnections.get(0);
				con = pc.aConnections.remove(0);
				System.out.println("Connections available: "+pc.aConnections.size());
				Thread.sleep(500);
			} else {
				this.addConnection();
				Thread.sleep(500);
			}
		}
		return con;
	}
	
	public synchronized void addConnection() throws InterruptedException {
		for (int i=0; i<growing_number; i++) {
			pc.aConnections.add(con);
		}
		System.out.println("Pool size increased by 5\n");
	}

			
	public synchronized void returnConnection(Connection con) throws InterruptedException, SQLException {
		cons.Disconnect();
		pc.aConnections.add(con);
		Thread.sleep(500);
	}
}
