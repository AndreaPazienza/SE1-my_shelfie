package MODEL;

import MODEL.CGEightTilesSameType;
import MODEL.Color;
import MODEL.CommonGoalAbs;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGEightTilesSameTypeTest {


    private Player current1;
    //private MODEL.PersonalShelf shelfAnalized;
    private CommonGoalAbs testingCommonGoal;

    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGEightTilesSameType(4);
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
    void notReady(){
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        assertEquals(0,current1.getScore());
    }

    @Test
    void checkEightTilesDifferentColor(){
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(3,2).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.randomColor());
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.randomColor());
        testingCommonGoal.control(current1);
        assertEquals(0,current1.getScore());
    }
    @Test
    void checkEightTilesSameColor(){
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        assertEquals(8,current1.getScore());
    }
    @Test
    void checkPointsOnDifferent(){
    }
    @Test
    void checkNotDoubleAssignOnePlayer(){
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        testingCommonGoal.control(current1);
        assertEquals(8,current1.getScore());
        testingCommonGoal.control((current1));
        assertEquals(8, current1.getScore());

    }
    @Test
    void checkAssignThreePlayer(){
        testingCommonGoal = new CGEightTilesSameType(3);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        Player current2 = new Player("RubV18");
        current2.getShelf().getSingleSlot(0,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,1 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(0,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,2 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,4 ).setColor(Color.YELLOW);
        Player current3 = new Player("Obsolete");
        current3.getShelf().getSingleSlot(0,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,4).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(2,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(4,1 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(5,3 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,3 ).setColor(Color.WHITE);

        //Controllo effettivo
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current2);
        assertEquals(6, current2.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current3);
        assertEquals(4, current3.getScore());
        testingCommonGoal.incrementCG();
        assertEquals(0, testingCommonGoal.playing);

    }

    @Test
    void checkNotAssignAllThreePlayer(){
        //definizione
        testingCommonGoal = new CGEightTilesSameType(3);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        Player current2 = new Player("RubV18");
        current2.getShelf().getSingleSlot(0,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,1 ).setColor(Color.GREY);
        current2.getShelf().getSingleSlot(0,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,2 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1 ).setColor(Color.GREY);
        current2.getShelf().getSingleSlot(3,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,4 ).setColor(Color.YELLOW);
        Player current3 = new Player("Obsolete");
        current3.getShelf().getSingleSlot(0,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,4).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(2,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(4,1 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(5,3 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,3 ).setColor(Color.WHITE);

        //Controllo effettivo
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current2);
        assertEquals(0, current2.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current3);
        assertEquals(6, current3.getScore());

    }

    @Test
    void checkNotAssignAllFourPlayer(){
        //definizione
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,2).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(2,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(3,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,2 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,1 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(1,4 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,3 ).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LBLUE);
        Player current2 = new Player("RubV18");
        current2.getShelf().getSingleSlot(0,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(3,2).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,1 ).setColor(Color.GREY);
        current2.getShelf().getSingleSlot(0,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,2 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(2,1 ).setColor(Color.GREY);
        current2.getShelf().getSingleSlot(3,4 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(5,3 ).setColor(Color.YELLOW);
        current2.getShelf().getSingleSlot(1,4 ).setColor(Color.YELLOW);
        Player current3 = new Player("Obsolete");
        current3.getShelf().getSingleSlot(0,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,4).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(2,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(3,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(4,1 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(5,3 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(1,0 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,2 ).setColor(Color.WHITE);
        current3.getShelf().getSingleSlot(0,3 ).setColor(Color.WHITE);
        Player current4 = new Player("GreenArrow");
        current4.getShelf().getSingleSlot(0,0 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(3,4).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(2,2 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(3,2 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(4,1 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(5,3 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(1,0 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(0,2 ).setColor(Color.BLUE);
        current4.getShelf().getSingleSlot(0,3 ).setColor(Color.BLUE);


        //Controllo effettivo
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current2);
        assertEquals(0, current2.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current3);
        assertEquals(6, current3.getScore());
        testingCommonGoal.incrementCG();
        testingCommonGoal.control(current4);
        assertEquals(0, current4.getScore());


    }
    @Test
    void testShow(){
        testingCommonGoal.show();
    }

}