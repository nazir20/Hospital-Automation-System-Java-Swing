package Model;

/**
 * 
 * @imports
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class BasHekim extends User{
	/**
	 * 
	 * 
	 * 
	 */
	Connection con = conn.connectDB();
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	/**
	 * 
	 * @param id
	 * @param tcno
	 * @param name
	 * @param password
	 * @param type
	 * 
	 */
	public BasHekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
	}
	public BasHekim(){}
	
	/**
	 * 
	 * 
	 * @methods
	 * 
	 * 
	 */
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<User> getDoctorList() throws SQLException{
		
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			
			stmt = con.createStatement();
			String query = "SELECT * FROM users WHERE type = 'doktor'";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				int id = rs.getInt("id");
				String tcno = rs.getString("tcno");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String type = rs.getString("type");
				
				obj = new User(id, tcno, name, password, type);
				list.add(obj);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	
public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException{
		
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			
			stmt = con.createStatement();
			String query = "SELECT u.id, u.tcno, u.type,u.password, u.name FROM employee e LEFT JOIN users u ON e.user_id = u.id WHERE clinic_id =" + clinic_id;
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				int id = rs.getInt("u.id");
				String tcno = rs.getString("u.tcno");
				String name = rs.getString("u.name");
				String password = rs.getString("u.password");
				String type = rs.getString("u.type");
				
				obj = new User(id, tcno, name, password, type);
				list.add(obj);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stmt.close();
			rs.close();
		}
		return list;
	}
	/**
	 * 
	 * @param tcno
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean addDoctor(String tcno, String password, String name) throws SQLException {
		
		boolean key = false;
		try {
			
			String query = "INSERT INTO users(tcno, password, name, type) VALUES(?,?,?,?)";
			stmt = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteDoctor(int id) throws SQLException {
		
		boolean key = false;
		try {
			
			String query = "DELETE FROM users WHERE id = ?";
			stmt = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param id
	 * @param tcno
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean updateDoctor(int id, String tcno, String password, String name) throws SQLException{
		boolean key = false;
		try {
			
			String query = "UPDATE users SET name = ?, tcno = ?, password = ?  WHERE id = ?";
			stmt = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4,id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @param tcno
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean addEmployee(int user_id, int clinic_id) throws SQLException {
		
		boolean key = false;
		int count = 0;
		try {
		
			stmt = con.createStatement();
			String sql = "SELECT * FROM employee WHERE clinic_id =" + clinic_id + " AND user_id=" + user_id ;
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				count++;
			}
			if(count == 0) {
				String query = "INSERT INTO employee(clinic_id, user_id) VALUES(?,?)";
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, clinic_id);
				preparedStatement.setInt(2, user_id);
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(key)
			return true;
		else
			return false;
	}
	
}
