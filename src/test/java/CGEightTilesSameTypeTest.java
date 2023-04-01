import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGEightTilesSameTypeTest {


    private Player current1;
    //private PersonalShelf shelfAnalized;
    private CommonGoalAbs testingCommonGoal;

    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGEightTilesSameType(4);
        // shelfAnalized = new PersonalShelf();
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


}