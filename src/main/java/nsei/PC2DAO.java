package nsei;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PC2DAO extends DAO {

	public PC2DAO() {
		super("jdbc:mysql://localhost:3306/pc2", "root", "");
	}
	
	@Override
	public ResultSet get(String query) {
		
		try {
			connect();
			rs = st.executeQuery(query);			
			disconnect();
			return rs;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(String update){
		try {
			connect();
			if(st.executeUpdate(update) == 1) {
				System.out.println("Successfully inserted on MySQL");
			} else {
				System.out.println("Failure inserting on MySQL");
			}
			disconnect();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void wipeTable(String table) {
		try {
			connect();
			st.executeUpdate("delete from " + table);
			disconnect();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
