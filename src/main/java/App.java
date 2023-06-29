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
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException, SameNicknameException, NotBoundException {

        String answer;
        Scanner keyboard = new Scanner(System.in);
        do{
         System.out.print("Vuoi essere client o server? ");
         answer= keyboard.nextLine();
        }while(!answer.equals("client") && !answer.equals("server"));

        if(answer.equals("client")){
            //Manca controllo del input
            System.out.println("Inserire l'indirizzo ip del server a cui vuoi connetterti: ");
            keyboard = new Scanner(System.in);
            String IP = keyboard.nextLine();
            //Si usa next Line o next Int?
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
