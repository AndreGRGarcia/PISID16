package nsei;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Sorts.descending;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Dred extends Thread {
	
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	
	public Dred(MongoDatabase database, MongoCollection<Document> collection) {
		this.database = database;
		this.collection = collection;
	}
	
	@Override
	public void run() {
		migrate(database, collection);
	}
	
	public void migrate(MongoDatabase database, MongoCollection<Document> collection) {
		
    	String lastDate;
//    	DBCursor<Document> c = new DBCursor<Document>();
    	Bson sort = descending("Data");
    	MongoIterable<Document> firstD = collection.find().sort(sort).limit(1);
    	Document d1 = firstD.first();
    	Service.put(new DBMedicao(d1));
		lastDate = (String)d1.get("Data");
		
		for(;;) {
    		Bson findFilter = gt("Data", lastDate);
    		MongoIterable<Document> result = collection.find(findFilter);
    		String lastDateTemp = "";
    		if(result.cursor().hasNext()) {
    			for(Document d: result) {
    				System.out.println(d);
    				Service.put(new DBMedicao(d));
    				lastDateTemp = (String)d.get("Data");
    			}
    			if(!lastDateTemp.isEmpty()) lastDate = lastDateTemp;
    		} else {
    			try {
    				System.out.println("Gonna sleep for a second");
					sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
    		}
    	}
    
    }
	
}
