package cabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections extends Thread {
	Connection con = null;
	Pool pool = new Pool();
	
	public void run() {
		Connect();
	}
	
	public synchronized Connection Connect() {
		Pool pool = Pool.getInstance();
		try {
			Class.forName(pool.getDriver());
			con = (Connection) DriverManager.getConnection(pool.getUrl(), 
					pool.getUsername(), pool.getPassword());
			System.out.println("A new connection has been created");
			Thread.sleep(500);
			}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
		
	 public synchronized Connection Disconnect(Connection con) {
		try {
			con.close();
			System.out.println("Connection closed");
			} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public synchronized  void Query() throws SQLException, InterruptedException {
		Statement stmt = con.createStatement();
	    stmt.executeQuery("SELECT * FROM test" );
	    ResultSet rs = stmt.getResultSet();
	    System.out.println("===FIELDS===");
	    while (rs.next()) {
	        System.out.println(rs.getString("field"));
			Thread.sleep(500);
	    }
	}
}