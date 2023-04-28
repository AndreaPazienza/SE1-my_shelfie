package Distributed;

import Errors.SameNicknameException;
import VIEW.SlotChoice;

import java.rmi.Remote;
import java.rmi.server.ServerNotActiveException;

public interface ServerRMIInterface extends Remote {
    public void selectTails(SlotChoice m);
    public void reorderTails();
    public void insertTails(int column);
    public void register(Client client) throws SameNicknameException, ServerNotActiveException;
}
