import Distributed.rmi.Client;
import Distributed.rmi.ServerImpl;
import Distributed.ServerRMIInterface;
import Errors.SameNicknameException;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *  Class that represents the main class for a client-server application using RMI communication.
 */
public class App 
{
    /**
     * The entry point of the communication.
     *
     * @param args The arguments from command line.
     * @throws RemoteException If an error occurs during the remote connection.
     * @throws SameNicknameException If a player choices a nickname already in use.
     * @throws NotBoundException If an attempt is made to lookup or unbind in the registry a name that has no associated binding.
     */
    public static void main(String[] args) throws RemoteException, SameNicknameException, NotBoundException {

        String answer;
        Scanner keyboard = new Scanner(System.in);
        do{
         System.out.print("Vuoi essere client o server? ");
         answer = keyboard.nextLine();
        }while(!answer.equals("client") && !answer.equals("server"));

        if(answer.equals("client")){
            System.out.println("Inserire l'indirizzo ip del server a cui vuoi connetterti: ");
            keyboard = new Scanner(System.in);
            String IP = keyboard.nextLine();
            Registry registry = LocateRegistry.getRegistry(IP,1068);
            ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");
            Client client = new Client(server);
            client.run();
        }else{
            System.out.print("Inserire l'indirizzo ip a cui vuoi essere reperibile:");
            keyboard = new Scanner(System.in);
            String IP = keyboard.nextLine();
            ServerRMIInterface server = new ServerImpl();
            System.setProperty("java.rmi.server.hostname", IP);
            Registry registry = LocateRegistry.createRegistry( 1068);

            try{
                registry.bind("server",server);
                System.out.println("Server in attesa di connesione ");}
            catch (AlreadyBoundException e){
                registry.rebind("server", server);
                System.out.println("Server in attesa di connesioni ");
            }
        }
    }
}
