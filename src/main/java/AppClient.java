import Distributed.ServerRMIInterface;
import Distributed.rmi.Client;
import Errors.SameNicknameException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, SameNicknameException {
        // Per implementazione del online.
      //Registry registry = LocateRegistry.getRegistry("192.168.22.214",1066);
        Registry registry = LocateRegistry.getRegistry(1066);
       ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server);
        client.run();
    }
}
