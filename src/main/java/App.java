import Distributed.rmi.Client;
import Distributed.rmi.ServerImpl;
import Distributed.ServerRMIInterface;
import Errors.SameNicknameException;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws RemoteException, SameNicknameException, IOException {
        ServerRMIInterface server = new ServerImpl();

        Client client1 = new Client(server);
        Client client2 = new Client(server);
        Client client3 = new Client(server);
        client1.run();
        client2.run();
        client3.run();
    }
}
