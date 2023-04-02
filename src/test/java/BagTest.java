import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

public class BagTest {

    private Bag bag;

    @Test
    void testBag () {

        bag = new Bag();

        for (int i = 0; i < bag.getInBag().length; i ++) {
            System.out.print(bag.getInBag()[i].getColor() + "-" + bag.getInBag()[i].getType() + "\n");
        }
    }
}
