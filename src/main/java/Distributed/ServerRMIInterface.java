package Distributed;

import Distributed.rmi.Client;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerRMIInterface extends Remote {
    void register(Client client) throws RemoteException;

    void updateServerSelection(Client client, SlotChoice[] SC) throws RemoteException; //throws NotAdjacentSlotsException, NotCatchableException;
    void updateServerReorder(Client client, OrderChoice C) throws RemoteException;


}

