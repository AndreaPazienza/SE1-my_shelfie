package Listeners;

import Errors.NotEnoughSpaceChoiceException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;


public interface viewListeners {
    void addviewEventListener(viewListeners listener);
    void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException;
    void notifyOrder(OrderChoice o) throws RemoteException;
    void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException;

}
