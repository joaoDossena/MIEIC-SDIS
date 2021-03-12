import java.net.*;
import java.io.*;

public class Client {

	public static void main(String[] args){
		if(args.length != 4 && args.length != 5) {
			System.out.println("Usage: java Client <host_name> <port_number> <oper> <opnd1> [<opnd2>]");
			return;
		}

		String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try (Socket socket = new Socket(hostname, port)) {
 
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            String time = reader.readLine();
 
            System.out.println(time);
 
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
	}
}