package Distributed.rmi;

import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.viewListeners;
import MODEL.GameView;
import VIEW.GameInterface;
import VIEW.OrderChoice;
import VIEW.SlotChoice;
import VIEW.turnThread;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface, Serializable {
    private String nickname;
    private final GameInterface view = new GameInterface();
    private final ServerRMIInterface connectedTo;
    private boolean gameState = false;

    public Client(ServerRMIInterface server) throws RemoteException, SameNicknameException {
        super();
        connectedTo = server;
        nickname = view.firstRun();
        view.addviewEventListener(this);
        initialize(server);
    }

    public Client(ServerRMIInterface server, int port) throws RemoteException, SameNicknameException {
        super(port);
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }

    public Client(ServerRMIInterface server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException, SameNicknameException {
        super(port, csf, ssf);
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }


    public void initialize(ServerRMIInterface server) throws RemoteException, SameNicknameException {
        try{
        server.register(this);
        }catch(SameNicknameException e){
           sameNickFound(e.getMessage());
        } catch (NotEnoughSpaceChoiceException | NotCatchableException | NotAdjacentSlotsException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
           // view.gameCancelled();
            view.endgame();
        }
    }

    public void sameNickFound(String errorMessage) throws SameNicknameException, RemoteException {
        view.errorNick(errorMessage);
    }


    public void run() {
      view.run();
    }

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
    public void notifyOrder(OrderChoice o) throws RemoteException {
        connectedTo.updateServerReorder(this, o);
    }

    //Observer: when the insertion column is chosen from the view
    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerInsert(this, column);
    }

    @Override
    public void notifyOneMoreTime() throws SameNicknameException, RemoteException {
        nickname = view.firstRun();
        try{connectedTo.register(this);}
        catch (RemoteException | SameNicknameException | NotEnoughSpaceChoiceException | NotAdjacentSlotsException |
               NotCatchableException e){
            sameNickFound(e.getMessage());
        }
    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerChoices(this, number);
    }

    //When the server has a new update, it is sent and displayed by the client.
    @Override
    public void updateClientFirst(GameView modelView) {
        gameState= modelView.getGameState();
        view.displayCommonGoal(modelView);
        view.displayDashboard(modelView.getTable());
        view.displayPersonalGoal(modelView.getPgoal());
       // view.onWait();
    }
    //Manda al giocaente la situazione attuale e la propria personal shelf
    @Override
    public void updateClientPlaying(GameView modelView) {
        gameState = modelView.getGameState();
        view.displayDashboard(modelView.getTable());
        view.displayPersonalShelf(modelView.getShelf());
    }
    //Manda a tutti i client la nuova board
    @Override
    public void updateClientRound(GameView model) throws RemoteException {
        gameState= model.getGameState();
        view.displayDashboard(model.getTable());
    }


    //Remote method: passing the nickname to the server.
    @Override
    public String getNickname() throws RemoteException {
        return this.nickname;
    }

    //Remote method: used to notify the first client that the number of players is missing
    @Override
    public int startGame() throws RemoteException {
        return view.numberOfPlayers();
    }

    //Remote method: when a new client registers, those already logged in are notified.
    @Override
    public void newPlayerAdded() throws RemoteException {
        view.arrived();
    }

    //Remote method: to notify the client that they still have to wait for their turn.
    public void onWait() throws RemoteException{
        view.onWait();
    }

    //Remote method: begin of turn
    public void startTurn() throws RemoteException{
       if(gameState){
        view.startTurn();
        turnThread play = new turnThread(view);
        play.start();
       }else{
           view.endgame();
       }
    }

    //Remote method:: end turn
    public void endTurn(){
        if(gameState) {
            view.onWait();
        }else{
            view.endgame();
        }
    }

    @Override
    public void winnerInterface(String winner ) throws RemoteException {
        gameState=false;
        view.displayWin(winner);
    }

    @Override
    public void notifyCompleted() throws RemoteException {
        view.notifyAlmostOver();

    }

    @Override
    public void errorNotCatchable() throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {
        view.errorNotCatchable();
    }

    @Override
    public void errorNotify(String message) throws RemoteException, NotCatchableException, NotAdjacentSlotsException {
        view.notifyError(message);
    }

    @Override
    public void errorNotAdjacent() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException {
        view.errorNotAdjacent();
    }

    @Override
    public void errorNotEnoughSpace() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        view.errorNotEnoughSpace();
    }

    @Override
    public void errorNotifyInsert(String message) throws RemoteException, NotEnoughSpaceChoiceException {
        view.notifyError(message);
    }

    @Override
    public void errorChoices(String message) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        view.errorNotAllowedChoice(message);
    }

    @Override
    public void notifyGameStarted() throws RemoteException {
        view.denyAcess();
    }

    @Override
    public void errorCrash() throws RemoteException {
        view.endgame();
    }

    @Override
    public void ping() throws RemoteException {
    }

    @Override
    public void subscriptionCancelled() throws RemoteException {
        view.gameCancelled();
    }

}
