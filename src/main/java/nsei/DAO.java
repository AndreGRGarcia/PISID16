package nsei;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract public class DAO {
	
	String url;
	String uname;
	String password;
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public DAO(String url) {
		this.url = url;
		this.uname = "";
		this.password = "";
	}
	
	public DAO(String url, String uname, String password) {
		this.url = url;
		this.uname = uname;
		this.password = password;
	}
	
	public DAO() {}
	
	public abstract ResultSet get(String query) throws SQLException;
	public abstract void put(String query) throws SQLException;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(url, uname, password);
		st = con.createStatement();
	}
	
	public void disconnect() throws SQLException {
		con.close();
		st.close();
	}
}
