import Distributed.rmi.Client;
import Distributed.ServerRMIInterface;
import Errors.SameNicknameException;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *  Class that represents the main class for a client application using RMI communication.
 */
public class AppClient {

    /**
     * The entry point of the communication.
     *
     * @param args The arguments from command line.
     * @throws RemoteException If an error occurs during the remote connection.
     * @throws SameNicknameException If a player choices a nickname already in use.
     * @throws NotBoundException If an attempt is made to lookup or unbind in the registry a name that has no associated binding.
     */
    public static void main(String[] args) throws RemoteException, NotBoundException, SameNicknameException {

        System.out.println("Inserire l'indirizzo ip del server: ");
        Scanner keyboard = new Scanner(System.in);
        String IP = keyboard.nextLine();

       Registry registry = LocateRegistry.getRegistry(IP,1068);
       ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server);
        client.run();
    }
}
