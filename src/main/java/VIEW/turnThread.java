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
    private Client user;

    public turnThread(GameInterface view, Client user){
        this.view = view;
        this.user=user;
    }

    @Override
    public synchronized void run() {
        System.out.println("Inizio del thread");
      while(valid) {
          try {
              view.playing();
          } catch (RemoteException | NotEnoughSpaceChoiceException | NotCatchableException |
                   NotAdjacentSlotsException e) {
              System.err.println(e);
              System.err.println("Sto chiudendo il thread del client per un errore ");
              stopThread();
           }
          System.err.println("--non ho più nulla da fare--");
          stopThread();
         }
      }
      public synchronized void runInsert(){
        try{
          view.playerInsert();
          System.out.println("Le tessere sono state inserite");
      } catch (RemoteException | NotEnoughSpaceChoiceException | NotCatchableException |
             NotAdjacentSlotsException e) {
        System.err.println("Sto chiudendo il thread del client ");
        stopThread();
        }

      }

    public void stopThread(){
        valid = false;
    }
}
