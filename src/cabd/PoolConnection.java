package cabd;

import java.util.ArrayList;

public class PoolConnection {
	public ArrayList<Connections> aConnections = new ArrayList<Connections>();
	public static ConnectionHandler handler = new ConnectionHandler();


	public static void main(String[] args) {
		handler.start();
	}
}
    
	