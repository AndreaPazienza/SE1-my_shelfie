package Distributed.rmi;

import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotEnoughSpaceChoiceException;
import Listeners.viewListeners;

import MODEL.GameView;
import VIEW.GameInterface;
import VIEW.OrderChoice;
import VIEW.SlotChoice;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface, Serializable {
    public final String nickname;
    private final GameInterface view = new GameInterface();
    private final ServerRMIInterface connectedTo;

    public Client(ServerRMIInterface server) throws RemoteException{
        super();
        connectedTo = server;
        nickname = view.firstRun();
        view.addviewEventListener(this);
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
    }

    public Client(ServerRMIInterface server, int port) throws RemoteException {
        super(port);
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }

    public Client(ServerRMIInterface server, int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
    }


    public void initialize(ServerRMIInterface server) throws RemoteException {
        try{
        server.register(this);
        }catch(RemoteException e){
            System.err.println(e.getCause());
        }
    }


    public void run() {
      view.run();
    }

    //Observer:
    @Override
    public void addviewEventListener(viewListeners listener) {
        System.out.println("\t Connessione Client/Server stabilita \n");
    }


    //Observer: quando dalla view vengono prese coordinate
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException {
        System.out.println("Invio delle coordinate in corso; \n");
        connectedTo.updateServerSelection(this, SC);
    }
    //Observer: quando dalla view viene scelto l'ordinamento
    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException {
        connectedTo.updateServerReorder(this, o);
    }
    //Observer: quando dalla view viene scelta la colonna di inserimento
    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException {
        connectedTo.updateServerInsert(this, column);
    }
    //Quando il server ha un nuovo update viene mandato e mostrato dal client
    @Override
    public void updateClientFirst(GameView modelView) {
        view.displayDashboard(modelView.getTable());
        view.displayPersonalShelf(modelView.getShelf());
        view.onWait();
    }
    //Manda al giocaente la situazione attuale e la propria personal shelf
    @Override
    public void updateClientPlaying(GameView modelView) {
        view.displayDashboard(modelView.getTable());
        view.displayPersonalShelf(modelView.getShelf());
    }
    //Manda a tutti i client la nuova board
    @Override
    public void updateClientRound(GameView model) throws RemoteException {
        view.displayDashboard(model.getTable());
    }

    //Metodo remoto: passaggio del nickname al server
    @Override
    public String getNickname() throws RemoteException {
        return this.nickname;
    }
    //Metodo remoto: usato per notificare al primo client di che manca il numero di giocatori
    @Override
    public int startGame() throws RemoteException {
        return view.numberOfPlayers();
    }
    //Metodo remoto: quando un nuovo cient si registra viene notificato a chi è già loggato
    @Override
    public void newPlayerAdded() throws RemoteException {
        view.arrived();
    }
    //Metodo remoto: per segnalare al client che deve stare ancora in attesa del proprio turno
    public void onWait() throws RemoteException{
        view.onWait();
    }
    //Metodo remoto: inizio del proprio turno
    public void startTurn() throws RemoteException {
        view.startTurn();
        view.playing();
    }
    //Metodo remoto: finel del turno
    public void endTurn(){
        view.onWait();
    }

    @Override
    public void winnerInterface(String winner ) throws RemoteException {
        view.displayWin(winner);
    }

    @Override
    public void notifyCompleted() throws RemoteException {
        view.notifyAlmostOver();

    }
}
