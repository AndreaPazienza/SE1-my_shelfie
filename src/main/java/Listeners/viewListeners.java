package Listeners;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.io.IOException;
import java.rmi.RemoteException;


public interface viewListeners {
    void addviewEventListener(viewListeners listener);
    void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException;
    void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;
    void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;
    void notifyOneMoreTime() throws SameNicknameException, IOException;
    void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

}
