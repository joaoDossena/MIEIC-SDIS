import java.net.*;
import java.io.*;

public class Client {
	public static void main(String[] args) throws IOException{
		if(args.length != 4) {
			System.out.println("Usage: java Client <multicast address> <multicast port> <oper> <opnd> *");
			return;
		}


    InetAddress group = InetAddress.getByName(args[0]);
		MulticastSocket socket = new MulticastSocket(Integer.parseInt(args[1]));
    socket.joinGroup(group);
    byte[] buf = new byte[256];

		while (true) {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Received: " + received);
        if ("end".equals(received)) {
            break;
        }
    }
    socket.leaveGroup(group);
    socket.close();
		// // prepare request
		// DatagramSocket socket = new DatagramSocket(); //opening socket
		// InetAddress address = InetAddress.getByName(args[0]);
		// int port = Integer.parseInt(args[1]); 
		// byte[] sbuf = args[2].getBytes();
		// // TODO: get <opnd>*

		// // send request
		// DatagramPacket packet = new DatagramPacket(sbuf, sbuf.length, address, port);
		// socket.send(packet);
  //       System.out.println("Sent request!");


		// // get response
  //       byte[] rbuf = new byte[sbuf.length];
  //       packet = new DatagramPacket(rbuf, rbuf.length);
  //       socket.receive(packet);
  //       System.out.println("Received a response!");


  //       // display response
  //       String received = new String(packet.getData());
  //       System.out.println("Received Message: " + received);
  //       socket.close();
	}
}