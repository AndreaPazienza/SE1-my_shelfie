import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import static org.junit.jupiter.api.Assertions.*;

class PersonalShelfTest {

    private PersonalShelf shelf;
    @BeforeEach
    public void init() {
        shelf = new PersonalShelf();
    }


    @Test
    void TestInsert(){
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
    void TestAdjacent(){

    }
}