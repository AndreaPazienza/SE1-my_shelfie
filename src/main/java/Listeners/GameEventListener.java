package Listeners;

import MODEL.GameView;

import java.rmi.RemoteException;

public interface GameEventListener {

    public void addGameEventListener(GameEventListener listener);
    public void gameStateChanged() throws RemoteException;
    public void turnIsOver(); //Manda le nuove game view a fine turno
    public void notifyTurnIsOver(GameView view);


}
