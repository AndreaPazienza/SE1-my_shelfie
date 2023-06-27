import Distributed.ServerRMIInterface;
import Distributed.rmi.Client;
import Errors.SameNicknameException;
import VIEW.GraphicInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static javafx.application.Application.launch;

public class AppClient extends Application {
     Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start (Stage stage) throws Exception{

        this.stage = stage;

        // Per implementazione del online.
        //Registry registry = LocateRegistry.getRegistry("192.168.22.214",1066);
        Registry registry = LocateRegistry.getRegistry(1066);
        ServerRMIInterface server = (ServerRMIInterface) registry.lookup("server");

        Client client = new Client(server,stage);


    }

}
