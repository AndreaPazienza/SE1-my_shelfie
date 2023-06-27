package Distributed.rmi;

import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.viewListeners;
import MODEL.GameError;
import MODEL.GameView;
import VIEW.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface, Serializable {
    private String nickname;
    //private final GameInterface view = new GameInterface();
    private final GraphicInterface view = new GraphicInterface();

    private Stage stage;

    private String[] arg;
    private final ServerRMIInterface connectedTo;
    private boolean gameState = false;
    private boolean endGame;

    public Client(ServerRMIInterface server,Stage stage) throws RemoteException, IOException, SameNicknameException, InterruptedException {
        super();
        connectedTo = server;
        this.stage= stage;
        nickname = view.firstRun(stage);
        view.addviewEventListener(this);
        initialize(server);
    }
    public Client(ServerRMIInterface server) throws RemoteException, IOException, SameNicknameException, InterruptedException {
        super();
        connectedTo = server;
        nickname = view.firstRun(stage);
        view.addviewEventListener(this);
        initialize(server);
    }

    public Client(ServerRMIInterface server, int port) throws RemoteException, IOException, SameNicknameException, InterruptedException {
        super(port);
        nickname = view.firstRun(stage);
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }

    public Client(ServerRMIInterface server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException, IOException, SameNicknameException, InterruptedException {
        super(port, csf, ssf);
        nickname = view.firstRun(stage);
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }


    public void initialize(ServerRMIInterface server) throws RemoteException, SameNicknameException, IOException {
        try {
            server.register(this);
        } catch (SameNicknameException e) {
            //sameNickFound(e.getMessage());
        } catch (NotEnoughSpaceChoiceException | NotCatchableException | NotAdjacentSlotsException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
         System.err.println("Non riesco a chiamare il server ");
        }
    }

    /*public void sameNickFound(String errorMessage) throws SameNicknameException, RemoteException {
        view.errorNick(errorMessage);
    }*/


    /*public void run() {
        view.run();
    }*/

   /* public void start(Stage stage) throws Exception {
        view.start(new Stage());
    }*/

    //Observer:
    @Override
    public void addviewEventListener(viewListeners listener) {
        System.out.println("\t Connessione Client/Server stabilita \n");
    }


    //Observer: when coordinates are taken from the view
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {
        System.out.println("Invio delle coordinate in corso; \n");
        connectedTo.updateServerSelection(this, SC);
    }

    //Observer: when the sorting is chosen from the view
    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerReorder(this, o);
    }

    //Observer: when the insertion column is chosen from the view
    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerInsert(this, column);
    }

    @Override
    public void notifyOneMoreTime() throws SameNicknameException, IOException, RemoteException, InterruptedException {
        nickname = view.firstRun(stage);
        try {
            connectedTo.register(this);
        } catch (Exception e) {
            //sameNickFound(e.getMessage());
        }
    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerChoices(this, number);
    }

    //When the server has a new update, it is sent and displayed by the client.
    @Override
    public void updateClientFirst(GameView modelView) {
        gameState = modelView.getGameState();
        view.displayCommonGoal(modelView);
        view.displayPersonalGoal(modelView);
        view.displayDashboard(modelView);

        // view.onWait();
    }

    //Manda al giocaente la situazione attuale e la propria personal shelf
    @Override
    public void updateClientPlaying(GameView modelView) {
        gameState = modelView.getGameState();
        /*for(int i = 0; i < 6; i++){
            view.displayTarget(i, modelView);
        }*/
        view.displayDashboard(modelView);
        view.displayPersonalShelf(modelView);
    }

    //Manda a tutti i client la nuova board
    @Override
    public void updateClientRound(GameView model) throws RemoteException {
        gameState = model.getGameState();
        view.displayDashboard(model);
    }



    //Remote method: passing the nickname to the server.
    @Override
    public String getNickname() throws RemoteException {
        return this.nickname;
    }

    //Remote method: used to notify the first client that the number of players is missing
    @Override
    public int startGame() throws Exception {
        return view.numberOfPlayers();
    }

    //Remote method: when a new client registers, those already logged in are notified.
    @Override
    public void newPlayerAdded(int enrolledPlayers, int nPlayers) throws RemoteException, IOException {
        view.waitingRoom(enrolledPlayers, nPlayers);
    }

    //Remote method: to notify the client that they still have to wait for their turn.
    public void onWait() throws Exception {
        view.onWait();
    }

    //Remote method: begin of turn
    public void startTurn() throws RemoteException {
        System.out.println("Avvio il turno per la n volta ");
        turnThread play = new turnThread(view);
        //thread non si chiude per la prima volta
         //if (gameState) {
            view.startTurn();
            play.start();
         //}
    }

    //Remote method:: end turn
    public void endTurn() throws Exception {
       // if (gameState) {
            view.onWait();
       // }
    }

    @Override
    public void winnerInterface(String winner) throws RemoteException {
        gameState = false;
        //view.displayWin(winner);
    }

    @Override
    public void notifyCompleted() throws RemoteException {
        view.notifyAlmostOver();

    }

    @Override
    public void notifyGameStarted() throws RemoteException {
        view.denyAcess();
    }

    @Override
    public void errorCrash() throws RemoteException {
        view.playerCrash();
    }

    @Override
    public void ping() throws RemoteException {
    }

    @Override
    public void subscriptionCancelled() throws RemoteException {
        view.gameCancelled();
    }

    @Override
    public void errorMissingPlayers() throws RemoteException {
        view.waitingForPlayers();
    }

    @Override
    public void errorEndGameNoMorePlayers() throws RemoteException {
        endGame = true;
        view.endgame();
    }

    @Override
    public void updateClientError(GameView modelView) throws RemoteException {
        GameError error = modelView.getGameError();
        printerErrors(error);
        switch (error) {
            case SELECT_ERROR_NOT_CATCHABLE, SELECT_ERROR_ONE_NOT_CATCHABLE, SELECT_ERROR_NOT_ADJACENT, SPACE_CHOICES_ERROR -> {
                System.out.println("Avvio play 2 ");
                turnThread play2 = new turnThread(view);
                play2.start();
            }

            case INSERT_ERROR -> {
                System.err.println("Avvio il 3");
                insertThread play3 = new insertThread(view);
                play3.start();
            }
        }
    }

    public void printerErrors(GameError error){
        switch (error){
            case SELECT_ERROR_NOT_CATCHABLE -> view.errorNotCatchable();
            case SELECT_ERROR_ONE_NOT_CATCHABLE -> view.errorOneNotCatchable();
            case SELECT_ERROR_NOT_ADJACENT -> view.errorNotAdjacent();
            case SPACE_CHOICES_ERROR -> view.errorSpaceChoicesError();
            case INSERT_ERROR -> view.errorInsert();
        }
    }
}


