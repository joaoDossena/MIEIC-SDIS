import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args){
        if(args.length < 4 || args.length > 5) {
            System.out.println("Usage: java Client <host_name> <port_number> <oper> <opnd1> [<opnd2>]");
            return;
        }

        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        String operation = args[2].toUpperCase();
        String dnsName = args[3];

        if(operation.equals("REGISTER") && args.length != 5){
            System.out.println("Client: REGISTER arguments wrong");
            return;
        }
        else if(operation.equals("LOOKUP") && args.length != 4){
            System.out.println("Client: LOOKUP arguments wrong");
            return;
        }
        else if(!operation.equals("REGISTER") && !operation.equals("LOOKUP")){
            System.out.println("Client: Unknown operation: " + operation);
            return;
        }
 
        try (Socket socket = new Socket(hostname, port)) {
 
            OutputStream output = socket.getOutputStream();
            String request = operation + " " + dnsName;
            if(operation.equals("REGISTER")){
                request += " " + args[4];
            }

            output.write(request.getBytes());
  
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}