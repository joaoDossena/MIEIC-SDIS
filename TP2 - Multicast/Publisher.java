import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		if(args.length != 3) {
			System.out.println("Usage: java Server <port number> <multicast address> <multicast port>");
			return;
		}

		// opening socket
		int port = Integer.parseInt(args[0]);
		DatagramSocket serverSocket = new DatagramSocket(port);

		// receiving request
        byte[] rbuf = new byte[256];
        DatagramPacket packet = new DatagramPacket(rbuf, rbuf.length);        
        serverSocket.receive(packet);
        String request = new String(packet.getData());
        System.out.println("Request: " + request);

        // prepare response
        

        // send response
        InetAddress destAddress = packet.getAddress();
        int destPort = packet.getPort();
		packet = new DatagramPacket(rbuf, rbuf.length, destAddress, destPort);
		serverSocket.send(packet);
	}
}