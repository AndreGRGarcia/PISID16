package nsei;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ButtonListener extends Thread {
	private final Server server;
	private ObjectInputStream in;
	
	public ButtonListener(Server server, Socket clientSocket) {
		this.server = server;
		try {
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(){
		try{
			while(true){
				processMessage((String)(in.readObject()));
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(IOException e){}
		}
	}
	
	private void processMessage(String message) {
		switch(message){
			case "RIP":
				server.resetMigrator();
		default:
			throw new IllegalStateException("Impossible message type.");
		}
	}
}
