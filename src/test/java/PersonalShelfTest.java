import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalShelfTest {

    private PersonalShelf shelf;
    @BeforeEach
    public void init() {
        shelf = new PersonalShelf();
    }


    @Test
    void testInsert(){
        Slot[] slots = new Slot[3];
        boolean testOk = true;
        for(int i = 0; i < 3; i++){
            slots[i] = new Slot(Color.PINK);
        }
        shelf.insert(slots, 2);
        for(int i = 5; i > 2; i--){
            if(shelf.getSingleSlot(i, 2).getColor().notEquals(Color.PINK)){
                testOk = false;
            }
        }
        System.out.println("Test ok: "+testOk);
    }

    @Test
    void testAdjacent(){
        shelf.getSingleSlot(0,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,1).setColor(Color.GREEN);
        shelf.getSingleSlot(2,2).setColor(Color.GREEN); //controllo che non conti come adiacenti anche le diagonali
        shelf.getSingleSlot(2,1).setColor(Color.LIGHTBLUE);
        int test = shelf.calculatePoints();
        assertEquals(2, test);
    }

    @Test
    void testAdjacent2(){
        shelf.getSingleSlot(0,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,1).setColor(Color.GREEN);
        shelf.getSingleSlot(2,3).setColor(Color.PINK);
        shelf.getSingleSlot(2,2).setColor(Color.PINK);
        shelf.getSingleSlot(2,1).setColor(Color.PINK);
        shelf.getSingleSlot(2,0).setColor(Color.PINK);
        int test = shelf.calculatePoints();
        assertEquals(5, test);
    }
    @Test
    void testAdjacent3(){
        shelf.getSingleSlot(0,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,1).setColor(Color.GREEN);
        shelf.getSingleSlot(2,3).setColor(Color.PINK);
        shelf.getSingleSlot(2,2).setColor(Color.PINK);
        shelf.getSingleSlot(2,1).setColor(Color.PINK);
        shelf.getSingleSlot(2,0).setColor(Color.PINK);
        shelf.getSingleSlot(3,3).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(3,4).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(4,4).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(4,3).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(3,2).setColor(Color.LIGHTBLUE);
        int test = shelf.calculatePoints();
        assertEquals(10, test);
    }

    @Test
    void testAdjacent4(){
        shelf.getSingleSlot(0,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,1).setColor(Color.GREEN);
        shelf.getSingleSlot(3,3).setColor(Color.GREEN);
        shelf.getSingleSlot(3,4).setColor(Color.GREEN);
        shelf.getSingleSlot(4,4).setColor(Color.GREEN);
        shelf.getSingleSlot(4,3).setColor(Color.GREEN);
        shelf.getSingleSlot(3,2).setColor(Color.GREEN);
        int test = shelf.calculatePoints();
        assertEquals(7, test);
    }
    @Test
    void testAdjacent5(){
        shelf.getSingleSlot(0,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,0).setColor(Color.GREEN);
        shelf.getSingleSlot(1,1).setColor(Color.GREEN);
        shelf.getSingleSlot(2,3).setColor(Color.PINK);
        shelf.getSingleSlot(2,2).setColor(Color.PINK);
        shelf.getSingleSlot(2,1).setColor(Color.PINK);
        shelf.getSingleSlot(2,0).setColor(Color.PINK);
        shelf.getSingleSlot(3,3).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(3,4).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(4,4).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(4,3).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(3,2).setColor(Color.LIGHTBLUE);
        shelf.getSingleSlot(4,0).setColor(Color.WHITE);
        shelf.getSingleSlot(3,0).setColor(Color.WHITE);
        shelf.getSingleSlot(3,1).setColor(Color.WHITE);
        shelf.getSingleSlot(4,1).setColor(Color.WHITE);
        shelf.getSingleSlot(4,2).setColor(Color.WHITE);
        shelf.getSingleSlot(5,2).setColor(Color.WHITE);
        int test = shelf.calculatePoints();
        assertEquals(18, test);
    }
    @Test
    void testAdjacent6(){ //controllo che in caso di 6+ tessere = adiacenti non dia più di 8
        for(int i = 0; i < shelf.N_ROWS; i++){
            for(int j = 0; j < shelf.N_COLUMN; j++){
                shelf.getSingleSlot(i, j).setColor(Color.YELLOW);
            }
        }
        int test = shelf.calculatePoints();
        assertEquals(8, test);
    }
}