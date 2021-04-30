package nsei;

public abstract class DBObject {
	
	protected String table;
	
	public DBObject() {
		
	}	
	
	public abstract String getValuesToInsert();
	public abstract String getFields();
	
	public String getTable() {
		return table;
	}
}
