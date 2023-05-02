package Distributed;

import MODEL.GameView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote {

    void updateClient(GameView view) throws RemoteException;

    String getNickname() throws RemoteException;

    int startGame() throws RemoteException;
    void onWait() throws RemoteException;

    void newPlayerAdded() throws RemoteException;
    void startTurn() throws  RemoteException;
    void endTurn() throws RemoteException;

}
