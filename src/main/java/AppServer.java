
import Distributed.*;
import Distributed.rmi.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *  Class that represents the main class for a server application using RMI communication.
 */
public class AppServer {

    /**
     * The entry point of the communication.
     *
     * @param args The arguments from command line.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    public static void main(String[] args) throws RemoteException{

        System.out.print("Inserire l'indirizzo ip del server: ");
        Scanner keyboard = new Scanner(System.in);
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