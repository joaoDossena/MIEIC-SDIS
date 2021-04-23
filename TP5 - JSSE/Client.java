import java.io.*;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


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

        SSLSocketFactory factory = null;  
         
        factory = (SSLSocketFactory) SSLSocketFactory.getDefault();  
 
        try (SSLSocket socket = (SSLSocket) factory.createSocket(hostname, port)) {
 
            OutputStream output = socket.getOutputStream();
            String request = operation + " " + dnsName;
            if(operation.equals("REGISTER")){
                request += " " + args[4];
                if(args.length == 6){
                    request += " " + args[5];
                }
            }
            else if(args.length == 5){
                request += " " + args[4];
            }

            output.write(request.getBytes());
  
        } catch (UnknownHostException ex) {
 
            System.out.println("Client: SSLServer not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
	}


}