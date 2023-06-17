import Distributed.ServerRMIInterface;
import Distributed.rmi.Client;
import Distributed.rmi.ServerImpl;
import Errors.SameNicknameException;
import VIEW.ControllerGraphic;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) throws RemoteException, SameNicknameException {
        ServerRMIInterface server = new ServerImpl();
        ControllerGraphic controllerGraphic = new ControllerGraphic();

        Client client1 = new Client(server);
        Client client2 = new Client(server);
        Client client3 = new Client(server);
        client1.run();
        client2.run();
        client3.run();
    }

/*
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayingScreen.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    */

}
