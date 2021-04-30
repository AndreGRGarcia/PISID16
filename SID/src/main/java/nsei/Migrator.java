package nsei;

import static com.mongodb.client.model.Sorts.descending;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;


public class Migrator {
	
	
	public Migrator() {
		updateSensorLimits();
		migrate();
	}
	
	private void updateSensorLimits() {
		ArrayList<DBSensor> list = Service.getTable("sensor", Service.CLOUD);
		if(!list.isEmpty()) {
			Service.wipeTable("sensor");
			list.forEach(l -> Service.put(l));
		}
	}

	public void migrate() {
		MongoClientURI uri = new MongoClientURI("mongodb://localhost:27017");
    	MongoClient mongoClient = new MongoClient(uri);
    	MongoDatabase database = mongoClient.getDatabase("sid2021");
    	MongoIterable<String> diogoCandelabros = database.listCollectionNames();
//    	ArrayList<MongoCollection<Document>> collections = new ArrayList<>();
//    	collections.add(database.getCollection("sensort1"));
//    	collections.add(database.getCollection("sensort2"));
//    	collections.add(database.getCollection("sensorl1"));
//    	collections.add(database.getCollection("sensorl2"));
//    	collections.add(database.getCollection("sensorh1"));
//    	collections.add(database.getCollection("sensorh2"));
    	
		ArrayList<Dred> dreds = new ArrayList<>();
		for(String c: diogoCandelabros) {
			MongoCollection<Document> col = database.getCollection(c);
			Dred dred = new Dred(database, col);
			dred.start();
			dreds.add(dred);
			System.out.println("Started Dred");
		}
		dreds.forEach(d -> {
			try {
				d.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
    	
    	mongoClient.close();
	}
	
	
	
    
	    public static void main(String[] args) {
	    	Migrator mig = new Migrator();
	    	
//	    	ArrayList<String> list = Service.getTable("utilizador");
//	    	list.forEach(l -> System.out.println(l));
//	    	
//	    	Service.put("'Hello', 'Bicho', 't'");
	    	
	    	
	    	
//	    	MongoClient mongoClient = new MongoClient("localhost", 27017);
//	    	MongoDatabase database = mongoClient.getDatabase("livro");
//	    	MongoClient mongoClient = new MongoClient("localhost", 27017);
//	    	MongoDatabase database = mongoClient.getDatabase("livro");
//	    	boolean auth = database.authenticate("username", "pwd".toCharArray());
	    	
//	    	String user = "aluno"; // the user name
//	    	String db = "sid2021"; // the name of the database in which the user is defined
//	    	char[] password = "aluno".toCharArray(); // the password as a character array
//	    	MongoCredential credential = MongoCredential.createCredential(user, db, password);
	    	
//	    	String user = "aluno"; // the user name
//	    	String db = "sid2021"; // the name of the database in which the user is defined
//	    	char[] password = "aluno".toCharArray(); // the password as a character array
//	    	// ...
//	    	MongoCredential credential = MongoCredential.createCredential(user, db, password);
//	    	MongoClient mongoClient = new MongoClient(new ServerAddress("194.210.86.10", 27017), Arrays.asList(credential));
//	    	MongoDatabase database = mongoClient.getDatabase("sid2021");
	    }
	    
	    
}
