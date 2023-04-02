import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CGFiveTilesXTest {

    private Player current1;
    //private PersonalShelf shelfAnalized;
    private CommonGoalAbs testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGFiveTilesX(4);
        // shelfAnalized = new PersonalShelf();
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void checkRightRandomX(){
        current1.getShelf().getSingleSlot(0,2 ).setColor(Color.LIGHTBLUE);
        current1.getShelf().getSingleSlot(0,4 ).setColor(Color.LIGHTBLUE);
        current1.getShelf().getSingleSlot(1,1 ).setColor(Color.LIGHTBLUE);
        current1.getShelf().getSingleSlot(2,2 ).setColor(Color.LIGHTBLUE);
        current1.getShelf().getSingleSlot(2,4 ).setColor(Color.LIGHTBLUE);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore());

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

}