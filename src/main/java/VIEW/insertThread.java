package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.rmi.RemoteException;

/**
 * Class that manages the insert phase of the turn.
 */
public class insertThread extends Thread{

    /**
     * The value that manage the thread.
     */
    public volatile boolean valid = true;

    /**
     * The interface of the current player.
     */
    private GameInterface view;

    /**
     * Sets the bond to the interface of the current player.
     *
     * @param view The interface of the current player.
     */
    public insertThread(GameInterface view){
        this.view = view;
    }

    /**
     * Runs the thread while is valid and handles the errors occurred during the insertion.
     */
    @Override
    public synchronized void run() {
      while(valid) {
          view.playerInsert();
          System.err.println("--non ho più nulla da fare, insert--");
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
