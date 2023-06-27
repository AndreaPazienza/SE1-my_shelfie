package Listeners;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.GameView;

import java.rmi.RemoteException;

public interface GameEventListener {

    void addGameEventListener(GameEventListener listener);
    void gameStateChanged() throws RemoteException;
    void turnIsOver() throws Exception; //Sends the new GameView when the turn is finished
 //   public void notifyTurnIsOver(GameView view);
    void readyToStart() throws Exception;
    void notifyEndGame() throws RemoteException;
    void notifyGameFinished() throws RemoteException;
    void notifySkipTurn() throws Exception;
    void notifyLastError() throws RemoteException;
}
