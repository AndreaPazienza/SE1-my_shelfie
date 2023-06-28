package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.rmi.RemoteException;

public class insertThread extends Thread{

    public volatile boolean valid = true;
    private GameInterface view;

    public insertThread(GameInterface view){
        this.view = view;
    }

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


    public void stopThread(){
        valid = false;
    }
}
