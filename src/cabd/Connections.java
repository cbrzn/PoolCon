package cabd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connections {
	Connection con;
	Pool pool = new Pool();

	public synchronized Connection Connect() {
		try {
			Pool pool = Pool.getInstance();
			Class.forName(pool.getDriver());
			con = DriverManager.getConnection(pool.getUrl(), 
					pool.getUsername(), pool.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return con;
	}
	
	public synchronized void Disconnect() throws SQLException {
		try {	
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void Query() throws SQLException, InterruptedException {
		try {
			Statement stmt = con.createStatement();
		    stmt.executeQuery("SELECT * FROM test");
		    ResultSet rs = stmt.getResultSet();
		    System.out.println("QUERY:");
		    while (rs.next()) {
		        System.out.println(rs.getString("field"));
		    }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}