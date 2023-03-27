import junit.framework.TestCase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class PersonalGoalTest{
   private PersonalGoal pgoal;

    @BeforeTest
    void init(){
        pgoal = new PersonalGoal();
    }



    @Test
     void TestMethod0(){
        PersonalShelf tShelf = new PersonalShelf();
        assertEquals(0, pgoal.AssignPoint(tShelf));
    }
    @Test
    void TestMethod1(){
        PersonalShelf tshelf = new PersonalShelf();
        Color c = pgoal.getSingleTarget(0).getTile();
        int x = pgoal.getSingleTarget(0).getPosX();
        int y = pgoal.getSingleTarget(0).getPosY();
        tshelf.getSingleSlot(x,y).setColor(c);
        assertEquals(1, pgoal.AssignPoint(tshelf));
    }
    @Test
    void TestMethod2(){
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 2; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(2, pgoal.AssignPoint(tshelf));
    }
    @Test
    void TestMethod3(){
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 3; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(4, pgoal.AssignPoint(tshelf));
    }
    @Test
    void TestMethod4(){
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 4; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(6, pgoal.AssignPoint(tshelf));
    }
    @Test
    void TestMethod5(){
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 5; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(9, pgoal.AssignPoint(tshelf));
    }
    @Test
    void TestMethod6(){
        PersonalShelf tShelf = new PersonalShelf();
        for(int i = 0; i < 6; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tShelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(12, pgoal.AssignPoint(tShelf));
    }
}