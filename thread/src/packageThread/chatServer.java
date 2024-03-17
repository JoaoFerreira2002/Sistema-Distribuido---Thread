package packageThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;

//import packageServer.client;

public class chatServer {
		public static  final int PORT = 4000;
		private ServerSocket serversocket  = null;
		private final String SERVE_ADDRESS = "127.0.0.1";
		private BufferedReader in;
		

		
		
		public void start() throws IOException {
			serversocket = new ServerSocket(PORT);
			System.out.println("Serve Started in poty "+PORT);
			clientConnectionLoop();
		}
		
		private void clientConnectionLoop() throws IOException {
			ClienteSocket clientesocket = new ClienteSocket(serversocket.accept());
			
			new Thread(()->{
				try {
					clienteMessegeLoop(clientesocket);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}).start();
			
		}
		
		public void clienteMessegeLoop(ClienteSocket clientesocket) throws IOException {
			String msg;
			try {
				while((msg = clientesocket.getMessage())!=null) {
					if("Sair".equalsIgnoreCase(msg)) {
						return;
					}
					System.out.printf("Msg received of client %ss: %s\n", clientesocket.getRemoteSocketAddress(), msg);
					
				}
			} finally  {
				clientesocket.close();
			}
		}
		public static void main(String[] args) throws IOException {
			chatServer server = new chatServer();
			server.start();
	    }
}
