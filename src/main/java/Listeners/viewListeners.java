package Listeners;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;

/**
 * The interface that represents a listener of the events occurred in the view.
 */
public interface viewListeners {

    /**
     * Method that add a listener to the view.
     *
     * @param listener The listener to add to the list.
     */
    void addviewEventListener(viewListeners listener);

    /**
     * Method that notify to the listeners about the tiles selected by the player.
     *
     * @param SC Object that contains the coordinates of the selected tiles.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException;

    /**
     * Method that notify to the listeners about the order chosen by the player for the insert.
     *
     * @param o Object that contains the order of the tiles for the insert.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Method that notify to the listeners about the column chosen by the player for the insert.
     *
     * @param column The column where the player wants to put the tiles in.
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Method that notify to the listeners if a player want to try another time to enter the game with a different nickname (he tried before to enter the game with a nickname already taken)
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws SameNicknameException Exception thrown if the nickname chosen by the player.
     */
    void notifyOneMoreTime() throws SameNicknameException, RemoteException;

    /**
     * Method that notify to the listeners about the number of tiles selected by the player.
     *
     * @param number The number of tiles selected by the player.
     *
     * @throws RemoteException Exception thrown if there are problems with the client-server connection.
     * @throws NotCatchableException Exception thrown if the tiles selected by the player are not catchable.
     * @throws NotAdjacentSlotsException Exception thrown if the tiles selected by the player are not nearby.
     * @throws NotEnoughSpaceChoiceException Exception thrown if there is not enough space to insert all the selected tiles.
     */
    void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;
}
