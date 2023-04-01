import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CGFourCornersTest {

    private Player current1;
    //private PersonalShelf shelfAnalized;
    private CGFourCorners testingCommonGoal;

    @BeforeEach
    public void init(){
      current1 = new Player("Dovahkiin");
      testingCommonGoal = new CGFourCorners(4);
      // shelfAnalized = new PersonalShelf();
    }

    @Test
    void emptyShelf(){
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);

    }
    @Test
    void onlyOneColor(){
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void twoOneColor(){
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void threeOneColor(){
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        assertEquals(0, points);
    }
    @Test
    void threeCGachived(){
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(0,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        //assertEquals(0);
        assertEquals(8, points);
    }

    @Test
    void twoPlayersCGAchived(){
        Player current2 = new Player("Mike");
        current1.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        current1.getShelf().getSingleSlot(0,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        testingCommonGoal.control(current1);
        int points = current1.getScore();
        //assertEquals(0);
        assertEquals(8, points);

        current2.getShelf().getSingleSlot(0,0).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        current2.getShelf().getSingleSlot(0,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        testingCommonGoal.control(current2);
        points = current2.getScore();
        //assertEquals(0);
        assertEquals(6, points);

    }



}