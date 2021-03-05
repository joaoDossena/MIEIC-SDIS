// package example.hello;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
        
public class Server implements RemoteInterface {
        
    private DNSService dnsService = null;

    public Server() {
        this.dnsService = new DNSService();
    }

    @Override
    public String register(String dnsName, String ipAddress) throws RemoteException {
        System.out.println("REGISTER called for " + dnsName + " - " + ipAddress);
        String req = "register " + dnsName + " " + ipAddress;
        String response = this.dnsService.handleRequest(req);
        return response;     
    }


    @Override
    public String lookup(String dnsName) throws RemoteException {
        System.out.println("LOOKUP called for " + dnsName);
        System.out.println("TODO:  do lookup");
        String req = "lookup " + dnsName;
        String response = this.dnsService.handleRequest(req);
        return response;
    }

    public static void main(String args[]) {

        if(args.length != 1) {
            System.out.println("Usage: java Server <remote_object_name>");
            return;
        }

        String remoteObjName = args[0];

        Server serverObj = new Server();

        setVMProperties();

        Registry registry = getRegistry();
        
        try {
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(serverObj, 4445);

            registry.rebind(remoteObjName, stub);

            System.err.println("The server is up and running. Object " + remoteObjName + " is listening at port " + "4445");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}