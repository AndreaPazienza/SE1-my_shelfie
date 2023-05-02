package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
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

            model = new Game(2);
            controller = new GameController(model);
            this.logged.add(client);
            this.model.addGameEventListener(this);


            model.signPlayer(client.getNickname());
             System.out.println("Il giocatore " + client.getNickname()+ "è stato correttamente iscritto ");
            firstPlayerEnrolled = true;
         } else {
            if (controller.checkNick(client.getNickname())) {
                model.signPlayer(client.getNickname());
                this.logged.add(client);
                System.out.println("Il giocatore " + client.getNickname()+ " è stato correttamente iscritto ");
                subscription();
                System.out.println("Non ho ancora un errore");
                if(model.isGameOn()) {
                    startGame();
                }
            }
        }
    }

    public void startGame() throws RemoteException {
        controller.startGame();
        for(ClientRMIInterface client : logged){
            if(controller.getOnStage().equals(client.getNickname()))
                client.startTurn();
        }
    }

    @Override
    public void updateServerSelection(Client client, SlotChoice[] SC){ //throws NotAdjacentSlotsException, NotCatchableException {
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
    public void updateServerReorder(Client client, OrderChoice C) {
        this.controller.checkOrder(C);
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
    public void subscription() throws RemoteException {
        for(ClientRMIInterface client : logged){
            client.newPlayerAdded();
        }
    }

    @Override
    public void turnIsOver() {

    }

    @Override
    public void notifyTurnIsOver(GameView view) {

    }
}

    /*  public void updateServerView(GameView view, GameState state) throws RemoteException {
          client.updateView(new GameView(model), state);
         }   */

/*
    @Override
    public void reorderTails() {

        if(controller.selectedSlot.length()==2){
             this.controller.checkReorder(controller.selectedSlot);}

        else if(controller.selectedSlot.length()==3){
            this.controller.checkReorder(controller.selectedSlot, OrderChoice c);
          }
    }

    @Override
    public void insertTails(int column) {
        try{
         this.controller.checkInsert(controller.selectedSlot, column);
        } catch(NotEnoughSpaceChoiceException e) {

        }

    }

*/
//}
