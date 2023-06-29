package Listeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.rmi.RemoteException;

public interface GameEventListener {

    /**
     * Adds a listener to the list of GameEventListener.
     *
     * @param listener The listener to add.
     */
    void addGameEventListener(GameEventListener listener);

    /**
     * Notifies the listeners the changing of the state of the game.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    void gameStateChanged() throws RemoteException;

    /**
     * Notifies the listeners the transition to the next player during the game.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException; //Sends the new GameView when the turn is finished
 //   public void notifyTurnIsOver(GameView view);

    /**
     * Notifies the listeners the start of the first player's turn.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Notifies the listeners the beginning of the last turn of the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    void notifyEndGame() throws RemoteException;

    /**
     * Notifies the listeners the end of the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    void notifyGameFinished() throws RemoteException;

    /**
     * Notifies the listeners the skip of the turn because in turn the player is disconnected.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws NotAdjacentSlotsException  If the user selects not adjacent slots.
     * @throws NotCatchableException  If the user selects one (or more) not catchable slots.
     */
    void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Notifies the listeners the last error occurred to the match.
     *
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    void notifyLastError() throws RemoteException;

    /**
     * Notifies the listeners the end of the current player's turn due to the crash of the player
     *
     * @throws NotEnoughSpaceChoiceException If a player wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     * @throws RemoteException If an error occurs while executing the remote operation.
     */
    void notifyForcedTurnEnding() throws NotEnoughSpaceChoiceException, RemoteException;
}
