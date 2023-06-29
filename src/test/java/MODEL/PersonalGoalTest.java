package MODEL;

import MODEL.Color;
import MODEL.PersonalGoal;
import MODEL.PersonalShelf;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

public class PersonalGoalTest{


    @Test
     void testMethod0() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
        PersonalShelf tShelf = new PersonalShelf();
        assertEquals(0, pgoal.assignPoint(tShelf));
    }
    @Test
    void testMethod1() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
        PersonalShelf tshelf = new PersonalShelf();
        Color c = pgoal.getSingleTarget(0).getTile();
        int x = pgoal.getSingleTarget(0).getPosX();
        int y = pgoal.getSingleTarget(0).getPosY();
        tshelf.getSingleSlot(x,y).setColor(c);
        assertEquals(1, pgoal.assignPoint(tshelf));
    }
    @Test
    void testMethod2() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
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
    void testMethod3() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
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
    void testMethod4() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
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
    void testMethod5() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
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
    void testMethod6() throws IOException, ParseException {
        PersonalGoalDeck deck = new PersonalGoalDeck();
        PersonalGoal pgoal = deck.extractionPGoal();
        PersonalShelf tShelf = new PersonalShelf();
        for(int i = 0; i < 6; i++) {
            Color c = pgoal.getSingleTarget(i).getTile();
            int x = pgoal.getSingleTarget(i).getPosX();
            int y = pgoal.getSingleTarget(i).getPosY();
            tShelf.getSingleSlot(x,y).setColor(c);
        }
        assertEquals(12, pgoal.assignPoint(tShelf));
    }

    @Test
    void assignPointsTest0(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(0, points);
    }

    @Test
    void assignPointsTest1(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(1, points);
    }

    @Test
    void assignPointsTest2(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        player.getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(2, points);
    }

    @Test
    void assignPointsTest3(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        player.getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        player.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(4, points);
    }

    @Test
    void assignPointsTest4(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        player.getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        player.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        player.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(6, points);
    }

    @Test
    void assignPointsTest5(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        player.getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        player.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        player.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        player.getShelf().getSingleSlot(3,1).setColor(Color.YELLOW);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(9, points);
    }

    @Test
    void assignPointsTest6(){
        PersonalGoal pGoal = new PersonalGoal();
        pGoal.setPGoal1();
        Player player = new Player("Gabriele");
        player.getShelf().getSingleSlot(0,0).setColor(Color.PINK);
        player.getShelf().getSingleSlot(0,2).setColor(Color.BLUE);
        player.getShelf().getSingleSlot(1,4).setColor(Color.GREEN);
        player.getShelf().getSingleSlot(2,3).setColor(Color.WHITE);
        player.getShelf().getSingleSlot(3,1).setColor(Color.YELLOW);
        player.getShelf().getSingleSlot(5,2).setColor(Color.LBLUE);
        int points = pGoal.assignPoint(player.getShelf());
        assertEquals(12, points);
    }
}