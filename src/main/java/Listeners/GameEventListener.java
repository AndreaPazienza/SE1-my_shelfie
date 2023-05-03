package Listeners;

import MODEL.GameView;

import java.rmi.RemoteException;

public interface GameEventListener {

    void addGameEventListener(GameEventListener listener);
    void gameStateChanged() throws RemoteException;
    void turnIsOver() throws RemoteException; //Manda le nuove game view a fine turno
 //   public void notifyTurnIsOver(GameView view);
    void readyToStart() throws RemoteException;
    void notifyEndGame() throws RemoteException;
    void notifyGameFinished() throws RemoteException;
}
