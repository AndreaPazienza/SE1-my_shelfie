package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;


import java.rmi.RemoteException;

/**
 * Class that manages the selection and the order phase of the turn.
 */
public class turnThread extends Thread{

    /**
     * The value that manage the thread.
     */
    public volatile boolean valid = true;

    /**
     * The interface of the current player.
     */
    private GameInterface view;

    /**
     * Constructor for turnThread.
     *
     * @param view The interface of the current player.
     */
    public turnThread(GameInterface view){
        this.view = view;
    }

    /**
     * Runs the thread while is valid to manage the selection phase and the order phase of the turn.
     */
    @Override
    public synchronized void run() {
      while(valid) {
          try {
              view.playing();
          } catch (RemoteException | NotEnoughSpaceChoiceException | NotCatchableException |
                   NotAdjacentSlotsException | RuntimeException e) {
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
