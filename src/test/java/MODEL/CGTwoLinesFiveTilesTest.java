package MODEL;

import MODEL.CGTwoLinesFiveTiles;
import MODEL.Color;
import MODEL.CommonGoalAbs;
import MODEL.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGTwoLinesFiveTilesTest {
    private Player current1;
    private CommonGoalAbs testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGTwoLinesFiveTiles(4);
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void checkTwoRowsLeftRight(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(0,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(0,2).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(0,3).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(0,4).setColor(Color.BLUE);

        current1.getShelf().getSingleSlot(5,3).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(5,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,2).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(5,1).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);

        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore() );
    }
    @Test
    void checkTwoRowsNotDiffColorsLeftRight(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(0,1).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(0,2).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(0,3).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(0,4).setColor(Color.BLUE);

        current1.getShelf().getSingleSlot(5,4).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(5,3).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(5,2).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(5,1).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);

        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }

    @Test
    void testShow(){
        testingCommonGoal.show();
    }


}