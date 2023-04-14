package MODEL;

import MODEL.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGTwoColumnsSixTilesTest {
    private Player current1;
    private CGOnColumn testingCommonGoal;
    @BeforeEach
    public void init(){
        current1 = new Player("Dovahkiin");
        testingCommonGoal = new CGTwoColumnsSixTiles(4);
    }

    @Test
    void emptyOne(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void checkOneColumn(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(2,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(3,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(4,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }
    @Test
    void checkTwoColumnLeftRight(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,0).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(0,4).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(2,4).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(3,4).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,4).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.PINK);
        testingCommonGoal.control(current1);
        assertEquals(8, current1.getScore() );
    }
    @Test
    void checkTwoColumnNextWithOneFine(){
        //Controllo anche il fatto che venga trovata almeno la riga verde
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(1,0).setColor(Color.WHITE);
        current1.getShelf().getSingleSlot(2,0).setColor(Color.PINK);
        current1.getShelf().getSingleSlot(3,0).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,0).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(5,0).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(0,4).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(1,4).setColor(Color.YELLOW);
        current1.getShelf().getSingleSlot(2,4).setColor(Color.BLUE);
        current1.getShelf().getSingleSlot(3,4).setColor(Color.LBLUE);
        current1.getShelf().getSingleSlot(4,4).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(5,4).setColor(Color.PINK);
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }
    @Test
    void checkFullShelfSameColor(){
        for(int i = 0; i < PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++){
                current1.getShelf().getSingleSlot(i,j).setColor(Color.GREEN);
            }
        }
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }
    @Test
    void checkFullShelfDifferentColor(){
        for(int i =0; i < PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++){
                current1.getShelf().getSingleSlot(i,j).setColor(Color.randomColor());
            }
        }
        testingCommonGoal.control(current1);
        assertEquals(0, current1.getScore() );
    }
    }


