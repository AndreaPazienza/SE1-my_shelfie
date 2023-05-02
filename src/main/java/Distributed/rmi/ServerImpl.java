package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
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

    @Override
    public void register(ClientRMIInterface client) throws RemoteException {
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
                System.out.println("Non ho ancora un errore");
                if(model.isGameOn()) {
                    //orderInGame();
                    startGame();
                }
            }
         System.out.println("Il giocatore " + client.getNickname()+ "è stato correttamente iscritto ");
     }
    }


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

    @Override
    public void updateServerReorder(ClientRMIInterface client, OrderChoice C) {
        this.controller.checkOrder(C);
    }

    @Override
    public void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException {
        this.controller.checkInsert(column);
        //lavoro
    }

    @Override
    public void addGameEventListener(GameEventListener listener) {
        System.out.println("Client registrato con successo! ");
    }


    @Override
    public void gameStateChanged() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.updateClient(new GameView(model));
        }
    }


    @Override
    public void turnIsOver() throws RemoteException {
    }

    @Override
    public void notifyTurnIsOver(GameView view) {

    }

    public void newTurn() throws RemoteException {
        for(ClientRMIInterface client : logged){
            if(controller.getOnStage().equals(client.getNickname()))
                client.startTurn();
        }
    }
    public void endTurn() throws RemoteException {
        for(ClientRMIInterface client : logged){
            if(controller.getOnStage().equals(client.getNickname()))
                client.endTurn();
        }
    }
    public void subscription() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.newPlayerAdded();
        }
    }
    public void startGame() throws RemoteException {
        controller.startGame();
        newTurn();
    }


}


