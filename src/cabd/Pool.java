package cabd;


public class Pool {
	
	private String username = "postgres";
	private String password = "cesar5683072";
	private String url = "jdbc:postgresql://localhost:5432/kbase";
	private String driver = "org.postgresql.Driver";
	private int max_connections = 50;	
	private int initial_connections = 20;
	private int connections_grow = 5;
		
	private static Pool instance = new Pool();
	
	public static Pool getInstance(){
	return instance;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public int getMax_connections() {
		return max_connections;
	}

	public void setMax_connections(int max_connections) {
		this.max_connections = max_connections;
	}

	public int getInitial_connections() {
		return initial_connections;
	}

	public void setInitial_connections(int initial_connections) {
		this.initial_connections = initial_connections;
	}

	public int getConnections_grow() {
		return connections_grow;
	}

	public void setConnections_grow(int connections_grow) {
		this.connections_grow = connections_grow;
	}
}