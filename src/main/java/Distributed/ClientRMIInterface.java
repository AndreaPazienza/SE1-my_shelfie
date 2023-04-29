package Distributed;

import MODEL.GameView;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote {

    void updateClient(GameView view) throws RemoteException;
}
