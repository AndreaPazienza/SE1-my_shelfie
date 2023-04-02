
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;

public class PersonalGoalTest{


    @Test
     void testMethod0(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tShelf = new PersonalShelf();
        assertEquals(0, pgoal.assignPoint(tShelf));
    }
    @Test
    void testMethod1(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tshelf = new PersonalShelf();
        Color c = pgoal.getSingleTarget(0).getTile();
        int x = pgoal.getSingleTarget(0).getPosX();
        int y = pgoal.getSingleTarget(0).getPosY();
        tshelf.getSingleSlot(x,y).setColor(c);
        assertEquals(1, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod2(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 2; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(2, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod3(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 3; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(4, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod4(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 4; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(6, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod5(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tshelf = new PersonalShelf();
        for(int i = 0; i < 5; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tshelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(9, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod6(){
        PersonalGoal pgoal = new PersonalGoal();
        PersonalShelf tShelf = new PersonalShelf();
        for(int i = 0; i < 6; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tShelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(12, pgoal.assignPoint(tShelf));
    }
}