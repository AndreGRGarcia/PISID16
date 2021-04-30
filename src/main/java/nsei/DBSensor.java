package nsei;

public class DBSensor extends DBObject {
	
	private String values;
	
	public DBSensor(String line) {
		table = "pc2.sensor";
		values = line;
	}
	
	@Override
	public String getValuesToInsert() {
		return values;
	}

	@Override
	public String getFields() {
		return "idsensor, tipo, limiteinferior, limitesuperior, idzona";
	}



}
