package Distributed;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import VIEW.GameInterface;

import java.rmi.RemoteException;

public class crashPreGame extends Thread{
    public volatile boolean valid = true;
    private ClientRMIInterface client;
    public crashPreGame(ClientRMIInterface client){
        this.client = client;
    }

    @Override
    public void run() {
        while (valid) {
            try {
                client.subscriptionCancelled();
            } catch (RemoteException e) {
                System.err.println("Client disconnesso ");
            }
            stopThread();
        }
    }

    public void stopThread(){
        valid = false;
    }

}
