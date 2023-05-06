package VIEW;

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
    public void run() {
      while(valid){
           try {
               view.playing();
           } catch (RemoteException e) {
               view.endgame();
           } catch (NotAdjacentSlotsException e) {
               throw new RuntimeException(e);
           } catch (NotCatchableException e) {
               throw new RuntimeException(e);
           } catch (NotEnoughSpaceChoiceException e) {
               throw new RuntimeException(e);
           }
           stopThread();
    }
    }

    public void stopThread(){
        valid = false;
    }
}
