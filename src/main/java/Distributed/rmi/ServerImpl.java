package Distributed.rmi;

import CONTROLLER.GameController;
import Distributed.ClientRMIInterface;
import Distributed.ServerRMIInterface;
import Distributed.crashThread;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.GameEventListener;
import MODEL.Game;
import MODEL.GameState;
import MODEL.GameView;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.Remote;
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
    public void register(ClientRMIInterface client) throws RemoteException, SameNicknameException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        ping();
           System.out.println("Ricevuto un tentativo di connessione");
           if (!firstPlayerEnrolled) {
               model = new Game(client.startGame());
               controller = new GameController(model);
               this.model.addGameEventListener(this);
               model.signPlayer(client.getNickname());
               this.logged.add(client);
               firstPlayerEnrolled = true;
           } else {
               if(model.getCurrentState().equals(GameState.LOGIN)) {
                   if (controller.checkNick(client.getNickname())) {
                       model.signPlayer(client.getNickname());
                       this.logged.add(client);
                       System.out.println("Il giocatore " + client.getNickname() + " è stato correttamente iscritto ");
                       subscription();
                       if (model.isGameOn()) {
                           startGame();
                       }
                   } else {
                       throw new SameNicknameException("Il nickname è già preso!! \n");
                   }
               }else{
                   client.notifyGameStarted();
               }
           }
    }

    //metodo remoto: usato dal client quando un utente ha selezionato delle coordinate
    @Override
    public void updateServerSelection(ClientRMIInterface client, SlotChoice[] SC) throws RemoteException, NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException { //throws NotAdjacentSlotsException, NotCatchableException {
        try{
            this.controller.checkSelect(SC);
            System.out.println("La selezione è andata a buon fine ");
        }catch(NotCatchableException e){
            for(ClientRMIInterface clients : logged){
                if(controller.getOnStage().equals(clients.getNickname())){
                    clients.errorNotCatchable();
                } else {
                    String message = model.playerOnStage().nickname+" ha fatto un errore nella selezione! Sta rifacendo la sua scelta!\n)";
                    clients.errorNotify(message);
                }
            }
        }catch(NotAdjacentSlotsException e ){
            for(ClientRMIInterface clients : logged){
                if(controller.getOnStage().equals(client.getNickname())){
                    clients.errorNotAdjacent();
                } else {
                    String message = model.playerOnStage().nickname+" ha fatto un errore nella selezione! Sta rifacendo la sua scelta!\n)";
                    clients.errorNotify(message);
                }
            }
        }

    }
    //metodo remoto: usato dal client quando un utente ha scelto se riordinare le tessere
    @Override
    public void updateServerReorder(ClientRMIInterface client, OrderChoice C) {
        this.controller.checkOrder(C);
    }
    //metodo remoto: usato dal client quando un utente ha la colonna dove inserire
    @Override
    public void updateServerInsert(ClientRMIInterface client, int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkInsert(column);
            System.out.println("Inserimento corretto ");
        } catch (NotEnoughSpaceChoiceException e){
            for(ClientRMIInterface clients : logged){
                if(controller.getOnStage().equals(clients.getNickname())){
                    clients.errorNotEnoughSpace();
                } else {
                    String message = model.playerOnStage().nickname+" ha fatto un errore nell'inserimento! Sta rifacendo la sua scelta!\n)";
                    clients.errorNotifyInsert(message);
                }
            }
        }
        System.out.println("Inserimento corretto \n Passo al prossimo giocatore \n");
        turnUpdate();
    }

    @Override
    public void updateServerChoices(ClientRMIInterface client, int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try {
            this.controller.checkSpaceChoices(number);
        } catch (NotEnoughSpaceChoiceException e) {
            for (ClientRMIInterface clients : logged) {
                if (controller.getOnStage().equals(clients.getNickname())) {
                    clients.errorChoices(e.getMessage());
                } else {
                    String message = model.playerOnStage().nickname + " ha fatto un errore nella selezione! Sta rifacendo la sua scelta!\n)";
                    clients.errorNotifyInsert(message);
                }
            }
        }
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
    public void turnIsOver() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
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
    public void readyToStart() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
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
    public void newTurn() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
    try{ping();}catch (RemoteException e){ System.out.println("Errore qui NT"); }
        for (ClientRMIInterface client : logged) {
            if (controller.getOnStage().equals(client.getNickname())) {
                client.startTurn();
            } else {
                client.onWait();
            }
        }
    }

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
    public void startGame() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
      controller.startGame();}


    public void turnUpdate() throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {
        try{ ping();
        }catch (RemoteException e){
            model.forcedGameOver();
            notifyForcedCrash(); }
        controller.turnUpdate();}

    public void ping() throws RemoteException{
        for(ClientRMIInterface client : logged){
            try{client.ping();}
            catch (RemoteException e){
                System.err.println("Il client down è: " +client.getNickname());
            }
        }
    }

    public void notifyForcedCrash() throws RemoteException {
        for(ClientRMIInterface client : logged){
            crashThread notifyUser = new crashThread(client);
            notifyUser.run();
        }
    }


}






