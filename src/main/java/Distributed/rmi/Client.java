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
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class that represents a client that interacts with a remote server using RMI (Remote Method Invocation).
 */
public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface, Serializable {
    private String nickname;
    private final GameInterface view = new GameInterface();
    private final ServerRMIInterface connectedTo;

    /**
     * Constructor for the Client class.
     *
     * @param server
     * @throws RemoteException
     * @throws SameNicknameException
     */
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
        try {
            server.register(this);
        } catch (SameNicknameException e) {
            sameNickFound(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
         System.err.println("Non riesco a chiamare il server ");
        } catch (NotEnoughSpaceChoiceException | NotCatchableException | NotAdjacentSlotsException | IOException |
                 ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void sameNickFound(String errorMessage) throws SameNicknameException, RemoteException {
        view.errorNick(errorMessage);
    }


    public void run() {
        view.run();
    }

    @Override
    public void addviewEventListener(viewListeners listener) {
        System.out.println("\t Connessione Client/Server stabilita \n");
    }


    //Observer: when coordinates are taken from the view
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {
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
    public void notifyOneMoreTime() throws SameNicknameException, RemoteException {
        nickname = view.firstRun();
        try {
            connectedTo.register(this);
        } catch (RemoteException | SameNicknameException | NotEnoughSpaceChoiceException | NotAdjacentSlotsException |
                 NotCatchableException e) {
            sameNickFound(e.getMessage());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        connectedTo.updateServerChoices(this, number);
    }

    /**
     * Sends to the user the first game view containing the personal goal, the common goals and the initial dashboard.
     *
     * @param modelView The starting game view.
     */
    @Override
    public void updateClientFirst(GameView modelView) {
        view.displayCommonGoal(modelView);
        view.displayPersonalGoal(modelView.getPgoal());
        view.displayDashboard(modelView.getTable());

    }

    /**
     * Sends to the user that is playing the game view containing the dashboard, the personal shelf and a reduced description of the common goals and the personal goal.
     *
     * @param modelView The complete game view.
     */
    @Override
    public void updateClientPlaying(GameView modelView) {
        view.displayPersonalShelf(modelView.getShelf());
        view.displayDashboard(modelView.getTable());
        view.displayPersonalGoal2(modelView.getPgoal());
        view.commonGoalReminder(modelView.getCommonGoal1());
        view.commonGoalReminder(modelView.getCommonGoal2());
    }

    /**
     * Sends to the user that is not playing the game view containing the dashboard.
     *
     * @param model The reduced game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void updateClientRound(GameView model) throws RemoteException {
        view.displayDashboard(model.getTable());
    }

    /**
     * Retrieves the nickname insert by the user that logged in.
     *
     * @return The nickname insert by the user that logged in.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public String getNickname() throws RemoteException {
        return this.nickname;
    }

    /**
     * Retrieves the number of players in the game insert by the first user to log in.
     *
     * @return The number of players in the game.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public int startGame() throws RemoteException {
        return view.numberOfPlayers();
    }

    /**
     * Notifies the user that another user logged in.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void newPlayerAdded() throws RemoteException {
        view.arrived();
    }

    /**
     * Notifies the user to wait for his turn.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    public void onWait() throws RemoteException {
        view.onWait();
    }

    /**
     * Starts the thread that manages the turn.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    public void startTurn() throws RemoteException {
        turnThread play = new turnThread(view);
        view.startTurn();
        play.start();
    }


    public void endTurn() {
            view.onWait();
    }

    /**
     * Notifies the user with the ranking and winner of the match.
     *
     * @param winner The ranking and winner of the match.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void winnerInterface(String winner) throws RemoteException {
        view.displayWin(winner);
    }

    /**
     * Notifies the user that the match finished.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void notifyCompleted() throws RemoteException {
        view.notifyAlmostOver();

    }

    /**
     * Notifies the user that the match started.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void notifyGameStarted() throws RemoteException {
        view.denyAcess();
    }

    /**
     * Notifies the user that another user crashed.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void errorCrash() throws RemoteException {
        view.playerCrash();
    }

    /**
     * Ping the user to check if it's still connected.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void ping() throws RemoteException {
    }

    /**
     * Notifies the user that the game is cancelled.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void subscriptionCancelled() throws RemoteException {
        view.gameCancelled();
    }

    /**
     * Notifies the user that the game stopped due to the lack of players.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void errorMissingPlayers() throws RemoteException {
        view.waitingForPlayers();
    }


    /**
     * Notifies the user that the game finished due to the lack of players.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void errorEndGameNoMorePlayers() throws RemoteException {
        view.endgame();
    }

    /**
     * Resumes the user interface from the phase where the last error occurred.
     *
     * @param modelView The error game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    @Override
    public void updateClientError(GameView modelView) throws RemoteException {
        GameError error = modelView.getGameError();
        printerErrors(error);
        switch (error) {
            case SELECT_ERROR_NOT_CATCHABLE, SELECT_ERROR_ONE_NOT_CATCHABLE, SELECT_ERROR_NOT_ADJACENT, SPACE_CHOICES_ERROR -> {
                turnThread play2 = new turnThread(view);
                play2.start();
            }

            case INSERT_ERROR -> {
                insertThread play3 = new insertThread(view);
                play3.start();
            }
        }
    }

    /**
     * Notifies the user the last error occurred in the game.
     *
     * @param error The last error occurred.
     */
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


