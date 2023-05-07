package VIEW;
import MODEL.*;
import org.testng.annotations.Test;

import org.junit.jupiter.api.RepeatedTest;

import java.rmi.RemoteException;

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

    //Test stampa del common goal
    @Test
    public void displayCgoalTest() {

        GameInterface display = new GameInterface();
        Game game = new Game(4);
        game.signPlayer("nome1");
        game.signPlayer("nome2");
        game.signPlayer("nome3");
        game.signPlayer("nome4");
        game.setCommonGoal1(game.getCommonGoalDeck().getACommonGoal());
        game.setCommonGoal2(game.getCommonGoalDeck().getACommonGoal());

        display.displayCommonGoal(new GameView(1, game));
    }

    //Test stampa dei display assieme passato
    @Test
    public void displayTest () {

        GameInterface display = new GameInterface();
        Game game = new Game(4);
        game.signPlayer("nome1");
        game.signPlayer("nome2");
        game.signPlayer("nome3");
        game.signPlayer("nome4");
        game.getTable().refill(game.getBag());
        PersonalShelf pShelf = game.getPlayer()[0].getShelf();
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(5,i).setColor(game.getBag().getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(4,i).setColor(game.getBag().getSingleSlot().getColor());
        for (int i = 0; i < PersonalShelf.N_COLUMN; i++)
            pShelf.getSingleSlot(3,i).setColor(game.getBag().getSingleSlot().getColor());
        pShelf.getSingleSlot(2,0).setColor(game.getBag().getSingleSlot().getColor());
        pShelf.getSingleSlot(2,3).setColor(game.getBag().getSingleSlot().getColor());
        pShelf.getSingleSlot(2,4).setColor(game.getBag().getSingleSlot().getColor());
        pShelf.getSingleSlot(1,3).setColor(game.getBag().getSingleSlot().getColor());
        game.getPlayer()[0].setPgoal(game.getDeck().extractionPGoal());
        PersonalGoal pGoal = game.getPlayer()[0].getPgoal();
        game.setCommonGoal1(game.getCommonGoalDeck().getACommonGoal());
        game.setCommonGoal2(game.getCommonGoalDeck().getACommonGoal());

        display.displayDashboard(game.getTable());
        display.displayPersonalShelf(pShelf);
        display.displayPersonalGoal(pGoal);
        display.displayCommonGoal(new GameView(1, game));
    }
}
