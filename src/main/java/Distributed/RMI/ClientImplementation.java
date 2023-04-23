package Distributed.RMI;

import Distributed.Client;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ClientImplementation extends UnicastRemoteObject implements Client {
    protected ClientImplementation() throws RemoteException {
    }

    protected ClientImplementation(int port) throws RemoteException {
        super(port);
    }

    protected ClientImplementation(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void update() {
        
    }
}
