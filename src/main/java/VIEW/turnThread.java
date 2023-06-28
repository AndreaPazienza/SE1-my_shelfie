package VIEW;

import Distributed.rmi.Client;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import VIEW.GameInterface;

import java.rmi.RemoteException;

public class turnThread extends Thread{

    public volatile boolean valid = true;
    private GameInterface view;

    public turnThread(GameInterface view){
        this.view = view;
    }

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


    public void stopThread(){
        valid = false;
    }
}
