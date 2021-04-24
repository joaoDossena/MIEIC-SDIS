import java.io.OutputStream;
import java.util.Arrays;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import java.io.IOException;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args){
		if(args.length < 4 || args.length > 6) {
			System.out.println("Usage: java Client <host_name> <port_number> <oper> <opnd1> [<opnd2>] [<cypher_suite>]");
			return;
		}

		String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String operation = args[2].toUpperCase();
        String dnsName = args[3];

        if(operation.equals("REGISTER") && args.length < 5){
            System.out.println("Client: REGISTER arguments wrong");
            return;
        }
        else if(operation.equals("LOOKUP") && args.length > 5){
            System.out.println("Client: LOOKUP arguments wrong");
            return;
        }
        else if(!operation.equals("REGISTER") && !operation.equals("LOOKUP")){
            System.out.println("Client: Unknown operation: " + operation);
            return;
        }

        //set the type of trust store
        System.setProperty("javax.net.ssl.trustStoreType","JKS");
        //set the password with which the truststore is encripted
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        //set the name of the trust store containing the server's public key and certificate           
        System.setProperty("javax.net.ssl.trustStore", "./truststore");
        //set the password with which the client keystore is encripted
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        //set the name of the keystore containing the client's private and public keys
        System.setProperty("javax.net.ssl.keyStore","./client.keys");

        SSLSocketFactory factory = null;  
         
        factory = (SSLSocketFactory) SSLSocketFactory.getDefault();  
 
        try (SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            String request = operation + " " + dnsName;
            if(operation.equals("REGISTER")){
                request += " " + args[4];
                if(args.length > 5){
                    String[] cypherSuites = Arrays.copyOfRange(args, 5, args.length);
                    try {
                        socket.setEnabledCipherSuites(cypherSuites);
                        System.out.println("Suite: " + cypherSuites[0]);
                    } catch (Exception e) {
                        System.out.println("Client: Unknown cypher suite. Exiting...");
                        e.printStackTrace();
                        return;
                    }
                }
            }
            else if(args.length > 4){
                String[] cypherSuites = Arrays.copyOfRange(args, 4, args.length);
                try {
                    socket.setEnabledCipherSuites(cypherSuites);
                } catch (Exception e) {
                    System.out.println("Client: Unknown cypher suite. Exiting...");
                    e.printStackTrace();
                    return;
                }
            }
            socket.startHandshake();
            output.write(request.getBytes());
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Client: SSLServer not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Client: " + ex.getMessage());
            ex.printStackTrace();
        }
	}


}