package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;

import java.rmi.RemoteException;

public class insertThread extends Thread{

    public volatile boolean valid = true;
    private GraphicInterface view;

    public insertThread(GraphicInterface view){
        this.view = view;
    }

    @Override
    public synchronized void run() {
      while(valid) {
          view.playerInsert();
          System.err.println("--non ho più nulla da fare, insert--");
          stopThread();
         }
    }


    public void stopThread(){
        valid = false;
    }
}
