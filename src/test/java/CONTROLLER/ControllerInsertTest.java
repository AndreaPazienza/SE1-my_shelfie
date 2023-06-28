package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import Errors.NotEnoughSpaceChoiceException;
import MODEL.Game;
import MODEL.PersonalShelf;
import VIEW.SlotChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static MODEL.GameError.SPACE_CHOICES_ERROR;
import static org.junit.jupiter.api.Assertions.*;

class ControllerInsertTest {
    private Game model = new Game(2);
    private GameController controller = new GameController(model);

    @BeforeEach
    void init(){
        model.getTable().refill(model.getBag());
        model.getTable().catchAfterRefill();
        String nick1 = "Gabry";
        String nick2 = "Rubin";
        model.signPlayer(nick1);
        model.signPlayer(nick2);
    }

    @Test
    void InsertInEmptyShelfOneTile() throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[1];
        slotChoice[0] = new SlotChoice(4,1);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        controller.checkSelect(slotChoice);
        controller.checkInsert(0);
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(5,0).getColor());
    }

    @Test
    void InsertInEmptyColumnTwoTiles() throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        System.out.println(model.getTable().getSingleSlot(5,1).getColor());
        controller.checkSelect(slotChoice);
        controller.checkInsert(0);
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(5,0).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(4,0).getColor());
    }

    @Test
    void InsertInEmptyColumnThreeTiles() throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        controller.checkInsert(0);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        System.out.println(model.getTable().getSingleSlot(3,2).getColor());
        System.out.println(model.getTable().getSingleSlot(4,2).getColor());
        System.out.println(model.getTable().getSingleSlot(5,2).getColor());
        controller.checkSelect(slotChoice2);
        controller.checkInsert(1);
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(5,1).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(4,1).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(3,1).getColor());
    }

    @Test
    void InsertInAlmostFullColumnThreeTiles() throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        System.out.println(model.getTable().getSingleSlot(5,1).getColor());
        controller.checkSelect(slotChoice);
        controller.checkInsert(0);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        System.out.println(model.getTable().getSingleSlot(3,2).getColor());
        System.out.println(model.getTable().getSingleSlot(4,2).getColor());
        System.out.println(model.getTable().getSingleSlot(5,2).getColor()+"\n");
        controller.checkSelect(slotChoice2);
        controller.checkInsert(0);
        /*for(int i = PersonalShelf.N_ROWS-1; i >= 0; i--){
            System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(4,0).getColor());
        }*/
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(5,0).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(4,0).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(3,0).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(2,0).getColor());
        System.out.println(model.getPlayer()[model.getPlayerInGame()].getShelf().getSingleSlot(1,0).getColor());

    }

    @Test
    void InsertInFullColumn() throws NotAdjacentSlotsException, NotCatchableException, NotEnoughSpaceChoiceException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        controller.checkInsert(0);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        controller.checkInsert(0);
        SlotChoice[] slotChoice3 = new SlotChoice[2];
        slotChoice3[0] = new SlotChoice(1,3);
        slotChoice3[1] = new SlotChoice(2,3);
        controller.checkSelect(slotChoice3);
        try {
            controller.checkInsert(0);
        } catch(NotEnoughSpaceChoiceException e){
          System.out.println("La colonna selezionata non ha abbastanza spazio!");
          if (model.getLastError().equals(SPACE_CHOICES_ERROR)){
              System.out.println("Ok errore captato");
          }
        }
    }

}