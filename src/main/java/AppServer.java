import Distributed.rmi.ServerImpl;
import Distributed.ServerRMIInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {

        ServerRMIInterface server = new ServerImpl();
        Registry registry = LocateRegistry.createRegistry(1066);
        try{
            //System.setProperty("java.rmi.server.hostname","192.168.1.74");
            registry.bind("server",server);}catch (AlreadyBoundException e){
            registry.rebind("server", server);
        }


    }

}
