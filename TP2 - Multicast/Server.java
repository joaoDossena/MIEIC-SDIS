import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		if(args.length != 3) {
			System.out.println("Usage: java Server <port number> <multicast address> <multicast port>");
			return;
		}

		Advertiser adv = new Advertiser("Advertiser", args[1], args[2], "teste");
		Listener lis = new Listener("Listener", args[0]);
		adv.run();
		lis.run();


		

        // prepare response
        

        // send response
        
	}
}