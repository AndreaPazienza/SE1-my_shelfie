package MODEL;

import MODEL.CGFourGroupsFourTiles;
import MODEL.Color;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGFourGroupsFourTilesTest {

    private Player current;
    private CGFourGroupsFourTiles testCommonGoal;

    @BeforeEach
    public void init(){
        current = new Player("Teo");
        testCommonGoal = new CGFourGroupsFourTiles(4);
    }

    @Test
    void emptyShelf(){
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(0, points);
    }

    @Test
    void cGoalAchieved(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(4,0).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(4,1).setColor(Color.WHITE);
        current.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8, points);
    }

    @Test
        void cGoalAchieved8Tiles(){
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8, points);
    }

    @Test
    void
    twoPlayersReachedGoal(){
        Player second = new Player("Ema");
        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8, points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,1).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(second);
        points = second.getScore();
        assertEquals(6, points);
    }

    @Test
    void twoPlayerReachOneWrong(){
        Player second = new Player("Ema");
        Player third = new Player("Pietro");

        current.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,1).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,2).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(1,3).setColor(Color.YELLOW);
        current.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        current.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(current);
        int points = current.getScore();
        assertEquals(8, points);

        testCommonGoal.incrementCG();
        second.getShelf().getSingleSlot(0,0).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,1).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,2).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(0,3).setColor(Color.YELLOW);
        second.getShelf().getSingleSlot(5,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(5,1).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,0).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(4,1).setColor(Color.WHITE);
        second.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(3,4).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(4,2).setColor(Color.GREEN);
        second.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,2).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        second.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        testCommonGoal.control(second);
        points = second.getScore();
        assertEquals(6, points);

        testCommonGoal.incrementCG();
        third.getShelf().getSingleSlot(0,0).setColor(Color.BLUE);
        third.getShelf().getSingleSlot(1,0).setColor(Color.BLUE);
        third.getShelf().getSingleSlot(2,0).setColor(Color.BLUE);
        third.getShelf().getSingleSlot(3,0).setColor(Color.BLUE);
        testCommonGoal.control(third);
        points = third.getScore();
        assertEquals(0, points);
    }

    @Test
    void testShow(){
        testCommonGoal.show();
    }

}