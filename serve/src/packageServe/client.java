package packageServe;

	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.Socket;
	import java.net.UnknownHostException;

	public class client {

	    private Socket socket = null;
	    private DataInputStream input = null;
	    private DataOutputStream out = null;

	    public client(String address, int port) throws IOException {
	        try {
	            socket = new Socket(address, port);
	            System.out.println("Connected");

	            input = new DataInputStream(socket.getInputStream());
	            out = new DataOutputStream(socket.getOutputStream());

	        } catch (UnknownHostException e) {
	            System.out.println(e);
	        } catch (IOException u) {
	            System.out.println(u);
	        }

	        String line = "";

	        while (!line.equals("over")) {
	            try {
	                line = input.readUTF();  // Corrigido para ler strings corretamente
	                out.writeUTF(line);
	                System.out.println("Enviado");
	            } catch (IOException e) {
	                System.out.println(e);
	            }
	        }

	        try {
	            input.close();
	            out.close();
	            socket.close();
	            System.out.println("Encerrando......");
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	    }
	    public static void main(String[] args) throws IOException {
	        client client = new client("127.0.0.1", 4040);
	    }
	}
