import Distributed.ServerRMIInterface;
import Distributed.rmi.Client;
import Distributed.rmi.ServerImpl;
import Errors.SameNicknameException;
import MODEL.*;
import VIEW.ControllerGraphic;
import VIEW.GraphicInterface;
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
public class App extends Application {
    public static void main( String[] args ) throws RemoteException, SameNicknameException {
        /*ServerRMIInterface server = new ServerImpl();
        ControllerGraphic controllerGraphic = new ControllerGraphic();

        Client client1 = new Client(server);
        Client client2 = new Client(server);
        Client client3 = new Client(server);
        /*client1.run();
        client2.run();
        client3.run();*/
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EndgameScene.fxml")));

        /*Dashboard table = new Dashboard(2);
        table.refill(new Bag());

        PersonalShelf pShelf = new PersonalShelf();
        Bag bag = new Bag();
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++) {
            pShelf.getSingleSlot(5, i).setColor(bag.getSingleSlot().getColor());
            pShelf.getSingleSlot(5, i).setType(bag.getSingleSlot().getType());
        }
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++) {
            pShelf.getSingleSlot(4, i).setColor(bag.getSingleSlot().getColor());
            pShelf.getSingleSlot(4, i).setType(bag.getSingleSlot().getType());
        }
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++) {
            pShelf.getSingleSlot(3, i).setColor(bag.getSingleSlot().getColor());
            pShelf.getSingleSlot(3, i).setType(bag.getSingleSlot().getType());
        }
        pShelf.getSingleSlot(2,0).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,0).setType(bag.getSingleSlot().getType());*/

        /*GameView gameView = new GameView(pShelf,table);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayingScreen.fxml"));
        Parent playingScene = loader.load();
        GraphicInterface controller = loader.getController();
        controller.displayUpdate(gameView);
        primaryStage.setScene(new Scene(playingScene));
        primaryStage.show();*/

        //primaryStage.setScene(new Scene(root));
        //primaryStage.setResizable(true);
        //primaryStage.show();
        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayingScreen.fxml"));
        Parent playingScene = loader.load();
        primaryStage.setScene(new Scene(playingScene));
        primaryStage.setResizable(true);
        primaryStage.show();*/
    }
/*
    public static void main(String[] args) {
        launch(args);
    }
    */

}
