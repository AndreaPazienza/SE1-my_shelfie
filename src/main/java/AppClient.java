
import Distributed.*;
import Distributed.rmi.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
       // Registry registry = LocateRegistry.getRegistry(10.169.232.254, 1066)
        // Per implementazione del online.
        Registry registry = LocateRegistry.getRegistry("10.169.140.245", 1066);
        ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server);
        client.run();
    }
}

//Fix ricezione delle view.