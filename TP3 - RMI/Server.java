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
        String response = this.dnsService.register(dnsName, ipAddress);
        System.out.println(req + " :: " + response);
        return response;     
    }


    @Override
    public String lookup(String dnsName) throws RemoteException {
        System.out.println("LOOKUP called for " + dnsName);
        String req = "lookup " + dnsName;
        String response = this.dnsService.lookup(dnsName);
        System.out.println(req + " :: " + response);
        return response;
    }

    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java Server <remote_object_name>");
            return;
        }

        try {
            Server server = new Server();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(args[0], stub);

            System.out.println("Server is on with the name " + args[0]);

        } catch (Exception e) {
            System.out.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}