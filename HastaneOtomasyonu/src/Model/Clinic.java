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
import Helper.DBconnection;

public class Clinic {
	
	private int id;
	private String name;
	DBconnection conn = new DBconnection();
	Connection con = conn.connectDB();
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	/**
	 * -- Constructors --
	 * @param id
	 * @param name
	 * 
	 */
	public Clinic() {
		
	}
	public Clinic(int id, String name) {
		setId(id);
		setName(name);
	}
	
	/**
	 * @methods
	 * @return
	 * @throws SQLException
	 * 
	 */
	public ArrayList<Clinic> getClinicList() throws SQLException{
		
		ArrayList<Clinic> list = new ArrayList<>();
		Clinic obj;
		try {
			// creating a new statement
			stmt = con.createStatement();
			// SQL command for getting every thing from the `clinic` table 
			String query = "SELECT * FROM clinic";
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				obj = new Clinic();
				obj.setId(rs.getInt("id"));
				obj.setName(rs.getString("name"));
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
	 * @param id
	 * @return
	 * @throws SQLException 
	 * 
	 */
	public Clinic getFetch(int id) throws SQLException {
		Clinic c = new Clinic();
		try {
			
			stmt = con.createStatement();
			// SQL command for selecting `clinic` table according to `id` provided for getFetch() method...
			String query = "SELECT * FROM clinic WHERE id = " + id;
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				break;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			stmt.close();
			rs.close();
		}
		return c;
	}
	/**
	 * 
	 * @add clinics
	 * @param name
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean addClinic(String name) throws SQLException {
		
		boolean key = false;
		try {
			// SQL command for inserting a new clinic to the `clinic` table
			String query = "INSERT INTO clinic(name) VALUES(?)";
			stmt = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	 * @delete clinics
	 * @param id
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean deleteclinic(int id) throws SQLException {
		
		boolean key = false;
		try {
			// SQL command for deleting a clinic from `clinic` table according to the provided `id` for deleteClinic() table
			String query = "DELETE FROM clinic WHERE id = ?";
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
	 * @update clinic
	 * @param id
	 * @param name
	 * @return
	 * @throws SQLException
	 * 
	 */
	public boolean updateClinic(int id, String name) throws SQLException{
		boolean key = false;
		try {
			// SQL command to update the name of clinic at `clinic` table according to the provided `id` & `new name` on updateClinic() method..
			String query = "UPDATE clinic SET name = ? WHERE id = ?";
			stmt = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2,id);
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
	 * @getter
	 * @setter
	 * @return
	 * 
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
