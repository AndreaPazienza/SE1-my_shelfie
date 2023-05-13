package Distributed;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.GameView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote {

    //Da implementare la PS solo alla fine
    void updateClientFirst(GameView view) throws RemoteException;
    void updateClientRound(GameView model) throws RemoteException;
    void updateClientPlaying(GameView model) throws RemoteException;
    String getNickname() throws RemoteException;
    int startGame() throws RemoteException;
    void onWait() throws RemoteException;
    void newPlayerAdded() throws RemoteException;
    void startTurn() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException;
    void endTurn() throws RemoteException;
    void winnerInterface(String winner) throws RemoteException;
    void notifyCompleted() throws RemoteException;
    void notifyGameStarted() throws RemoteException;
    void errorCrash() throws RemoteException;
    void ping() throws RemoteException;
    void subscriptionCancelled() throws RemoteException;
    void errorMissingPlayers() throws RemoteException;
    void errorEndGameNoMorePlayers() throws RemoteException;
    void updateClientError(GameView view) throws RemoteException;
}
