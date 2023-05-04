package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.GameEventListener;
import MODEL.Game;
import MODEL.GameView;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class ServerImpl extends UnicastRemoteObject implements ServerRMIInterface, GameEventListener {

    private GameController controller;
    private Game model;

    private ArrayList<ClientRMIInterface> logged = new ArrayList<>();
    private boolean firstPlayerEnrolled = false;

    public ServerImpl() throws RemoteException {
        super();
    }

    public ServerImpl(int port) throws RemoteException {
        super(port);
    }

    public ServerImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    //Metodo remoto usato dal client per registrarsi al model
    @Override
    public void register(ClientRMIInterface client) throws RemoteException, SameNicknameException {
        System.out.println("Ricevuto un tentativo di connessione");
        if (!firstPlayerEnrolled) {
            model = new Game(client.startGame());
            controller = new GameController(model);
            this.model.addGameEventListener(this);
            model.signPlayer(client.getNickname());
            this.logged.add(client);
            firstPlayerEnrolled = true;
        } else {
            if (controller.checkNick(client.getNickname())) {
                model.signPlayer(client.getNickname());
                this.logged.add(client);
                System.out.println("Il giocatore " + client.getNickname()+ " è stato correttamente iscritto ");
                subscription();
                if(model.isGameOn()) {
                    startGame();
                }
            }else{
                throw new SameNicknameException("Il nickname è già preso!! \n");
            }
        }
    }

    //metodo remoto: usato dal client quando un utente ha selezionato delle coordinate
    @Override
    public void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC){ //throws NotAdjacentSlotsException, NotCatchableException {
        try{
            this.controller.checkSelect(SC);
            System.out.println("La selezione è andanta a buon fine ");
        }catch(NotCatchableException e){
            System.err.println("La tessera selezionata non è prendibile");
            //Notify Errore al client
        }catch(NotAdjacentSlotsException e ){
            System.err.println("Le coordinate inserite non sono adiacenti");
            //Notify Errore al client
        }

    }
    //metodo remoto: usato dal client quando un utente ha scelto se riordinare le tessere
    @Override
    public void updateServerReorder(ClientRMIInterface client, OrderChoice C) {
        this.controller.checkOrder(C);
    }
    //metodo remoto: usato dal client quando un utente ha la colonna dove inserire
    @Override
    public void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException {
        this.controller.checkInsert(column);
        System.out.println("Inserimento corretto \n Passo al prossimo giocatore \n");
        controller.turnUpdate();
    }
    //Viene aggiunto il Listener al gioco
    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Client registrato con successo! ");
    }

    //Notifica al client l'avvenuta creazione (quindi inizio) del gioco, mandando la situazione della board
    @Override
    public void gameStateChanged() throws RemoteException {
        int i=0;
        for(ClientRMIInterface client : logged){
            client.updateClientFirst(new GameView(i, model));
            i++;
        }
    }

    //Notifica al client la nuova view dopo che un client ha finito il proprio turno, con la PersonalShelf
    @Override
    public void turnIsOver() throws RemoteException {
        for (ClientRMIInterface client : logged) {
            if (controller.getOnStage().equals(client.getNickname())) {
                client.updateClientPlaying(new GameView(model));
                client.endTurn();
            }else{
                client.updateClientRound(new GameView(model));
            }
        }
        this.newTurn();
    }

    //Notifica al client che abbiamo iniziato la partita
    @Override
    public void readyToStart() throws RemoteException {

        for(ClientRMIInterface client : logged){
            if(controller.getOnStage().equals(client.getNickname())) {
                client.startTurn();
            }else{
                client.onWait();
            }
        }
    }

    @Override
    public void notifyEndGame() throws RemoteException {
        controller.completeShelf();
        notifyCompleted();
    }

    @Override
    public void notifyGameFinished() throws RemoteException {
        winnerInterface(controller.endGame());
    }


    //Rispetto a tutti i client iscritti manda la notifica di "via libera" al client di turno
    public void newTurn() throws RemoteException {
        for (ClientRMIInterface client : logged) {
            if (controller.getOnStage().equals(client.getNickname())) {
                client.startTurn();
            } else {
                client.onWait();
            }
        }
    }


    //----SU
    //Notifica di aver aggiunto un nuovo player alla partita
    public void subscription() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.newPlayerAdded();
        }
    }
    public void notifyCompleted() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.notifyCompleted();
        }
    }
    public void winnerInterface(String s) throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.winnerInterface(s);
        }
    }
    //Una volta giunto al numero giusto di giocatori fa partire la partita
    public void startGame() throws RemoteException {
        controller.startGame();
    }


    //---------FINO A QUI


}


