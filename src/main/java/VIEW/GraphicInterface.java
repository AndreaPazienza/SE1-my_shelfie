package VIEW;

import Errors.SameNicknameException;
import MODEL.*;
import Listeners.viewListeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.awt.*;
import java.awt.TextArea;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.cert.PolicyNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

import MODEL.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicInterface extends Application implements viewListeners{

    private final List<viewListeners> listeners = new ArrayList<>();

    private String nickname;
    private CountDownLatch latch;

    private int nPlayers;

    Stage stage;

    @FXML
    TextField nickField;
    @FXML
    Button confirmNickButton;

    @FXML
    ButtonBar numberOfPlayersButtons;

    @FXML
    Button twoPlayersButton;

    @FXML
    Button threePlayersButton;

    @FXML
    Button fourPlayersButton;

    @FXML
    ProgressBar EnrolledBar;

    @FXML
    GridPane tableGrid;
    @FXML
    GridPane shelfGrid;

    @FXML
    ImageView commonGoal1Image;

    @FXML
    ImageView commonGoal2Image;

    @FXML
    ImageView personalGoalImage;

    @FXML
    TextArea textArea1;

    @FXML
    Label textArea2;


    public String firstRun(/*String[] arg*/) throws InterruptedException {
        //launch(arg);
        confirmNickButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nickname = nickField.getText();
                if(nickname.isBlank()){
                    ERRORE DA GESTIRE;
                }
                stage.close();
            }
        });
        return nickname;
    }

    /*public void confirmButton(){
      nickname = nickField.getText();
      latch.countDown();
    }*/



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent nickScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("testStart.fxml")));
        stage.setScene(new Scene(nickScene));
        stage.showAndWait();
    }

    public int numberOfPlayers() throws Exception {
        Parent numberOfPlayersScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NumberOfPlayers.fxml")));
        stage.setScene(new Scene(numberOfPlayersScene));
        stage.showAndWait();
        twoPlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nPlayers = Integer.parseInt(twoPlayersButton.getText());
                stage.close();
            }
        });
        threePlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nPlayers = Integer.parseInt(threePlayersButton.getText());
                stage.close();
            }
        });
        fourPlayersButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nPlayers = Integer.parseInt(fourPlayersButton.getText());
                stage.close();
            }
        });
        return nPlayers;
    }

    public void playing ()throws Exception{

    }

    /*public String confirmNick() {

        String nick = nickField.getText();
        return nick;
        //notifyNick(nick);
    }*/

    /*public void nPlayersButtons(ActionEvent event) {
        Button numberChoice = (Button) event.getSource();
        nPlayers = Integer.parseInt(numberChoice.getText());
    }*/


    public void waitingRoom(int enrolledPlayers, int nPlayers) throws IOException {
        Parent waitingRoomScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WaitingRoom.fxml")));
        stage.setScene(new Scene(waitingRoomScene));
        stage.showAndWait();
        double progress = (double)enrolledPlayers/nPlayers;
        EnrolledBar.setProgress(progress);
    }

    public void playing(GameView gameView) throws Exception {
        displayUpdate(gameView);
        Parent playingScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayingScreen.fxml")));
        stage.setScene(new Scene(playingScene));
        stage.show();
    }

    public void notPlaying(GameView gameView) throws Exception{
        displayUpdate(gameView);
        Parent notPlayingScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NotPlayingScene.fxml")));
        stage.setScene(new Scene(notPlayingScene));
        stage.show();
    }
    public void endgame(GameView gameView) throws Exception{
        setTexts(gameView.getRanking(), textArea1, textArea2);
        Parent endgameScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EndgameScene.fxml")));
        stage.setScene(new Scene(endgameScene));
        stage.show();
    }

    public void displayUpdate(GameView gameView){
        ImageView sfondo = new ImageView ("GraphicResources/boards/livingroom.png");

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(sfondo.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        tableGrid.setBackground(background);



        /*sfondo.setPreserveRatio(true);
        sfondo.fitWidthProperty().bind(tableGrid.widthProperty());
        sfondo.fitHeightProperty().bind(tableGrid.heightProperty());
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sfondo,tableGrid);*/

        for(int i = 0; i < Dashboard.getSide(); i ++) {
            for(int j = 0; j < Dashboard.getSide(); j ++) {

                ImageView tile;

                if (!gameView.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK) && !gameView.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getTable().getSingleSlot(i, j).getColor(), gameView.getTable().getSingleSlot(i, j).getType());
                    //GridPane.setRowIndex(tile, i);
                    //GridPane.setColumnIndex(tile, j);
                    tile.setPreserveRatio(true);
                    tile.setFitHeight(tableGrid.getColumnConstraints().get(i).getPrefWidth());
                    tile.setFitWidth(tableGrid.getRowConstraints().get(j).getPrefHeight());

                    //tile.setFitHeight(20.0);
                    //tile.setFitWidth(20.0);
                      tableGrid.add(tile,i,j);
                }
            }
        }

        for(int i = 0; i < PersonalShelf.N_ROWS; i ++) {
            for(int j = 0; j < PersonalShelf.N_COLUMN; j ++) {

                ImageView tile;

                if (!gameView.getShelf().getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getShelf().getSingleSlot(i, j).getColor(), gameView.getShelf().getSingleSlot(i, j).getType());
                    //GridPane.setRowIndex(tile, i);
                    //GridPane.setColumnIndex(tile, j);
                    //shelfGrid.getChildren().add(tile);
                    tile.setFitHeight(25.0);
                    tile.setFitWidth(25.0);
                    shelfGrid.add(tile,i,j);
                }
            }
        }
    }

    public void displayGoals (GameView gameView) {
        commonGoal1Image = new ImageView(gameView.getCommonGoal1().getImage());
        commonGoal2Image = new ImageView(gameView.getCommonGoal1().getImage());
        //personalGoalImage = new ImageView(gameView.getPgoal().getImage());
    }

    public ImageView setTile(Color color, Type type) {

        ImageView tile = null;

        switch(color) {
            case GREEN -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Gatti1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Gatti1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Gatti1.3.png");
                }
            }
            case PINK -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Piante1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Piante1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Piante1.3.png");
                }
            }
            case BLUE -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Cornici1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Cornici1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Cornici1.3.png");
                }
            }
            case LBLUE -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Trofei1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Trofei1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Trofei1.3.png");
                }
            }
            case WHITE -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Libri1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Libri1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Libri1.3.png");
                }
            }
            case YELLOW -> {
                switch (type) {
                    case TYPE1 -> tile = new ImageView("GraphicResources/item tiles/Giochi1.1.png");
                    case TYPE2 -> tile = new ImageView("GraphicResources/item tiles/Giochi1.2.png");
                    case TYPE3 -> tile = new ImageView("GraphicResources/item tiles/Giochi1.3.png");
                }
            }
        }

        return tile;
    }

    public void setTexts(Player[] ranking, TextArea textArea1, Label textArea2) {

        String rankingText = "";
        String winnerText = "";

        for(int i = 1; i <= ranking.length; i ++) {
            rankingText = rankingText + i + "°: " + ranking[i-1].getNickname() + " \n    punti totalizzati: " + ranking[i-1].getScore() + " \n    ordine nel giro: " + ranking[i-1].getOrderInTurn() + "\n\n";
        }

        winnerText = "Congratulazioni " + ranking[0].getNickname() + " hai vinto questa partita!";

        textArea1.setText(rankingText);
        textArea2.setText(winnerText);

    }

    public void addviewEventListener(viewListeners listener) {
        listeners.add(listener);
        System.out.println("Creato bond client / view \n");
    }

    public void notifyNick(String nick) {
        for( viewListeners listener : listeners  ) {
            //listener.notifyNick(nick);
        }
    }

    public void notifyNumberOfPlayers(int number) {
        for (viewListeners listener : listeners) {
            //listener.notifyNumberOfPlayers(number);
        }
    }


    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        for( viewListeners listener : listeners  ) {
            listener.notifySelectedCoordinates(SC);
        }
    }


    @Override
    public void notifyOrder(OrderChoice o) throws NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            try {
                listener.notifyOrder(o);
            } catch (RemoteException e) {
                System.out.println("ciao");
            } catch (NotEnoughSpaceChoiceException e) {
                throw new RuntimeException(e);
            } catch (NotAdjacentSlotsException e) {
                throw new RuntimeException(e);
            } catch (NotCatchableException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyInsert(column);
        }
    }

    @Override
    public void notifyOneMoreTime() throws IOException, SameNicknameException {
        for( viewListeners listener : listeners  ) {
            listener.notifyOneMoreTime();
        }
    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        for( viewListeners listener : listeners  ) {
            listener.notifyChoices(number);
        }
    }

}
