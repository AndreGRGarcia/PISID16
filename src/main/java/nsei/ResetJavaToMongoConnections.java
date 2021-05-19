package nsei;

import java.io.IOException;

import java.net.InetAddress;
import java.net.Socket;

public class ResetJavaToMongoConnections {

	private static final int PORTO = 8585;
	private static Socket socket;

	public static void main(String[] args) throws IOException {

		try {
			socket = new Socket(InetAddress.getLocalHost(), PORTO);
			System.out.println("Passei aqui");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			socket.close();
		}
	}
	
}
