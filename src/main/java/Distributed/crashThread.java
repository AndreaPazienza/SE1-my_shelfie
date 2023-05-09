package Distributed;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import VIEW.GameInterface;

import java.rmi.RemoteException;

public class crashThread extends Thread{
    public volatile boolean valid = true;
    private ClientRMIInterface client;

    public crashThread(ClientRMIInterface client){
        this.client = client;
    }

    @Override
    public void run() {
        while (valid) {
            try {
                client.errorCrash();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            System.err.println(" Chiudo il thread ");
            stopThread();
        }
    }

    public void stopThread(){
        valid = false;
    }

}
