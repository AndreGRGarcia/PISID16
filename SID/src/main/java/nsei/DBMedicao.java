package nsei;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

public class DBMedicao extends DBObject {
	
	private Map<String, String> values;
	
	public DBMedicao(Document d) {
		table = "pc2.medicao";
		values = new HashMap<String, String>();
		String data = (String)d.get("Data");
		data = data.replace('T', ' ');
		data = data.replace("Z", "");
		values.put("_id", String.valueOf(d.get("_id")));
		values.put("Zona", ((String)d.get("Zona")).substring(1));
		values.put("Sensor", ((String)d.get("Sensor")).substring(0, 1));
		values.put("Data", data);
		values.put("Medicao", (String)d.get("Medicao"));
	}
	
	@Override
	public String getValuesToInsert() {
		return "'" + values.get("Zona") + "', '" + values.get("Sensor") + "', '" + values.get("Data") + "', '" + values.get("Medicao") + "'";
	}

	@Override
	public String getFields() {
		return "Zona, Sensor, Hora, Leitura";
	}


}
