
import Distributed.*;
import Distributed.rmi.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppServer {
    public static void main(String[] args) throws RemoteException{

        ServerRMIInterface server = new ServerImpl();
        String Ip = "192.168.22.176";
        Registry registry = LocateRegistry.createRegistry( 1066);
     try{
            registry.bind("server",server);}catch (AlreadyBoundException e){
            registry.rebind("server", server);
      }


    }

}