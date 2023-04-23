package Distributed;

import java.rmi.Remote;

public interface Server extends Remote {

    public void register(Client client);

    public void update();
}
