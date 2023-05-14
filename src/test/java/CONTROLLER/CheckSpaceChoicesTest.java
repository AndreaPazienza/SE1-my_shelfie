package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.Color;
import MODEL.Game;
import MODEL.PersonalShelf;
import VIEW.SlotChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class CheckSpaceChoicesTest {
    private Game model = new Game(2);
    private GameController controller = new GameController(model);

    @BeforeEach
    void init() {
        model.getTable().refill(model.getBag());
        model.getTable().catchAfterRefill();
        String nick1 = "Gabry";
        String nick2 = "Rubin";
        model.signPlayer(nick1);
        model.signPlayer(nick2);
    }


    //Shelf inizializzata con riga in cima vuota, e tessera da selezionare solo 1
    @Test
    void CountInsert() throws NotEnoughSpaceChoiceException, RemoteException {
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if(i==0){
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREY);
                }
                else {
                model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREEN);
                }
            }
        }
        controller.checkSpaceChoices(1);
    }


    //Shelf inizializzata con riga 0 e 1 in cima vuota, e tessera da selezionare 2
    void CountInsert1() throws NotEnoughSpaceChoiceException, RemoteException {
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if(i==0 || i==1){
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREY);
                }
                else {
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREEN);
                }
            }
        }
        controller.checkSpaceChoices(2);
    }

    //Shelf inizializzata con riga 0 e 1 in cima vuota, e tessera da selezionare 3
    @Test
    void CountInsert2() throws NotEnoughSpaceChoiceException, RemoteException {
        model.getTable().refill(model.getBag());
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if(i==0 || i==1){
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREY);
                }
                else {
                    model.getPlayer()[0].getShelf().getSingleSlot(i, j).setColor(Color.GREEN);
                }
            }
        }
        controller.checkSpaceChoices(1);
    }




}










