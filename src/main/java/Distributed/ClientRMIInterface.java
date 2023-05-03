package Distributed;

import MODEL.GameView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote {

    //Da implementare la PS solo alla fine
    void updateClientFirst(GameView view) throws RemoteException;
    void updateClientRound(GameView model) throws RemoteException;
    String getNickname() throws RemoteException;

    int startGame() throws RemoteException;
    void onWait() throws RemoteException;

    void newPlayerAdded() throws RemoteException;
    void startTurn() throws  RemoteException;
    void endTurn() throws RemoteException;
    void winnerInterface(String winner) throws RemoteException;
    void notifyCompleted() throws RemoteException;

}
