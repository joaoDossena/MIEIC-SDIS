import java.io.*;
import java.net.*;

import javax.net.ssl.SSLSocket;


public class SSLServer {
	private static DNSServer dns = new DNSServer();

	public static void main(String[] args){
		if(args.length < 1 || args.length > 2) {
			System.out.println("Usage: java SSLServer <port> <cypher-suite>*");
			return;
		}

		int port = Integer.parseInt(args[0]);
		String cypherSuite = "";
		if(args.length == 2)
			cypherSuite = args[1];
		else
			cypherSuite = "default";

 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("SSLServer is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
                 InputStream input = socket.getInputStream();
                byte[] buf = new byte[256];

                input.read(buf);

                String received = new String(buf);
	            handleRequest(received);

 
            }
 
        } catch (IOException ex) {
            System.out.println("SSLServer exception: " + ex.getMessage());
            ex.printStackTrace();
        }
	}

	public static void handleRequest(String request){
		System.out.println("SSLServer: " + request);
		String response = "ERROR";
		String[] args = request.split(" ");
		switch(args[0].toUpperCase()){
			case "REGISTER":
			dns.register(args[1], args[2]);
			break;
			case "LOOKUP":
			dns.lookup(args[1]);
			break;
			default:
			break;
		}
	}
	
}