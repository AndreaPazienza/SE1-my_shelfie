import Distributed.ServerRMIInterface;
import Distributed.rmi.Client;
import Errors.SameNicknameException;
import VIEW.GraphicInterface;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static javafx.application.Application.launch;

public class AppClient {

    public static void main(String[] args) throws Exception {
        // Per implementazione del online.
      //Registry registry = LocateRegistry.getRegistry("192.168.22.214",1066);
        Registry registry = LocateRegistry.getRegistry(1066);
       ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server);
        FXMLLoader loader = new FXMLLoader();
        GraphicInterface controller = loader.getController();
        launch(args);

    }
}
