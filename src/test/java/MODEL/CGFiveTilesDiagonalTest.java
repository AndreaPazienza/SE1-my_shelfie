package MODEL;

import MODEL.CGFiveTilesDiagonal;
import MODEL.Color;
import MODEL.CommonGoalAbs;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGFiveTilesDiagonalTest {


    private Player current1;
    private CommonGoalAbs testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGFiveTilesDiagonal(4);
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void shelfNotReadyOnLowerWGreyCresc(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.GREY);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());
    }
    @Test
    void shelfReadyForCGOnLowerSXColors(){
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void shelfReadyForCGOnUpperSXColors(){
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,4).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void shelfReadyForCGOnUpperDXColors(){
        current1.getShelf().getSingleSlot(0,4).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,0).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void shelfReadyForCGOnLowerDXColors(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }
    @Test
    void randomOnDiagonal(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());
    }
    @Test
    void randomOnDiagonalOnlyLowerDXWork(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.LBLUE);

        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(4,4).setColor(Color.GREEN);

        current1.getShelf().getSingleSlot(1,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(2,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.GREY);

        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
    }

    @Test
    void multipleRoundCorrectAssignment(){
        current1.getShelf().getSingleSlot(5,0).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(4,1).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(2,3).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.PINK);
        Player current2 = new Player("Obsolete");
        current2.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(1,1).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(2,2).setColor(Color.GREY);
        current2.getShelf().getSingleSlot(3,3).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(4,4).setColor(Color.GREEN);
        Player current3 = new Player("RubV18");
        current3.getShelf().getSingleSlot(1,0).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(2,1).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,2).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(4,3).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(5,4).setColor(Color.WHITE);
        Player current4 = new Player("GreenArrow");
        current4.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current4.getShelf().getSingleSlot(4,1).setColor(Color.YELLOW);
        current4.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current4.getShelf().getSingleSlot(2,3).setColor(Color.YELLOW);
        current4.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        testingCommonGoal.control(current1);
        testingCommonGoal.incrementCG();
        assertEquals(8, current1.getScore());
        assertEquals(1, testingCommonGoal.playing);
        testingCommonGoal.control(current2);
        testingCommonGoal.incrementCG();
        assertEquals(0, current2.getScore());
        assertEquals(2, testingCommonGoal.playing);
        testingCommonGoal.control(current3);
        testingCommonGoal.incrementCG();
        assertEquals(6, current3.getScore());
        assertEquals(3, testingCommonGoal.playing);
        testingCommonGoal.control(current4);
        testingCommonGoal.incrementCG();
        assertEquals(4, current4.getScore());
        assertEquals(0, testingCommonGoal.playing);
        testingCommonGoal.control(current1);
        testingCommonGoal.incrementCG();
        assertEquals(8, current1.getScore());
        assertEquals(1, testingCommonGoal.playing);
        current2.getShelf().getSingleSlot(2,2).setColor(Color.GREEN);
        testingCommonGoal.control(current2);
        testingCommonGoal.incrementCG();
        assertEquals(2, current2.getScore());
        assertEquals(2, testingCommonGoal.playing);
    }



}