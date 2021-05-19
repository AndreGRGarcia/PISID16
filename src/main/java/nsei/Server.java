package nsei;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	private final int PORTO = 8585;
	private ServerSocket server;
	private Socket socket;

	private Migrator migrator;

	public Server(Migrator migrator) {
		this.migrator = migrator;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(PORTO);
			while (true) {
				socket = server.accept();
				System.out.println("RESET CONNECTIONS BUTTON PRESSED");
//				migrator.reset();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				socket.close();
				server.close();
				System.out.println("SERVIDOR FECHADO");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
