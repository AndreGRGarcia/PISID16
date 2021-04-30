package nsei;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CloudMySQLDAO extends DAO {
	
	public CloudMySQLDAO() {
		super("jdbc:mysql://194.210.86.10:3306/sid2021", "aluno", "aluno");
	}
	
	@Override
	public ResultSet get(String query) {
		
		try {
			connect();
			rs = st.executeQuery(query);			
			return rs;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void put(String query) throws SQLException {}
	
}
