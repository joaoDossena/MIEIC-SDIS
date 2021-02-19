import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException{
		if(args.length != 4) {	//Review 
			System.out.println("Usage: java Client <host> <port> <oper> <opnd>*");
			return;
		}

		// prepare request
		DatagramSocket socket = new DatagramSocket(); //opening socket
		InetAddress address = InetAddress.getByName(args[0]);
		int port = Integer.parseInt(args[1]); 
		byte[] sbuf = args[2].getBytes();
		// TODO: get <opnd>*

		// send request
		DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, address, port);
		socket.send(packet);

		// get response
        byte[] rbuf = new byte[sbuf.length];
        packet = new DatagramPacket(rbuf, rbuf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData());
        System.out.println("Received Message: " + received);
        socket.close();
	}
}