package VIEW;

import Distributed.rmi.Client;
import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.GameView;
import VIEW.GameInterface;

import java.rmi.RemoteException;

public class turnThread extends Thread{

    public volatile boolean valid = true;
    private GraphicInterface view;

    public turnThread(GraphicInterface view){
        this.view = view;
    }

    @Override
    public synchronized void run() {
      while(valid) {
          try {
              view.playing();
          } catch (Exception e) {
              stopThread();
           }
          stopThread();
         }
    }


    public void stopThread(){
        valid = false;
    }
}
