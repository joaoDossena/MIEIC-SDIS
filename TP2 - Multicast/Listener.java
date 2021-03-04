import java.net.*;
import java.io.*;

public class Listener extends Thread {

    private int port;

	public Listener(String name, String port) {
		super(name);
        this.port = Integer.parseInt(port);
	}

	@Override
    public void run() {
        System.out.println("Listener - START " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
            //make this a while loop
            listen();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Listener - END "+Thread.currentThread().getName());
    }

    private void listen() {
        try{
            DatagramSocket socket = new DatagramSocket(this.port);
            System.out.println("Socket created\nListening...");

            // receiving request
            byte[] rbuf = new byte[256];
            DatagramPacket packet = new DatagramPacket(rbuf, rbuf.length);        
            socket.receive(packet);
            String request = new String(packet.getData());

            // process request
            System.out.println("Request: " + request);
            if(request == "REGISTER")
                // TODO: register DNS name and IP address to hashmap
                System.out.println("The DNS name " + "\"<DNS name>\"" 
                    + " was registered as the following IP Address: " + "\"<IP Address>\"");
            else if(request == "LOOKUP")
                // TODO: send IP address back
                System.out.println("The DNS name " + "\"<DNS name>\"" 
                    + " translates to the following IP Address: " + "\"<IP Address>\"");
            else
                System.out.println("Error: this command is unknown. The possible commands are REGISTER and LOOKUP");

            // send response
            InetAddress destAddress = packet.getAddress();
            int destPort = packet.getPort();
            packet = new DatagramPacket(rbuf, rbuf.length, destAddress, destPort);
            socket.send(packet);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}