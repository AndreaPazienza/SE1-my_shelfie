package Distributed;

import Distributed.rmi.Client;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the server-side application methods that can be invoked on a remote object using RMI.
 */
public interface ServerRMIInterface extends Remote {

    /**
     * Executes the registration of the client to the game.
     *
     * @param client The client to register.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws SameNicknameException If the client choices a nickname already in use.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    void register(ClientRMIInterface client) throws RemoteException, SameNicknameException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Updates the server with the slots selected by the input client.
     *
     * @param client The client that sent the update.
     * @param SC The slots selected by the client.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     */
    void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Updates the server with the slots selected by the input client.
     *
     * @param client The client that sent the update.
     * @param C The order chosen by the client.
     * @throws RemoteException If a communication error occurs during the remote operation.
     */
    void updateServerReorder(ClientRMIInterface client, OrderChoice C) throws RemoteException;

    /**
     * Updates the server with the shelf column to perform the insertion chosen by the input client.
     *
     * @param client The client that sent the update.
     * @param column The column of the shelf chosen by the client.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

    /**
     * Updates the server with the number of slots selected by the input client.
     *
     * @param client The client that sent the update.
     * @param number The number of slots selected by the client.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws RemoteException If a communication error occurs during the remote operation.
     * @throws NotAdjacentSlotsException If the user selects not adjacent slots.
     * @throws NotCatchableException If the user selects one (or more) not catchable slots.
     * @throws NotEnoughSpaceChoiceException If the user wants to select too much slots (according to the space in his shelf and the slot's configuration on the dashboard).
     */
    void updateServerChoices(ClientRMIInterface client, int number ) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;
}

