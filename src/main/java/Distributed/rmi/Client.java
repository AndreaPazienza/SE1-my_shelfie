package Distributed.rmi;

import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Listeners.GameEventListener;
import Listeners.viewListeners;

import MODEL.GameView;
import VIEW.GameInterface;
import VIEW.OrderChoice;
import VIEW.SlotChoice;



import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements viewListeners, ClientRMIInterface {
    public final String nickname;
    private final GameInterface view = new GameInterface();

    private ServerRMIInterface connectedTo = null;

    public Client(ServerRMIInterface server) throws RemoteException{
        super();
        nickname = view.firstRun();
        System.out.println("Inserimento nick corretto, provo a connettermi al server:");
        initialize(server);
        connectedTo = server;
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
//Manca il controllo che il server prende i dati dal client giusto.
    public void initialize(ServerRMIInterface server) throws RemoteException {
        try{
        //System.out.println(this.nickname);
        server.register(this);
        view.addviewEventListener(this);
        }catch(RemoteException e){
            System.err.println("ciao");
        }
    }

    public int numberOfPlayer() {
        return view.numberOfPlayers();
    }
    public void run(){view.run();}

    @Override
    public void addviewEventListener(viewListeners listener) {
        System.out.println("\t Connessione Client/Server stabilita \n");
    }


    //Mancano i metodi di ordering e di insertion,
    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException {
        connectedTo.updateServerSelection(this, SC);
    }

    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException {
        connectedTo.updateServerReorder(this, o);
    }

    @Override
    public void updateClient(GameView modelView) {
        view.displayDashboard(modelView.getTable());
        view.displayPersonalShelf(modelView.getShelf());
    }
}
