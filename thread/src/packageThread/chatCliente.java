package packageThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class chatCliente {
	public static  final int PORT = 4000;
	private static final String SERVER_ADDRESS = null;
	private ClienteSocket clientesocket  = null;
	private final String SERVE_ADDRESS = "127.0.0.1";
	
	
	private Scanner scanner;
	
	public  chatCliente(){
		scanner = new Scanner (System.in);
	}
	
	
	public void start() throws UnknownHostException, IOException { 
		clientesocket = new ClienteSocket(new Socket(SERVER_ADDRESS,chatServer.PORT));
		
		System.out.println("Client Connected to server in"+ SERVE_ADDRESS +":" +chatServer.PORT);
		messageLoop();
		
	}
	
	
	private void messageLoop() {
		String msg = null;
		do {
			System.out.println("Write your messsage ou exit to leave");
			msg = scanner.nextLine();
			clientesocket.send(msg);
			
			}while(!msg.equalsIgnoreCase("exit"));
	}
	
	
	public static void main(String[] args) {
		chatCliente cliente = new chatCliente();
		try {
			cliente.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client exit!");

	}

}
