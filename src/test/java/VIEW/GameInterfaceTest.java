package VIEW;
import MODEL.*;
import org.testng.annotations.Test;

import org.junit.jupiter.api.RepeatedTest;

public class GameInterfaceTest {

    //Test stampa della dashboard passato
    @Test
    public void displayDashboardTest() {

        GameInterface display = new GameInterface();
        Dashboard table = new Dashboard(4);
        table.refill(new Bag());

        display.displayDashboard(table);
    }

    //Test stampa della personal shelf
    @Test
    public void displayPersonalShelfTest() {

        GameInterface display = new GameInterface();
        PersonalShelf pShelf = new PersonalShelf();
        Bag bag = new Bag();
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(5,i).setColor(bag.getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(4,i).setColor(bag.getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(3,i).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,0).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,3).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,4).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(1,3).setColor(bag.getSingleSlot().getColor());

        display.displayPersonalShelf(pShelf);
    }

    //Test stampa del personal goal passato
    @Test
    public void displayPgoalTest() {

        GameInterface display = new GameInterface();
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pGoal = deck.extractionPGoal();

        display.displayPersonalGoal(pGoal);
    }

    //Test stampa dei display assieme passato
    @Test
    public void displayTest () {

        GameInterface display = new GameInterface();
        Dashboard table = new Dashboard(4);
        Bag bag = new Bag();
        table.refill(bag);
        PersonalShelf pShelf = new PersonalShelf();
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(5,i).setColor(bag.getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(4,i).setColor(bag.getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(3,i).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,0).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,3).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(2,4).setColor(bag.getSingleSlot().getColor());
        pShelf.getSingleSlot(1,3).setColor(bag.getSingleSlot().getColor());
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pGoal = deck.extractionPGoal();

        display.displayDashboard(table);
        display.displayPersonalShelf(pShelf);
        display.displayPersonalGoal(pGoal);
    }
}
