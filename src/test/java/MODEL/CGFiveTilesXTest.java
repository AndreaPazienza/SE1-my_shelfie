package MODEL;

import MODEL.CGFiveTilesX;
import MODEL.Color;
import MODEL.CommonGoalAbs;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGFiveTilesXTest {

    private Player current1;
    //private MODEL.PersonalShelf shelfAnalized;
    private CommonGoalAbs testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGFiveTilesX(4);
        // shelfAnalized = new MODEL.PersonalShelf();
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void checkRightRandomX(){
        current1.getShelf().getSingleSlot(0,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1, 3).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,4 ).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());

    }
    @Test
    void checkWrongX(){
        current1.getShelf().getSingleSlot(0,2 ).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1, 3).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(2,2 ).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(2,4 ).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());

    }
    @Test
    void checkWrongRandomX(){
        current1.getShelf().getSingleSlot(0,2 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(1,1 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(2,2 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(2,4 ).setColor(Color.randomColor());
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore());

    }
    @Test
    void checkMultiNotAllCGFourPlayersX(){

        current1.getShelf().getSingleSlot(0,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1, 3).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,4 ).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        testingCommonGoal.incrementCG();
        assertEquals(8, current1.getScore());
        Player current2 = new Player("RubV18");
        current2.getShelf().getSingleSlot(5,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(4,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,2 ).setColor(Color.YELLOW);
        testingCommonGoal.control(current2);
        testingCommonGoal.incrementCG();
        assertEquals(6, current2.getScore());
        Player current3 = new Player("Obsolete");
        current3.getShelf().getSingleSlot(0,0 ).setColor(Color.BLUE);
        current3.getShelf().getSingleSlot(3,4).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(2,2 ).setColor(Color.GREEN);
        current3.getShelf().getSingleSlot(3,2 ).setColor(Color.YELLOW);
        current3.getShelf().getSingleSlot(4,1 ).setColor(Color.WHITE);
        testingCommonGoal.control(current3);
        testingCommonGoal.incrementCG();
        assertEquals(0, current3.getScore());

    }

    @Test
    void testShow(){
        testingCommonGoal.show();
    }

}