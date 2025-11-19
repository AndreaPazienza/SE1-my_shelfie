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
     * Constructor for insertThread.
     *
     * @param view The interface of the current player.
     */
    public insertThread(GameInterface view){
        this.view = view;
    }

    /**
     * Runs the thread while is valid to manage the insert phase of the turn.
     */
    @Override
    public synchronized void run() {
      while(valid) {
          try {
              view.playerInsert();
          } catch (RemoteException | NotEnoughSpaceChoiceException | NotCatchableException |
                   NotAdjacentSlotsException e) {
              System.err.println(e.getMessage());
              stopThread();
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
