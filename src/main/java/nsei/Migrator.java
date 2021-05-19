package nsei;

import java.util.ArrayList;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;


public class Migrator {
	
	private DredFather father;
	private Server server;
	
	public Migrator() throws InterruptedException {
		this.server = new Server(this);
		server.start();
//		updateSensorLimits();
//		migrate();
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
    	
		father = new DredFather(database);
		father.start();
		try {
			father.join();
		} catch (InterruptedException e) {
			mongoClient.close();
		}
	}

	public void closeApp() {
		server.interrupt();
	}

	public void reset() {
		notify();
	}
}
