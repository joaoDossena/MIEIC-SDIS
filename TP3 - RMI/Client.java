import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public Client() {}

    public static void main(String[] args) {


        if(args.length < 4 || args.length > 5){
            System.out.println("Usage: java Client <host_name> <remote_object_name> <oper> <opnd>*");
            return;
        }

        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            RemoteInterface stub = (RemoteInterface) registry.lookup(args[1]);
            String response = "";
            String command = args[2].toUpperCase();
            if(command.equals("REGISTER") && args.length == 5){
                response = stub.register(args[3], args[4]);
                System.out.println(command + " " + args[3] + " " + args[4] + " :: " + response);
            }
            else if(command.equals("LOOKUP") && args.length == 4){
                response = stub.lookup(args[3]);
                System.out.println(command + " " + args[3] + " :: " + response);
            }
            else{
                System.out.println("ERROR: Unknown command or wrong number of arguments! The available commands are 'REGISTER <DNS Name> <IP Address>' and 'LOOKUP <IP Address>'");
                return;
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}