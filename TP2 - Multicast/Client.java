import java.net.*;
import java.io.*;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws IOException{
		if(args.length != 4) {
			System.out.println("Usage: java Client <multicast address> <multicast port> <oper> <opnd> *");
			return;
		}


    InetAddress group = InetAddress.getByName(args[0]);
		MulticastSocket multicastSocket = new MulticastSocket(Integer.parseInt(args[1]));
    multicastSocket.joinGroup(group);
    byte[] buf = new byte[256];
    String received = "";

		while (true) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        multicastSocket.receive(packet);
        received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received: " + received);
        if (received.length() > 0) {
            break;
        }
    }
    multicastSocket.leaveGroup(group);
    multicastSocket.close();


		// prepare request
		DatagramSocket socket = new DatagramSocket(); //opening socket
    String[] split = received.split(":");//split received string by :
		InetAddress address = InetAddress.getByName(split[0]);
		int port = Integer.parseInt(split[1]); 
		byte[] sbuf = args[2].getBytes();
		// TODO: get <opnd>*

		// send request
		DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, address, port);
		socket.send(packet);
    System.out.println("Sent request!");


		// get response
    byte[] rbuf = new byte[sbuf.length];
    packet = new DatagramPacket(rbuf, rbuf.length);
    socket.receive(packet);
    System.out.println("Received a response!");


    // display response
    received = new String(packet.getData());
    System.out.println("Received Message: " + received);
    socket.close();
	}
}