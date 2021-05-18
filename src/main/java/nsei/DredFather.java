package nsei;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class DredFather extends Thread{
	
	private MongoIterable<String> iterator;
	private MongoDatabase database;
	private ArrayList<Dred> dreds = new ArrayList<>();
	private boolean running = true;
	
	public DredFather(MongoDatabase database) {
		this.database = database;
		iterator = database.listCollectionNames();
	}	
	
	public void stopRunning() {
		running = false;
		dreds.forEach (Dred::stopRunning);
		dreds.clear();

	}
	
	public void makeChildrens() {
		for(String c: iterator) {
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
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public void run() {
		if(running)
			makeChildrens();
	}
	
}
