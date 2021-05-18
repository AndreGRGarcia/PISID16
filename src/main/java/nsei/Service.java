package nsei;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Service {
	
	private static PC2DAO pc2DAO = new PC2DAO();
	private static CloudMySQLDAO cloudDAO = new CloudMySQLDAO();
	
	public static final int PC2 = 0;
	public static final int CLOUD = 1;
	
	public static ArrayList<DBSensor> getTable(String table, int server) {
		String query = "SELECT * FROM " + table;
		ResultSet rs;
		ArrayList<DBSensor> list = new ArrayList<>();
		
		switch(server) {
		case PC2:
//			rs = pc2DAO.get(query);
//			try {
//				while(rs.next()) {
//					list.add(new DBSensor("'" + rs.getInt("idsensor") + "', '" +  rs.getString("tipo") + "', '" + rs.getString("limiteinferior") + "', '" + rs.getString("limitesuperior") + "', '" + rs.getString("idzona") + "'"));
//				}
//				return list;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			break;
		case CLOUD:
			rs = cloudDAO.get(query);
			try {
				while(rs.next()) {
					list.add(new DBSensor("'" + rs.getString("tipo") + "', '" + rs.getString("limiteinferior") + "', '" + rs.getString("limitesuperior") + "', '" + rs.getString("idzona") + "'"));
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
		return null;
	}
	
//	public static Structure getRegister(String name, String table) {
//		
//		return null;
//	}
//	
//	public static Structure getRegister(int id, String table) {
//		
//		return null;
//	}
//	
//	
	public static void put(DBObject dbo) {
//		switch(table) {
//			case "pc69.medicao":
//				campos = "Zona, Sensor, Hora, Leitura";
//			break;
//			case "pc69.sensor":
//				campos = "idsensor, tipo, limiteinferior, limitesuperior, idzona";
//			break;
//		}
		
		String update = "INSERT INTO " + dbo.getTable() +" (" + dbo.getFields() + ") VALUES (" + dbo.getValuesToInsert() +")"; 
		System.out.println(update);
		pc2DAO.put(update);
	
	}
	
	public static void wipeTable(String table) {
		pc2DAO.wipeTable(table);
	}
	
	//   TO_TIMESTAMP(:ts_val, 'YYYY-MM-DD HH24:MI:SS')
}
