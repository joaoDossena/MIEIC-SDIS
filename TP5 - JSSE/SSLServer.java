import java.io.InputStream;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;


import java.io.IOException;

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

		SSLServerSocketFactory factory = null;  
		 
		factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();  
 
        try (SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(port)) {
 
            System.out.println("SSLServer is listening on port " + port);
 
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
 
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