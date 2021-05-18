package nsei;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ButtonListener extends Thread {
	private static final int QUEUES_MAX_CAPACITY = 10;
	private final Migrator migrator;
	private final HandlerOut handlerOut;
	private final BlockingQueue<String> messageQueue;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ButtonListener(Migrator server, Socket clientSocket) {
		this.migrator = server;
		handlerOut = new HandlerOut();
		messageQueue = new ArrayBlockingQueue<>(QUEUES_MAX_CAPACITY);
		try {
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(){
		handlerOut.start();
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
				migrator.resetMigrate();
		default:
			throw new IllegalStateException("Impossible message type.");
		}
	}
	
	private class HandlerOut extends Thread{
		@Override
		public void run(){
			try{
				while(true){
					out.writeObject(messageQueue.take());
				}
			}catch(InterruptedException | IOException e){
				e.printStackTrace();
			}finally{
				try{
					if(out != null){
						out.close();
					}
				}catch(IOException e){}
			}
		}
	}
}
