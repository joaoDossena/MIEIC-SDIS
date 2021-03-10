import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    String register(String dnsName, String ipAddress) throws RemoteException;
    String lookup(String ipAddress) throws RemoteException;
}