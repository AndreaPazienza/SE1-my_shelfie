package Distributed;

import CONTROLLER.GameController;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import MODEL.Game;
import MODEL.GameState;
import MODEL.GameView;
import VIEW.OrderChoice;
import VIEW.SlotChoice;

import java.rmi.RemoteException;
import java.rmi.server.*;

public class Server extends UnicastRemoteObject implements ServerRMIInterface {

    private GameController controller;
    private Game model;
    protected boolean firstPlayerEnrolled = false;

    protected Server() throws RemoteException {
    }

    protected Server(int port) throws RemoteException {
        super(port);
    }

    protected Server(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    public void register(Client client) throws SameNicknameException, ServerNotActiveException {

        if(client.nickname.isEmpty() ){
            throw new IllegalArgumentException("The name is not initializated, cannot recongnize!");
        }

        if(!firstPlayerEnrolled){
            model = new Game(client.numberOfPlayer());
            controller = new GameController(model);

            model.signPlayer(client.nickname);
            this.model.addGameEventListener(client);

            System.out.println("The player " + client.nickname + "has been enrolled, IP: " + RemoteServer.getClientHost());

            firstPlayerEnrolled=true;
        }

        else{

            if(controller.checkNick(client.nickname)) {
                model.signPlayer(client.nickname);
                this.model.addGameEventListener(client);
                /*this.model.addObserver((o, arg) -> {
                                                try{
                                                  client.updateView(new GameView(model), model.getCurrentState());}
                                                       catch (RemoteException e){
                                                       System.err.println("Unable to reach the client.");
                                                     }
                                                });*/

                System.out.println("The player " + client.nickname + "has been enrolled IP: " + RemoteServer.getClientHost());
                controller.startGame();
            }
            else{
                throw new SameNicknameException("The name is already taken! ");
            }
        }
    }

    /*  public void updateServerView(GameView view, GameState state) throws RemoteException {
          client.updateView(new GameView(model), state);
         }   */

    @Override
    public void selectTails(SlotChoice m) {

        try{
            this.controller.checkSelect(m);
            System.out.println("La selezione è andanta a buon fine ");
            updateServerView();
        }catch(NotCatchableException e){
            System.err.println("La tessera selezionata non è prendibile");
        }catch(NotAdjacentSlotsException e ){
            System.err.println("Le coordinate inserite non sono adiacenti");
        }
    }

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


}
