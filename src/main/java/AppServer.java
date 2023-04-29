import Distributed.rmi.ServerImpl;
import Distributed.ServerRMIInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        ServerRMIInterface server = new ServerImpl();

        Registry registry = LocateRegistry.getRegistry();
        try{
        registry.bind("server",server);}catch (AlreadyBoundException e){
            registry.rebind("server", server);
        }


    }

}
