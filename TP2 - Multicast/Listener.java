import java.net.*;
import java.io.*;

public class Listener extends Thread {

	public Listener(String name) {
		super(name);
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

    private void listen() throws InterruptedException {
        System.out.println("Listening...");
		// DatagramSocket socket = new DatagramSocket();
		// byte[] buf = (this.ipAddress + (String)this.portNumber).getBytes();

		// DatagramPacket packet = new DatagramPacket(buf, buf.length, this.group, this.multicastPort);
  //       socket.send(packet);
  //       socket.close();
    }
}