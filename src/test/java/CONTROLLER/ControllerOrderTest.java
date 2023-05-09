package CONTROLLER;

import Errors.NotAdjacentSlotsException;
import Errors.NotCatchableException;
import MODEL.Game;
import VIEW.OrderChoice;
import VIEW.SlotChoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ControllerOrderTest {


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
    void orderTwoTest() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        OrderChoice orderChoice = new OrderChoice(1,1,1);
        controller.checkOrder(orderChoice);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
    }

    @Test
    void orderThreeTest() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
        OrderChoice o = new OrderChoice(3,2,1);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }

    @Test
    void orderThreeTest1() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor()+"\n");
        OrderChoice o = new OrderChoice(3,1,2);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }

    @Test
    void orderThreeTest2() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor()+"\n");
        OrderChoice o = new OrderChoice(2,1,3);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }

    @Test
    void orderThreeTest3() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor()+"\n");
        OrderChoice o = new OrderChoice(2,3,1);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }

    @Test
    void orderThreeTest4() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor()+"\n");
        OrderChoice o = new OrderChoice(1,3,2);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }

    @Test
    void orderThreeTest5() throws NotAdjacentSlotsException, NotCatchableException, RemoteException {
        SlotChoice[] slotChoice = new SlotChoice[2];
        slotChoice[0] = new SlotChoice(4,1);
        slotChoice[1] = new SlotChoice(5,1);
        controller.checkSelect(slotChoice);
        model.getTable().catchAfterRefill();
        SlotChoice[] slotChoice2 = new SlotChoice[3];
        slotChoice2[0] = new SlotChoice(3,2);
        slotChoice2[1] = new SlotChoice(4,2);
        slotChoice2[2] = new SlotChoice(5,2);
        controller.checkSelect(slotChoice2);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor()+"\n");
        OrderChoice o = new OrderChoice(1,2,3);
        controller.checkOrder(o);
        System.out.println(controller.getSelectedSlots()[0].getColor());
        System.out.println(controller.getSelectedSlots()[1].getColor());
        System.out.println(controller.getSelectedSlots()[2].getColor());
    }
}