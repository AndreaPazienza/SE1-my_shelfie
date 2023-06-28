
import Distributed.*;
import Distributed.rmi.*;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppServer {


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