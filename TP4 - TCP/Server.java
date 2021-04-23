import java.io.*;
import java.net.*;
import java.util.Date;

public class Server {
	private DNSServer dns = new DNSServer();

	public static void main(String[] args){
		if(args.length != 1) {
			System.out.println("Usage: java Server <srvc_port>");
			return;
		}

		int port = Integer.parseInt(args[0]);
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
 
                System.out.println("New client connected");
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
	            handleRequest(reader.readLine());

 
                writer.println(new Date().toString());
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
	}

	public String handleRequest(String request){
		System.out.print(request + " :: ");
		String response = "ERROR";
		String args = request.split(" ");
		switch(args[0].toUppercase()){
			case "REGISTER":
			this.dns.register(args[1], args[2]);
			response = 
			break;
			case "LOOKUP":
			this.dns.lookup(args[1]);
			break;
			default:
			break;
		}
}
}