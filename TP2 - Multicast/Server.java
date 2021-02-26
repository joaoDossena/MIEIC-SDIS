import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws IOException{
		if(args.length != 3) {
			System.out.println("Usage: java Server <port number> <multicast address> <multicast port>");
			return;
		}


		DatagramSocket socket = new DatagramSocket();
		InetAddress group = InetAddress.getByName(args[1]);
		byte[] buf = "8080".getBytes();
		int multicastPort = Integer.parseInt(args[2]);

		DatagramPacket packet = new DatagramPacket(buf, buf.length, group, multicastPort);
        socket.send(packet);
        socket.close();



		// // opening socket
		// int port = Integer.parseInt(args[0]);
		// DatagramSocket serverSocket = new DatagramSocket(port);

		// // receiving request
  //       byte[] rbuf = new byte[256];
  //       DatagramPacket packet = new DatagramPacket(rbuf, rbuf.length);        
  //       serverSocket.receive(packet);
  //       String request = new String(packet.getData());
  //       System.out.println("Request: " + request);

  //       // prepare response
        

  //       // send response
  //       InetAddress destAddress = packet.getAddress();
  //       int destPort = packet.getPort();
		// packet = new DatagramPacket(rbuf, rbuf.length, destAddress, destPort);
		// serverSocket.send(packet);
	}
}