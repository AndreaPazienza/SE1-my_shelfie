package VIEW;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import Errors.SameNicknameException;
import Listeners.viewListeners;
import VIEW.GraphicObjects.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphicGameInterface implements Runnable, viewListeners {

    private final List<viewListeners> listeners = new ArrayList<>();
    public Scanner keyboard;

    public String firstRun(){
        NickChoice nickChoice = new NickChoice();
        final String[] nick = {null};
        nickChoice.getConfirm().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nick[0] = nickChoice.getNickField().getText();
                if(nick[0].isBlank()){
                    JDialog error = new JDialog(nickChoice.getWindow(), "ERRORE");
                    error.add(new JLabel("Il nickname inserito è nullo o formato solo da spazi! Sceglierne un altro!"));
                    firstRun();
                }
            }
        });
         return nick[0];
    }

    public void waitingRoom(){
        WaitingRoom wRoom = new WaitingRoom();

    }

    public void arrived(){

    }


























    @Override
    public void addviewEventListener(viewListeners listener) {

    }

    @Override
    public void notifySelectedCoordinates(SlotChoice[] SC) throws RemoteException, NotCatchableException, NotAdjacentSlotsException, NotEnoughSpaceChoiceException {

    }

    @Override
    public void notifyOrder(OrderChoice o) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void notifyInsert(int column) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void notifyOneMoreTime() throws SameNicknameException, RemoteException {

    }

    @Override
    public void notifyChoices(int number) throws RemoteException, NotEnoughSpaceChoiceException, NotAdjacentSlotsException, NotCatchableException {

    }

    @Override
    public void run() {

    }
}
