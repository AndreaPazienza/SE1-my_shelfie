package VIEW;

import Errors.SameNicknameException;
import MODEL.*;
import Listeners.viewListeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import MODEL.Color;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicInterface extends Application implements viewListeners{

    private final List<viewListeners> listeners = new ArrayList<>();

    Stage stage;

    private String nick = null;
    private int number = 0;
    private int column = -1;

    @FXML
    TextField nickField;
    @FXML
    Button confirmNickButton;

    @FXML
    ButtonBar numberOfPlayersButtons;

    @FXML
    GridPane selectedGrid;

    @FXML
    GridPane tableGrid;

    @FXML
    GridPane shelfGrid;

    @FXML
    Button confirmSelectionButton;

    @FXML
    Button confirmInsertButton;

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


    public String fistRun(String[] arg) {
        launch(arg);
        while (nick == null) {

        }
        return nick;
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent nickScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("testStart.fxml")));
        stage.setScene(new Scene(nickScene));
        stage.show();
    }

    public int numberOfPlayers() throws Exception {
        Parent numberOfPlayersScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NumberOfPlayers.fxml")));
        stage.setScene(new Scene(numberOfPlayersScene));
        stage.show();
        while (number == 0) {

        }
        return number;
    }

    public void confirmNick() {

        nick = nickField.getText();
    }

    public void setNumber2 () {

        number = 2;
    }

    public void setNumber3 () {

        number = 3;
    }

    public void setNumber4 () {

        number = 4;
    }

    /*

    Gestione waiting room

     */

    public void playing(GameView gameView) throws Exception {
        displayUpdate(gameView);
        Parent playingScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("PlayingScreen.fxml")));
        stage.setScene(new Scene(playingScene));
        stage.show();
    }

    public void tileSelected(Tile selectedTile) {
        int nextColumn = findFirstEmptyColumn(selectedGrid);

        if (nextColumn != -1 && nextColumn <= 2) {
            selectedTile.setOnMouseClicked(null);
            tableGrid.getChildren().remove(selectedTile);
            selectedTile.setFitHeight(90.0);
            selectedTile.setFitWidth(90.0);
            selectedGrid.add(selectedTile, nextColumn, 0);
            selectedTile.setOnMouseClicked(event -> tileDeselected(selectedTile));
        }
    }

    private int findFirstEmptyColumn(GridPane gridPane) {
        int columnCount = gridPane.getColumnCount();

        for (int column = 0; column < columnCount; column++) {
            boolean isEmpty = true;
            boolean found = false;
            for (Node node : gridPane.getChildren()) {
                if (found==false) {
                    if (GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column) {
                        isEmpty = false;
                        found = true;
                        break;
                    }
                }
            }
            if (isEmpty) {
                return column;
            }
        }
        return -1; // Se non viene trovata una colonna libera
    }

    public void tileDeselected(Tile deselectedTile) {

        int column = selectedGrid.getColumnIndex(deselectedTile);

        deselectedTile.setOnMouseClicked(null);
        selectedGrid.getChildren().remove(deselectedTile);
/*
        selectedGrid.getChildren().forEach(child -> {
            int childColumn = selectedGrid.getColumnIndex(child);
            if (childColumn > column) {
                selectedGrid.setColumnIndex(child, childColumn - 1);
            }
        });

*/
        deselectedTile.setFitHeight(50.0);
        deselectedTile.setFitWidth(50.0);
        tableGrid.add(deselectedTile, deselectedTile.getTileY(), deselectedTile.getTileX());
        deselectedTile.setOnMouseClicked(event -> tileSelected(deselectedTile));
    }

    /*public void confirmSelection() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {

        int nChoices = (int) selectedGrid.getChildren().stream()
                .filter(node -> node instanceof Tile)
                .count();

        notifyChoices(nChoices);

        SlotChoice[] selection = new SlotChoice[nChoices];

        for (int i = 0; i < nChoices; i ++) {
            Node node = selectedGrid.getChildren().get(i);
            Tile tile = (Tile) node;
            selection[i] = new SlotChoice(tile.getTileX(), tile.getTileY());
        }

        notifySelectedCoordinates(selection);
    }
     */

    public void confirmSelection() {
        int check = findFirstEmptyColumn(selectedGrid);
        //if I have less than 1 column free
        if (check <= 1) {
            //abilito drag and drop delle immagini (Istanze Tile) su selectedGrid
            for (Node node : selectedGrid.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    enableDragandDrop(tile);
                }
            }
        }
    }

    private void enableDragandDrop(Tile tile){
        //start of drag and drop
        tile.setOnDragDetected(event -> {
            Dragboard dragboard = tile.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            //get the image of Tile
            ImageView card = new ImageView(tile.getImage());
            card.setFitWidth(100.0);
            card.setFitHeight(100.0);
            content.putImage(card.snapshot(null,null));
            dragboard.setContent(content);
            event.consume();
        });
        tile.setOnDragOver(event -> {
            if (event.getGestureSource() != tile && event.getDragboard().hasImage()) {
                //accept the transfer mode for the drag over event
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });
        tile.setOnDragEntered(event -> {
            if (event.getGestureSource() != tile && event.getDragboard().hasImage()) {
                tile.setOpacity(0.7);
            }
            event.consume();
        });
        tile.setOnDragExited(event -> {
            if (event.getGestureSource() != tile && event.getDragboard().hasImage()) {
                tile.setOpacity(1.0);
            }
            event.consume();
        });

        tile.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasImage()) {
                ImageView sourceImageView = (ImageView) event.getGestureSource();
                ImageView targetImageView = (ImageView) event.getSource();

                // Scambia le immagini tra le due ImageView
                Image sourceImage = sourceImageView.getImage();
                sourceImageView.setImage(targetImageView.getImage());
                targetImageView.setImage(sourceImage);

                success = true;
            }

            event.setDropCompleted(success);
            event.consume();
        });
        //reset opacity
        tile.setOnDragDone(event -> {
            if (event.getTransferMode() == TransferMode.MOVE) {
                tile.setOpacity(1.0);
            }
            event.consume();
        });
    }


    public void setColumn0() {

        column = 0;
        confirmInsertButton.setVisible(true);
    }

    public void setColumn1() {

        column = 1;
        confirmInsertButton.setVisible(true);
    }

    public void setColumn2() {

        column = 2;
        confirmInsertButton.setVisible(true);
    }

    public void setColumn3() {

        column = 3;
        confirmInsertButton.setVisible(true);
    }

    public void setColumn4() {

        column = 4;
        confirmInsertButton.setVisible(true);
    }

    public void confirmInsert() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {

        notifyInsert(column);
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
        /*ImageView sfondo = new ImageView ("GraphicResources/boards/livingroom.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(sfondo.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImage);
        tableGrid.setBackground(background);*/
        /*sfondo.setPreserveRatio(true);
        sfondo.fitWidthProperty().bind(tableGrid.widthProperty());
        sfondo.fitHeightProperty().bind(tableGrid.heightProperty());
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(sfondo,tableGrid);*/

        for(int i = 0; i < Dashboard.getSide(); i ++) {
            for(int j = 0; j < Dashboard.getSide(); j ++) {

                Tile tile;

                if (!gameView.getTable().getSingleSlot(i,j).getColor().equals(Color.BLACK) && !gameView.getTable().getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getTable().getSingleSlot(i, j).getColor(), gameView.getTable().getSingleSlot(i, j).getType(), i, j);
                    tile.setOnMouseClicked(event -> tileSelected(tile));
                    //GridPane.setRowIndex(tile, i);
                    //GridPane.setColumnIndex(tile, j);
                    tile.setPreserveRatio(true);
                    //tile.setFitHeight(tableGrid.getColumnConstraints().get(i).getPrefWidth());
                    //tile.setFitWidth(tableGrid.getRowConstraints().get(j).getPrefHeight());

                    tile.setFitHeight(50.0);
                    tile.setFitWidth(50.0);
                    tableGrid.add(tile,j,i);
                }
            }
        }

        for(int i = 0; i < PersonalShelf.N_ROWS; i ++) {
            for(int j = 0; j < PersonalShelf.N_COLUMN; j ++) {

                Tile tile;

                if (!gameView.getShelf().getSingleSlot(i,j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getShelf().getSingleSlot(i, j).getColor(), gameView.getShelf().getSingleSlot(i, j).getType(), -1, -1);
                    //GridPane.setRowIndex(tile, i);
                    //GridPane.setColumnIndex(tile, j);
                    //shelfGrid.getChildren().add(tile);
                    tile.setFitHeight(59.0);
                    tile.setFitWidth(59.0);
                    shelfGrid.add(tile,j,i);
                }
            }
        }
    }

    public void displayGoals (GameView gameView) {
        commonGoal1Image = new ImageView(gameView.getCommonGoal1().getImage());
        commonGoal2Image = new ImageView(gameView.getCommonGoal1().getImage());
        //personalGoalImage = new ImageView(gameView.getPgoal().getImage());
    }

    public Tile setTile(Color color, Type type, int x, int y) {

        Tile tile = null;

        switch(color) {
            case GREEN -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Gatti1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Gatti1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Gatti1.3.png", x, y);
                }
            }
            case PINK -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Piante1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Piante1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Piante1.3.png", x, y);
                }
            }
            case BLUE -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Cornici1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Cornici1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Cornici1.3.png", x, y);
                }
            }
            case LBLUE -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Trofei1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Trofei1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Trofei1.3.png", x, y);
                }
            }
            case WHITE -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Libri1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Libri1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Libri1.3.png", x, y);
                }
            }
            case YELLOW -> {
                switch (type) {
                    case TYPE1 -> tile = new Tile("GraphicResources/item tiles/Giochi1.1.png", x, y);
                    case TYPE2 -> tile = new Tile("GraphicResources/item tiles/Giochi1.2.png", x, y);
                    case TYPE3 -> tile = new Tile("GraphicResources/item tiles/Giochi1.3.png", x, y);
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
    public void notifyOneMoreTime() throws RemoteException, SameNicknameException {
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
