import java.net.*;
import java.io.*;


public class Advertiser extends Thread {
	private InetAddress multicastAddress;
	private int multicastPort;
	private String message;

	public Advertiser(String name, String multicastAddress, String multicastPort,  String message) throws UnknownHostException {
        super(name);
        this.multicastAddress = InetAddress.getByName(multicastAddress);
        this.multicastPort = Integer.parseInt(multicastPort);
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println("Advertiser - START " + Thread.currentThread().getName());

        MulticastSocket multicastSocket = null;
        try {
            multicastSocket = new MulticastSocket(this.multicastPort);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatagramPacket advertisement = new DatagramPacket(this.message.getBytes(),
										 this.message.getBytes().length, this.multicastAddress, this.multicastPort);

        try {
            while(true){
                Thread.sleep(1000);
                multicastSocket.send(advertisement);
            }

        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }


        multicastSocket.close();

        System.out.println("Advertiser - END "+Thread.currentThread().getName());
    }
}