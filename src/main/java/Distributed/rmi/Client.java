package Distributed.rmi;

import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Listeners.GameEventListener;
import Listeners.viewListeners;

import MODEL.GameView;
import VIEW.GameInterface;
import VIEW.SlotChoice;


import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface {
    public final String nickname;
    private final GameInterface view = new GameInterface();
    public Client(ServerRMIInterface server) throws RemoteException{
        super();
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");

        initialize(server);
    }

    public Client(ServerRMIInterface server, int port) throws RemoteException {
        super(port);
        nickname = view.firstRun();
        initialize(server);
    }

    public Client(ServerRMIInterface server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        nickname = view.firstRun();
        initialize(server);
    }
//Manca il controllo che il server prende i dati dal client giusto.
    public void initialize(ServerRMIInterface server) throws RemoteException {
        try {
            server.register(this);
            view.addviewEventListener(this);
        }catch (RemoteException e){
            System.err.println("Ciao");
        }
    }

    public int numberOfPlayer() {
        return view.numberOfPlayers();
    }
    public void run(){view.run();}

//Questa communicaizone non avviene in questo modo, bisogna esporre i metodi del clientRMIInterface
//In modo che sia il server ad andare a chiamre questi metodi nella propria Update quando il model cambia,
  /*  @Override
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
    }*/
//Inizio commento riga 56.

    @Override
    public void addviewEventListener(viewListeners listener) {
        System.out.println("\t Connessione Client/Server stabilita \n");
    }


    //Mancano i metodi di ordering e di insertion,
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) {
        //Va chiamato il metodo esposto del server per chiamare selezione
    }

    @Override
    public void selecteCoordinates() {
        //Conferma la ricezione delle coordinate
    }


    @Override
    public void updateClient(GameView modelView) {
        view.displayDashboard(modelView.getTable());
        view.displayPersonalShelf(modelView.getShelf());
    }
}
