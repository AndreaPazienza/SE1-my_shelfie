import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGSameTypeSquareTest {

    private Player current;
    private CGSameTypeSquare testCommonGoal;

    @BeforeEach
    public void init(){
        current = new Player("Luigi");
        testCommonGoal = new CGSameTypeSquare(4);
    }

    @Test
    void emptyShelf(){
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(0, points);
    }

    @Test
    void adiacentNotASquare(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(0,1).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(0,2).setColor(Color.LIGHTBLUE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(0, points);
    }

    @Test
    void commonGoalAchieved(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,1).setColor(Color.YELLOW);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);
    }

    @Test
    void twoSquaresDiffType(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,1).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(5,1).setColor(Color.GREEN);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);
    }

    @Test
    void twoPlayersReachedGoal(){
        Player second = new Player("Liana");

        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,1).setColor(Color.YELLOW);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,4).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,4).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,2).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(4,2).setColor(Color.PINK);
        testCommonGoal.control(second);
        int points1 = second.getScore();
        assertEquals(6,points1);
    }

    @Test
    void twoPlayersAchievedOneWrong(){
        Player third = new Player("Ricky");
        Player second = new Player("Liana");

        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,1).setColor(Color.YELLOW);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,4).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,4).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,2).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(4,2).setColor(Color.PINK);
        testCommonGoal.control(second);
        int points1 = second.getScore();
        assertEquals(6,points1);

        testCommonGoal.incrementCG();
        third.getShelf().getSingleSlot(1,2).setColor(Color.WHITE);
        third.getShelf().getSingleSlot(1,3).setColor(Color.WHITE);
        third.getShelf().getSingleSlot(2,2).setColor(Color.WHITE);
        third.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        testCommonGoal.control(third);
        int points2 = third.getScore();
        assertEquals(0,points2);
    }

    @Test
    void moreThanTwoSquares(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(4,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(3,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(2,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(2,3).setColor(Color.YELLOW);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);
    }

    @Test
    void twoRowsOfThreeTiles(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,2).setColor(Color.YELLOW);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8, points);   //quindi come lo gestiamo questo?
    }

}