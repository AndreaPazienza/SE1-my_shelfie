package Distributed;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.GameView;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the client-side application methods that can be invoked on a remote object using RMI.
 */
public interface ClientRMIInterface extends Remote {

    /**
     * Sends to the user the first game view containing the personal goal, the common goals and the initial dashboard.
     *
     * @param view The starting game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void updateClientFirst(GameView view) throws RemoteException;

    /**
     * Sends to the user that is not playing the game view containing the dashboard.
     *
     * @param model The reduced game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void updateClientRound(GameView model) throws RemoteException;

    /**
     * Sends to the user that is playing the game view containing the dashboard, the personal shelf and a reduced description of the common goals and the personal goal.
     *
     * @param model The complete game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void updateClientPlaying(GameView model) throws RemoteException;

    /**
     * Retrieves the nickname insert by the user that logged in.
     *
     * @return The nickname insert by the user that logged in.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    String getNickname() throws RemoteException;

    /**
     * Retrieves the number of players in the game insert by the first user to log in.
     *
     * @return The number of players in the game.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    int startGame() throws RemoteException;

    /**
     * Notifies the user to wait for his turn.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void onWait() throws RemoteException;

    /**
     * Notifies the user that another user logged in.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void newPlayerAdded() throws RemoteException;

    /**
     * Notifies the user the beginning of the turn.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    void startTurn() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException;

    void endTurn() throws RemoteException;

    /**
     * Notifies the user with the winner of the match.
     *
     * @param winner The winner of the match.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void winnerInterface(String winner) throws RemoteException;

    /**
     * Notifies the user that the match finished.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void notifyCompleted() throws RemoteException;

    /**
     * Notifies the user that the match started.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void notifyGameStarted() throws RemoteException;

    /**
     * Notifies the user that another user crashed.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void errorCrash() throws RemoteException;

    /**
     * Ping the user to check if it's still connected.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void ping() throws RemoteException;

    /**
     * Notifies the user that the game is cancelled.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void subscriptionCancelled() throws RemoteException;

    /**
     * Notifies the user that the game stopped due to the lack of players.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void errorMissingPlayers() throws RemoteException;

    /**
     * Notifies the user that the game finished due to the lack of players.
     *
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void errorEndGameNoMorePlayers() throws RemoteException;

    /**
     * Sends to the user that is playing the game view containing the last error occurred.
     *
     * @param view The error game view.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void updateClientError(GameView view) throws RemoteException;
}
