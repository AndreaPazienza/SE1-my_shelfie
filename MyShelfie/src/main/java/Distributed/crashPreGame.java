package Distributed;


import java.rmi.RemoteException;

/**
 * Class that manages that notifies the logged players that a client crashed in the pre game phase.
 */
public class crashPreGame extends Thread{

    /**
     * The value that manage the thread.
     */
    public volatile boolean valid = true;

    /**
     * The client that have to be notified.
     */
    private ClientRMIInterface client;

    /**
     * Sets the bond with the client that have to be notified.
     *
     * @param client The client that have to be notified.
     */
    public crashPreGame(ClientRMIInterface client){
        this.client = client;
    }

    /**
     * Runs the thread while is valid to notify the disconnection.
     */
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

    /**
     * Sets value on false to stop the thread.
     */
    public void stopThread(){
        valid = false;
    }

}
