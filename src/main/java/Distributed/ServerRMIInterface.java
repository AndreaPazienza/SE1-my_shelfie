package Distributed;

import Distributed.rmi.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface ServerRMIInterface extends Remote {
    void register(Client client) throws RemoteException;

}

