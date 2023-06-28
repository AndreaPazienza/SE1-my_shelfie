package MODEL;

import MODEL.CGDecreaseTiles;
import MODEL.Color;
import MODEL.CommonGoalAbs;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGDecreaseTilesTest {

    private Player current1;
    //private MODEL.PersonalShelf shelfAnalized;
    private CommonGoalAbs testingCommonGoal;

    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGDecreaseTiles(4);
        // shelfAnalized = new MODEL.PersonalShelf();
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void emptyFour(){
        Player current2 = new Player("Mike");
        Player current3 = new Player("RubV18");
        Player current4 = new Player("Obsolete");
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
        testingCommonGoal.control(current2);
        points = current2.getScore();
        assertEquals(0, points);
        testingCommonGoal.control(current3);
        points = current3.getScore();
        assertEquals(0, points);
        testingCommonGoal.control(current4);
        points = current4.getScore();
        assertEquals(0, points);
    }
    @Test
    void shelfNotReadyForCGDec(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());

    }
    @Test
    void shelfNotReadyWGreyDec(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.GREY);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());
    }
    @Test
    void shelfNotReadyWGreyCresc(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.GREY);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());
    }
    @Test
    void shelfReadyForCGDiffColors(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void shelfReadyForCGDiffColorsCresc(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void checkCorrectAssignPointsThreePlayers(){
        testingCommonGoal = new CGDecreaseTiles(3);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        Player current3 = new Player("RubV18");
        current3.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current3.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        Player current2 = new Player("Obsolete");
        current2.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(4,3).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current2);
        assertEquals(6, current2.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current3);
        assertEquals(4, current3.getScore());

    }
    @Test
    void checkCorrectAssignPointsFourPlayers(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        Player current3 = new Player("RubV18");
        current3.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current3.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        Player current2 = new Player("Obsolete");
        current2.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(4,3).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        Player current4 = new Player("GreenArrow");
        current4.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current4.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current4.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current4.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current4.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);

        testingCommonGoal.control(current1);
        testingCommonGoal.incrementCG();
        assertEquals(8, current1.getScore());
        testingCommonGoal.control(current2);
        testingCommonGoal.incrementCG();
        assertEquals(6, current2.getScore());
        testingCommonGoal.control(current3);
        testingCommonGoal.incrementCG();
        assertEquals(4, current3.getScore());
        testingCommonGoal.control(current4);
        testingCommonGoal.incrementCG();
        assertEquals(2, current4.getScore());

    }
    @Test
    void checkCorrectAssignPointsNotAllFourPlayers(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREY);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        Player current3 = new Player("RubV18");
        current3.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current3.getShelf().getSingleSlot(3,2).setColor(Color.GREY);
        current3.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        Player current2 = new Player("Obsolete");
        current2.getShelf().getSingleSlot(1,0).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(4,3).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        Player current4 = new Player("GreenArrow");
        current4.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current4.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current4.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current4.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current4.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);

        testingCommonGoal.control(current1);
        testingCommonGoal.incrementCG();
        assertEquals(0, current1.getScore());
        testingCommonGoal.control(current2);
        testingCommonGoal.incrementCG();
        assertEquals(8, current2.getScore());
        testingCommonGoal.control(current3);
        testingCommonGoal.incrementCG();
        assertEquals(0, current3.getScore());
        testingCommonGoal.control(current4);
        testingCommonGoal.incrementCG();
        assertEquals(6, current4.getScore());
        assertEquals(0, testingCommonGoal.playing);

    }
    @Test
    void testShow(){
        testingCommonGoal.show();
    }

}