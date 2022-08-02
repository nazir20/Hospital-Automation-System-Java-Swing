package Helper;
/**
 * 
 * @imports
 * 
 */
import java.sql.*;

public class DBconnection {
	
	Connection con = null;
	
	/**
	 * 
	 * @constructor
	 * 
	 */
	public DBconnection() {
		
	}
	
	/**
	 * 
	 * @methods
	 *
	 */
	public Connection connectDB() {
		//jbdc -> java Database connection
		String jdbcURL = "jdbc:mysql://localhost:8889/hospitalDB?useSSL=false";
		String dbUsername = "root";
		String dbPassword = "root";
		
		try {
			this.con = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
			return this.con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.con;
	}
}
