package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import MODEL.GameView;

import java.io.IOException;
import java.rmi.RemoteException;

public interface UserInterface {

    public String firstRun();
    public void waitingRoom(int enrolledPlayers, int nPlayers) throws IOException;
    public int numberOfPlayers();
    public void playing() throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException;
    public void playerMoveSelection() throws NotEnoughSpaceChoiceException, RemoteException, NotAdjacentSlotsException, NotCatchableException;
    public void startTurn();
    public void onWait();
    public void errorNotCatchable();
    public void errorOneNotCatchable();
    public void errorNotAdjacent();
    public void errorSpaceChoicesError();
    public void errorInsert();
    public void endgame();
    public void denyAcess();
    public void playerCrash();
    public void gameCancelled();
    public void waitingForPlayers();
    public void errorNick(String message) throws SameNicknameException, RemoteException;
}
