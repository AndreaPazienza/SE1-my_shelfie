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
              System.err.println("Sto chiudendo il thread del client per un errore ");
              stopThread();
           }
          System.err.println("--non ho più nulla da fare, insert--");
          stopThread();
         }
      }


    public void stopThread(){
        valid = false;
    }
}
