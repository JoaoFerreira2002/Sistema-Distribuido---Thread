package packageServe;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.net.*;

public class Serve {
    
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    
    public Serve(int port){
        try {
            server = new ServerSocket(port);
            System.out.println("Server Started");
            System.out.println("Waiting for a client...");
            
            socket = server.accept();
            System.out.println("Cliente Accepted");
            
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            
            while (line.equals("Over")) {                
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("Closing Connection");
            socket.close();
            in.close();
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return;
    }
  
    public static void main(String[] args) {
        Serve Serveserve = new Serve(4040);
    }
    
}
