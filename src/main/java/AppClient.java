import Distributed.rmi.Client;
import Distributed.ServerRMIInterface;
import Distributed.rmi.ServerImpl;
import Errors.SameNicknameException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, SameNicknameException {

        System.out.println("Inserire l'indirizzo ip del server: ");
        Scanner keyboard = new Scanner(System.in);
        String IP = keyboard.nextLine();

        // Per implementazione del online.
       Registry registry = LocateRegistry.getRegistry(IP,1067);
         //Registry registry = LocateRegistry.getRegistry(1067);
       ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server);
        client.run();
    }
}
