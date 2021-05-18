package nsei;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ResetSQLConnectionButton {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ResetSQLConnectionButton() {		
		try {
			connectToServer();
			sendMessage("RIP");
			closeStream();
		} catch (IOException e) {
			if(e.getClass().equals(ConnectException.class)){
				JOptionPane.showMessageDialog(null, new JLabel("Couldn't connect to the server."));
				closeStream();
			}
		}
	}
	
	private void connectToServer() throws UnknownHostException, IOException{
		socket = new Socket(InetAddress.getByName(null), Migrator.PORTO);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}
	
	private void sendMessage(Object o){
		try {
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void closeStream(){
		System.out.println("Closing Streams...");
		try{
			if(out != null){
				out.close();
			}
			if(in != null){
				in.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void main(String[] args) throws IOException, InvocationTargetException {
		new ResetSQLConnectionButton();
	}	
	
}
