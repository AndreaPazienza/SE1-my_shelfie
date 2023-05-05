package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import MODEL.Game;
import VIEW.SlotChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerSelectionTest {

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

    /*
    @Test
    void selectOneTileCorrect() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[1];
        slotChoice[0] = new SlotChoice(4,1);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        controller.checkSelect(slotChoice);
        System.out.println(controller.selectedSlots[0].getColor());
    }

    @Test
    void selectOneNotCatchable(){
        SlotChoice[] slotChoice = new SlotChoice[1];
        slotChoice[0] = new SlotChoice(4,5);
        System.out.println(model.getTable().getSingleSlot(4,5).getColor());
        try {
            controller.checkSelect(slotChoice);
            System.out.println(controller.selectedSlots[0].getColor());
        }catch(NotCatchableException | NotAdjacentSlotsException e){
            System.out.println("La tessera presa non è catchable");
        }
    }

    @Test
    void selectTwoCatchable() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        System.out.println(model.getTable().getSingleSlot(5,1).getColor());
        controller.checkSelect(slotChoice);
        System.out.println(controller.selectedSlots[0].getColor());
        System.out.println(controller.selectedSlots[1].getColor());
    }

    @Test
    void selectTwoNotCatchable() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(2,5);
        slotChoice[1] = new SlotChoice(3,5);
        System.out.println(model.getTable().getSingleSlot(2,5).getColor());
        System.out.println(model.getTable().getSingleSlot(3,5).getColor());
        try {
            controller.checkSelect(slotChoice);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }
    }

    @Test
    void selectTwoNotAdjacent() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(2,5);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        System.out.println(model.getTable().getSingleSlot(2,5).getColor());
        try {
            controller.checkSelect(slotChoice);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }
    }

    @Test
    void selectTwoNotCorrectAtAll() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(3,5);
        System.out.println(model.getTable().getSingleSlot(4,1).getColor());
        System.out.println(model.getTable().getSingleSlot(3,5).getColor());
        try {
            controller.checkSelect(slotChoice);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }
    }

    @Test
    void selectThreeCatchable() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        System.out.println(model.getTable().getSingleSlot(3,2).getColor());
        System.out.println(model.getTable().getSingleSlot(4,2).getColor());
        System.out.println(model.getTable().getSingleSlot(5,2).getColor());
        controller.checkSelect(slotChoice2);
        System.out.println(controller.selectedSlots[0].getColor());
        System.out.println(controller.selectedSlots[1].getColor());
        System.out.println(controller.selectedSlots[2].getColor());
    }

    @Test
    void selectThreeNotCatchable() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(1,3);
        slotChoice2[1] = new SlotChoice(2,3);
        slotChoice2[2] = new SlotChoice(3,3);
        System.out.println(model.getTable().getSingleSlot(1,3).getColor());
        System.out.println(model.getTable().getSingleSlot(2,3).getColor());
        System.out.println(model.getTable().getSingleSlot(3,3).getColor());
        try {
            controller.checkSelect(slotChoice2);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
            System.out.println(controller.selectedSlots[2].getColor());
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }
    }

    @Test
    void selectThreeNotAdjacent() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(2,3);
        slotChoice2[1] = new SlotChoice(1,3);
        slotChoice2[2] = new SlotChoice(3,3);
        System.out.println(model.getTable().getSingleSlot(2,3).getColor());
        System.out.println(model.getTable().getSingleSlot(1,3).getColor());
        System.out.println(model.getTable().getSingleSlot(3,3).getColor());
        try {
            controller.checkSelect(slotChoice2);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
            System.out.println(controller.selectedSlots[2].getColor());
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }
    }

    @Test
    void selectThreeCorrectNearbyBug() throws NotAdjacentSlotsException, NotCatchableException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(4,2);
        slotChoice2[1] = new SlotChoice(3,2);
        slotChoice2[2] = new SlotChoice(5,2);
        System.out.println(model.getTable().getSingleSlot(4,2).getColor());
        System.out.println(model.getTable().getSingleSlot(3,2).getColor());
        System.out.println(model.getTable().getSingleSlot(5,2).getColor());
        try {
            controller.checkSelect(slotChoice2);
            System.out.println(controller.selectedSlots[0].getColor());
            System.out.println(controller.selectedSlots[1].getColor());
            System.out.println(controller.selectedSlots[2].getColor());
        }catch(NotCatchableException e){
            System.out.println("Una delle tessere selezionate non è catchable!");
        }catch(NotAdjacentSlotsException e){
            System.out.println("Le tessere selezionate non sono adiacenti");
        }
    }
    */
}