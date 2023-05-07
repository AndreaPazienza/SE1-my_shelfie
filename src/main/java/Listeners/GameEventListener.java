package Listeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.GameView;

import java.rmi.RemoteException;

public interface GameEventListener {

    void addGameEventListener(GameEventListener listener);
    void gameStateChanged() throws RemoteException;
    void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException; //Sends the new GameView when the turn is finished
 //   public void notifyTurnIsOver(GameView view);
    void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;
    void notifyEndGame() throws RemoteException;
    void notifyGameFinished() throws RemoteException;
    void notifySkipTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException;

}
