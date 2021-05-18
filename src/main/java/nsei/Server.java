package nsei;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int PORTO = 8080;
	private final ServerSocket serverSocket;
	private boolean running = true;
	private Migrator migrator;
	
	public Server(Migrator migrator) throws IOException {
		serverSocket = new ServerSocket(PORTO);
		this.migrator = migrator;
		try {
			startServing();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				serverSocket.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		System.out.println("Server closing...");
	}
	
	public void resetMigrator() {
		migrator.resetMigrate();
	}
	
	private void startServing() throws IOException{
		while(running){
			Socket clientSocket = serverSocket.accept();		
			ButtonListener h = new ButtonListener(this, clientSocket);
			h.start();
		}
	}	
	
	 public static void main(String[] args) {
			try {
				new Server(new Migrator());
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
	
}
