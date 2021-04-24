import java.io.InputStream;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import java.io.IOException;

public class SSLServer {
	private static DNSServer dns = new DNSServer();

	public static void main(String[] args){
		if(args.length < 1) {
			System.out.println("Usage: java SSLServer <port> <cypher-suite>*");
			return;
		}

		//set the type of trust store
        System.setProperty("javax.net.ssl.trustStoreType","JKS");
        //set the password with which the truststore is encripted
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        //set the name of the trust store containing the client public key and certificate
        System.setProperty("javax.net.ssl.trustStore", "./truststore");
        //set the password with which the server keystore is encripted
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        //set the name of the keystore containing the server's private and public keys
        System.setProperty("javax.net.ssl.keyStore","./server.keys");

		int port = Integer.parseInt(args[0]);

		SSLServerSocketFactory factory = null;  
		 
		factory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();  
 
        try (SSLServerSocket serverSocket = (SSLServerSocket) factory.createServerSocket(port)) {
			if(args.length > 1){
	        	String[] cypherSuites = Arrays.copyOfRange(args, 1, args.length);
				try { 
	                serverSocket.setEnabledCipherSuites(cypherSuites);
                    System.out.println("Suite: " + cypherSuites[0]);
	            } catch (Exception e) {
					System.out.println("SSLServer: Unknown cypher suite. Exiting...");
	                e.printStackTrace();
	                return;
	            }
			}
 
            System.out.println("SSLServer is listening on port " + port);
 
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                System.out.println("SSLServer: New client connected");
 
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