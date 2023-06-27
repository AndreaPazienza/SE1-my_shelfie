package VIEW;

import Distributed.rmi.Client;
import Errors.SameNicknameException;
import MODEL.*;
import Listeners.viewListeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

import MODEL.Color;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GraphicInterface implements viewListeners  {

    private final List<viewListeners> listeners = new ArrayList<>();

    private CountDownLatch latch;

    private Client client;
    Stage stage;

    private String nick = null;
    private int number = 0;
    private int column = -1;

    private int nPlayers = 0;

    private boolean buttonPressed = false;

    public TextArea getNickField() {
        return nickField;
    }

    @FXML
    private TextArea nickField;

    public Button getConfirmNickButton() {
        return confirmNickButton;
    }

    @FXML
    private Button confirmNickButton;

    @FXML
    private TextArea nickErrorArea;
    @FXML
    private ButtonBar numberOfPlayersButtons;

    @FXML
    private GridPane selectedGrid;

    @FXML
    private GridPane tableGrid;

    @FXML
    GridPane shelfGrid;

    @FXML
    Button confirmSelectionButton;

    @FXML
    Button insertIn0;

    @FXML
    Button insertIn1;

    @FXML
    Button insertIn2;

    @FXML
    Button insertIn3;

    @FXML
    Button insertIn4;

    @FXML
    Button confirmInsertButton;

    @FXML
    ImageView commonGoal1Image;

    @FXML
    ImageView commonGoal2Image;

    @FXML
    ImageView personalGoalImage;

    @FXML
    TextArea errorTextArea;

    @FXML
    TextArea notPlayingTextArea;

    @FXML
    TextArea textArea1;

    @FXML
    Label textArea2;

    @FXML
    ProgressBar enrolledbar;

    @FXML
    TextArea waitingTextArea;

    @FXML
    Button twoPlayersButton;

    @FXML
    Button threePlayersButton;

    @FXML
    Button fourPlayersButton;


  /*  public void initialize(URL location, ResourceBundle resources){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/testStart.fxml"));

    }
*/

    /*@Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent nickScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("testStart.fxml")));
        stage.setScene(new Scene(nickScene));
        stage.show();
    }*/

    /*public int numberOfPlayers() throws Exception {
        Parent numberOfPlayersScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NumberOfPlayers.fxml")));
        stage.setScene(new Scene(numberOfPlayersScene));
        stage.show();
        while (number == 0) {

        }
        return number;
    }*/

    /*public void confirmNick() {
        if(nickField.getText().isBlank()){
            String blanknick = "Il messaggio è vuoto";
            nickErrorArea.setText(blanknick);
        }
        else {
            buttonPressed = true;
        }
    }

     */

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

    public void playing() throws Exception {
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
        if (column < 3 && selectedGrid.getChildren().size() != 0) {
            for (int i = column + 1; i < selectedGrid.getColumnCount(); i++) {
                Node nodeToMove = selectedGrid.getChildren().get(i);
                Tile tileToMove = (Tile) nodeToMove;
                tileToMove.setOnMouseClicked(null);
                selectedGrid.getChildren().remove(tileToMove);
                selectedGrid.add(tileToMove, i - 1, 0);
                tileToMove.setOnMouseClicked(event -> tileDeselected(tileToMove));
            }
        }
        */

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

    public void confirmSelection() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {

        int nChoices = (int) selectedGrid.getChildren().stream()
                .filter(node -> node instanceof Tile)
                .count();
        System.out.println(nChoices);
        //notifyChoices(nChoices);

        SlotChoice[] selection = new SlotChoice[nChoices];
        int i=0;

            for (Node node : selectedGrid.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    System.out.println(i);
                    selection[i] = new SlotChoice(tile.getTileX(), tile.getTileY());
                    i++;
                }
            }

           /* Node node = selectedGrid.getChildren().get(i);
            if(node instanceof Tile) {
                Tile tile = (Tile) node;
                selection[i] = new SlotChoice(tile.getTileX(), tile.getTileY());
                System.out.println(i);
            }*/


        //notifySelectedCoordinates(selection);

        if (nChoices> 1) {
            for (Node node : selectedGrid.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    System.out.println(i);
                    tile.setOrder(i + 1);
                    tile.setOnMouseClicked(null);
                    System.out.println(tile.getOrder());
                }
            }

            setDragAndDrop();
            confirmSelectionButton.setText("Conferma");
            confirmSelectionButton.setOnAction(event -> {
                try {
                    confirmOrder(nChoices);
                } catch (NotAdjacentSlotsException e) {
                    throw new RuntimeException(e);
                } catch (NotCatchableException e) {
                    throw new RuntimeException(e);
                } catch (NotEnoughSpaceChoiceException e) {
                    throw new RuntimeException(e);
                }
            });

        }
    }

    public void confirmOrder(int nChoices) throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        for (Node node : selectedGrid.getChildren()) {
            if (node instanceof Tile) {
                Tile tile = (Tile) node;
                disableDragandDrop(tile);
            }
        }

        if(nChoices == 2) {
            for (Node node : selectedGrid.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    if (tile.getOrder() == 2) {
                        OrderChoice order = new OrderChoice(1, 1, 1);
                        //notifyOrder(order);
                    }
                }
            }
        }

        if(nChoices == 3) {
            int pos1=0;
            int pos2=0;
            int pos3=0;
            Node node;
            Tile tile;
            node = selectedGrid.getChildren().get(0);
            if(node instanceof Tile) {
                tile = (Tile) node;
                pos1 = tile.getOrder();
            }
            node = selectedGrid.getChildren().get(1);
            if(node instanceof Tile) {
                tile = (Tile) node;
                pos2 = tile.getOrder();
            }
            node = selectedGrid.getChildren().get(2);
            if(node instanceof Tile) {
                tile = (Tile) node;
                pos3 = tile.getOrder();
            }

            OrderChoice order = new OrderChoice(pos1, pos2, pos3);
            notifyOrder(order);
        }
    }

    public void setDragAndDrop() {
        System.out.print("sdsfsfs");
        //if I have less than 1 column free
            //abilito drag and drop delle immagini (Istanze Tile) su selectedGrid
            for (Node node : selectedGrid.getChildren()) {
                if (node instanceof Tile) {
                    Tile tile = (Tile) node;
                    enableDragandDrop(tile);
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
    private void disableDragandDrop(Tile tile){
        tile.setOnDragDetected(null);
        tile.setOnDragOver(null);
        tile.setOnDragEntered(null);
        tile.setOnDragExited(null);
        tile.setOnDragDropped(null);
        tile.setOnDragDone(null);
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

    public void playerInsert(){
        insertIn0.setVisible(true);
        insertIn1.setVisible(true);
        insertIn2.setVisible(true);
        insertIn3.setVisible(true);
        insertIn4.setVisible(true);
    }
    public void confirmInsert() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {

        notifyInsert(column);

        int firstSpot = 5;

        for(Node node : shelfGrid.getChildren()) {
            if (shelfGrid.getColumnIndex(node) == column && shelfGrid.getColumnIndex(node) == firstSpot && node instanceof Tile) {
                firstSpot--;
            }
        }

        for(Node node : selectedGrid.getChildren()) {
            if(node instanceof Tile) {
                Tile tileToInsert = (Tile) node;
                shelfGrid.add(tileToInsert, firstSpot ,column);
                tileToInsert.setFitHeight(59.0);
                tileToInsert.setFitWidth(59.0);
                firstSpot++;
            }
        }

    }

    public void onWait() throws Exception{
        Parent notPlayingScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NotPlayingScene.fxml")));
        stage.setScene(new Scene(notPlayingScene));
        stage.show();
    }

    public void displayWin(GameView gameView) throws Exception{
        setTexts(gameView.getRanking(), textArea1, textArea2);
        Parent endgameScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EndgameScene.fxml")));
        stage.setScene(new Scene(endgameScene));
        stage.show();
    }

    public void waitingRoom(int enrolledPlayers, int nPlayers) throws IOException {
        Parent waitingRoomScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WaitingRoom.fxml")));
        stage.setScene(new Scene(waitingRoomScene));
        stage.showAndWait();
        double progress = (double)enrolledPlayers/nPlayers;
        enrolledbar.setProgress(progress);
    }
    public String firstRun(Stage stage) throws InterruptedException, IOException {
        Semaphore semaphore = new Semaphore(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/testStart.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GraphicInterface.setClient(client);
        Scene scene = new Scene(root);
        this.stage = stage;
        stage.setScene(scene);
        stage.show();


        confirmNickButton.setOnAction(event -> {
            String nick = String.valueOf(nickField.getText());
            if (nick.isBlank()) {
                String blankNick = "Il nickname inserito è nullo o formato solo da spazi! Sceglierne un altro!";
                //controller.setNickError(blankNick);
            } else {
            }
            semaphore.release();
        });
        semaphore.acquire();
        return nick;
    }


    public void confirmNick(){
        nick = nickField.getText();
        System.out.print(nick);
        if(nick.isBlank()){
            //ERRORE DA GESTIRE;
            String blankNick = "Il nickname inserito è nullo o formato solo da spazi! Sceglierne un altro!";
            nickErrorArea.setText(blankNick);
        }
    }

    public int numberOfPlayers() throws Exception {
        Platform.runLater(() -> {
            Parent numberOfPlayersScene = null;
            try {
                numberOfPlayersScene = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/NumberOfPlayersScreen.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.close();
        stage.setScene(new Scene(numberOfPlayersScene));
        stage.show();
        twoPlayersButton.setOnAction(event -> {{
                nPlayers = Integer.parseInt(twoPlayersButton.getText());
                stage.close();
            }
        });
        threePlayersButton.setOnAction(event -> {
             {
                nPlayers = Integer.parseInt(threePlayersButton.getText());
                stage.close();
            }
        });
        fourPlayersButton.setOnAction(event -> {
                nPlayers = Integer.parseInt(fourPlayersButton.getText());
                stage.close();
        });

    });
        return nPlayers;
    }




    public void displayDashboard(GameView gameView) {
        for (int i = 0; i < Dashboard.getSide(); i++) {
            for (int j = 0; j < Dashboard.getSide(); j++) {

                Tile tile;

                if (!gameView.getTable().getSingleSlot(i, j).getColor().equals(Color.BLACK) && !gameView.getTable().getSingleSlot(i, j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getTable().getSingleSlot(i, j).getColor(), gameView.getTable().getSingleSlot(i, j).getType(), i, j);
                    tile.setOnMouseClicked(event -> tileSelected(tile));
                    tile.setPreserveRatio(true);
                    tile.setFitHeight(50.0);
                    tile.setFitWidth(50.0);
                    tableGrid.add(tile, j, i);
                }
            }
        }
    }

    public void displayPersonalShelf(GameView gameView) {

        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                Tile tile;
                if (!gameView.getShelf().getSingleSlot(i, j).getColor().equals(Color.GREY)) {
                    tile = setTile(gameView.getShelf().getSingleSlot(i, j).getColor(), gameView.getShelf().getSingleSlot(i, j).getType(), -1, -1);
                    tile.setFitHeight(59.0);
                    tile.setFitWidth(59.0);
                    shelfGrid.add(tile, j, i);
                }
            }
        }
    }

    public void displayCommonGoal (GameView gameView){
        commonGoal1Image = new ImageView(gameView.getCommonGoal1().getImage());
        commonGoal2Image = new ImageView(gameView.getCommonGoal1().getImage());
    }

    public void displayPersonalGoal (GameView gameView){
        personalGoalImage = new ImageView(gameView.getPgoal().getImage());
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
    public void notifyOneMoreTime() throws IOException, SameNicknameException, InterruptedException {
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

    public void errorNotCatchable() {
        errorTextArea.setEditable(true);
        String errorMessage = "La tessera selezionata non è prendibile! Ripetere la selezione!";
        errorTextArea.setText(errorMessage);
        errorTextArea.setEditable(false);
    }

    public void errorOneNotCatchable() {
        errorTextArea.setEditable(true);
        String errorMessage = "Una delle tessere selezionate non è prendibile! Ripetere la selezione!";
        errorTextArea.setText(errorMessage);
        errorTextArea.setEditable(false);
    }

    public void errorNotAdjacent() {
        errorTextArea.setEditable(true);
        String errorMessage = "Le tessere selezionate non sono adiacenti! Ripetere la selezione!";
        errorTextArea.setText(errorMessage);
        errorTextArea.setEditable(false);
    }

    public void errorSpaceChoicesError() {
        errorTextArea.setEditable(true);
        String errorMessage = "Non c'è abbastanza spazio nella shelf per così tante tessere!";
        errorTextArea.setText(errorMessage);
        errorTextArea.setEditable(false);
    }

    public void errorInsert() {
        errorTextArea.setEditable(true);
        String errorMessage = "La colonna selezionata non ha abbastanza spazio per tutte le tessere! Scegline un'altra!";
        errorTextArea.setText(errorMessage);
        errorTextArea.setEditable(false);
    }

    public void errorNick(String message){
        errorTextArea.setEditable(true);
        String nickError = message+"se vuoi giocare prova ad inserirne un altro!";
        nickErrorArea.setText(nickError);
        errorTextArea.setEditable(false);
    }

    public void denyAcess() {
        nickErrorArea.setEditable(true);
        String alreadyStarted = "La partita è già iniziata, troppo tardi :/ ";
        nickErrorArea.setText(alreadyStarted);
        nickErrorArea.setEditable(false);
    }

    public void notifyAlmostOver(){
        notPlayingTextArea.setEditable(true);
        String almostOver = "Alla fine del giro il gioco terminerà!\n";
        notPlayingTextArea.setText(almostOver);
        notPlayingTextArea.setEditable(false);
    }

    public void startTurn() {
        notPlayingTextArea.setEditable(true);
        String newTurn = "-- Inizio del nuovo turno -- \n";
        notPlayingTextArea.setText(newTurn);
        notPlayingTextArea.setEditable(false);
    }

    public void playerCrash() {
        errorTextArea.setEditable(true);
        notPlayingTextArea.setEditable(true);
        String playerCrash = "E' crashato un player ";
        errorTextArea.setText(playerCrash);
        notPlayingTextArea.setText(playerCrash);
        errorTextArea.setEditable(false);
        notPlayingTextArea.setEditable(false);
    }

    public void gameCancelled() {
        waitingTextArea.setEditable(true);
        String stopGame = "E' crashato un player in pregame, chiusura della partita, scusate! ";
        waitingTextArea.setText(stopGame);
        waitingTextArea.setEditable(false);
    }

    public void waitingForPlayers() {
        errorTextArea.setEditable(true);
        notPlayingTextArea.setEditable(true);
        String waitingFor = "Non ci sono abbastanza giocatori per continuare, attesa riconnessione o fine partita in 10s ";
        notPlayingTextArea.setText(waitingFor);
        errorTextArea.setText(waitingFor);
        errorTextArea.setEditable(false);
        notPlayingTextArea.setEditable(false);
    }

    public void endgame() {
        errorTextArea.setEditable(true);
        notPlayingTextArea.setEditable(true);
        String endgame = " Il gioco è finito! ";
        notPlayingTextArea.setText(endgame);
        errorTextArea.setText(endgame);
        errorTextArea.setEditable(false);
        notPlayingTextArea.setEditable(false);
    }

    public static void setClient(Client client) {
        client = client;
    }
}
