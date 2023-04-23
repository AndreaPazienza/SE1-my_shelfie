package Distributed.RMI;

import Distributed.Client;
import Distributed.Server;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ServerImplementation extends UnicastRemoteObject implements Server {
    protected ServerImplementation() throws RemoteException {
    }

    protected ServerImplementation(int port) throws RemoteException {
        super(port);
    }

    protected ServerImplementation(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public void register(Client client) {

    }

    @Override
    public void update() {

    }
}
