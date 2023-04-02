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


}