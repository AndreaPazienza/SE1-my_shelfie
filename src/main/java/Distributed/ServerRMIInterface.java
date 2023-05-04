package Distributed;

import Distributed.rmi.Client;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerRMIInterface extends Remote {
    void register(ClientRMIInterface client) throws RemoteException;
    void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException; //throws NotAdjacentSlotsException, NotCatchableException;
    void updateServerReorder(ClientRMIInterface client, OrderChoice C) throws RemoteException;
    void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException;



}

