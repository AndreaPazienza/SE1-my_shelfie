package Distributed;

import MODEL.GameEventListener;
import MODEL.GameState;
import Errors.SameNicknameException;
import MODEL.GameView;
import VIEW.GameInterface;
import VIEW.SlotChoice;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements GameEventListener {
    public String nickname;
    private GameInterface view;
    protected Client(GameInterface view) throws RemoteException {
        super();
        this.view = view;
    }

    protected Client(int port, GameInterface view) throws RemoteException {
        super(port);
        this.view = view;
    }

    protected Client(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public void firstRun(){
        this.nickname = view.firstRun();
    }

    public void updateView( GameView game, GameState state ) throws RemoteException{
        view.update(game, state);
    }

    public int numberOfPlayer() {
        return view.numberOfPlayers();
    }

    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Connesso al server");
    }

    @Override
    public void gameStateChanged() {
        System.out.println("Un nuovo giocatore si è unito alla partita");
    }

    @Override
    public void turnIsOver() {
        System.out.println("Il turno è finito");
    }

    @Override
    public void notifyTurnIsOver(GameView viewgame) {
        view.displayDashboard(viewgame.getTable());
        view.displayPersonalShelf(viewgame.getShelf());
    }
}
