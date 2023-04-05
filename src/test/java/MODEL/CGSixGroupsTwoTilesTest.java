package MODEL;

import MODEL.CGSixGroupsTwoTiles;
import MODEL.Color;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGSixGroupsTwoTilesTest {

    private Player current;
    private CGSixGroupsTwoTiles testCommonGoal;

    @BeforeEach
    public void init(){
        current = new Player("Luigi");
        testCommonGoal = new CGSixGroupsTwoTiles(4);
    }

    @Test
    void emptyShelf(){
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(0, points);
    }

    @Test
    void reachedGoal(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(4,1).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);
    }

    @Test
    void twoPlayersReachGoal(){

        Player second = new Player("Talla");

        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(4,1).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        second.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        second.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,4).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(second);
        int points1 = second.getScore();
        assertEquals(6,points1);
    }

    @Test
    void notSixCouples(){
        current.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(4,1).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(0,points);
    }

    @Test
    void twoPlayersReachOneWrong(){
        Player second = new Player("Talla");
        Player third = new Player("Chiara");

        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(4,1).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(1,0).setColor(Color.LIGHTBLUE);
        second.getShelf().getSingleSlot(1,1).setColor(Color.LIGHTBLUE);
        second.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,4).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        testCommonGoal.control(second);
        int points1 = second.getScore();
        assertEquals(6,points1);

        third.getShelf().getSingleSlot(4,2).setColor(Color.BLUE);
        third.getShelf().getSingleSlot(4,3).setColor(Color.BLUE);
        third.getShelf().getSingleSlot(5,3).setColor(Color.BLUE);
        testCommonGoal.control(third);
        int points2 = third.getScore();
        assertEquals(0,points2);
    }

    @Test
    void squaresOrRowsOrColumns(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current.getShelf().getSingleSlot(0,4).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(3,4).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(2,4).setColor(Color.BLUE);
        current.getShelf().getSingleSlot(1,4).setColor(Color.BLUE);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8,points);
    }
}