package cabd;


public class ConnectionHandler extends Thread {
	Connections cons;
	PoolConnection pc = new PoolConnection();
	Pool pool_config = Pool.getInstance();
	int initial_slots = pool_config.getInitial_connections();
	int growing_number = pool_config.getConnections_grow();
	int max_slots = pool_config.getMax_connections();

	public synchronized void run(){
		try{
			while(pc.aConnections.size() < max_slots) {
				if (pc.aConnections.size() < initial_slots) {
					for (int i=0; i<initial_slots; i++) {
						getConnection(cons);
						Thread.sleep(500);
					}	
					System.out.println("POOL INITIALIZED WITH 20 CONNECTIONS\n");
				} else {
					for (int i=0; i<growing_number; i++) {
						getConnection(cons);
						Thread.sleep(500);
					}
					System.out.println("POOL SIZE: "+pc.aConnections.size()+"\n");
				}
			if (pc.aConnections.size() == max_slots) {
				for (int i=0; i<5; i++)
					returnConnection();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
	 	}
	}
	
	public synchronized void getConnection(Connections cons) throws InterruptedException {
		cons = new Connections();
		cons.start();
		Thread.sleep(500);
		pc.aConnections.add(cons);
	}

			
	public synchronized void returnConnection() throws InterruptedException {
			pc.aConnections.remove(0);
			Thread.sleep(500);
			System.out.println("Connection #"+(pc.aConnections.size()+1)+" finished");
    }
}