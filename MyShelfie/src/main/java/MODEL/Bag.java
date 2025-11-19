package MODEL;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represents the bag of slots, ordered by color.
 */
public class Bag{

    /**
     * The list of slots still in the bag.
     */
    private ArrayList<Slot> inBag = new ArrayList<>();

    /**
     * Constructor for Bag class.
     */
    public Bag() {
        //Initialization of the 22 green slots' attributes
        for (int i = 0; i < 132; i++) {
            if(i < 22) {
                Slot toAdd1 = new Slot(Color.GREEN);
                toAdd1.fillType(i % 3);
                toAdd1.setCatchable(false);
                inBag.add(toAdd1);
            }
            //Initialization of the 22 pink slots' attributes
            if(i > 21 && i < 44){
                Slot toAdd2 = new Slot(Color.PINK);
                toAdd2.fillType(i%3);
                toAdd2.setCatchable(false);
                inBag.add(toAdd2);
            }
            //Initialization of the 22 blue slots' attributes
            if(i > 43 && i < 66){
                Slot toAdd3 = new Slot(Color.BLUE);
                toAdd3.fillType(i % 3);
                toAdd3.setCatchable(false);
                inBag.add(toAdd3);
            }
            //Initialization of the 22 light blue slots' attribute
            if(i > 65 && i < 88){
                Slot toAdd4 = new Slot(Color.LBLUE);
                toAdd4.fillType(i % 3);
                toAdd4.setCatchable(false);
                inBag.add(toAdd4);
            }
            //Initialization of the 22 white slots' attributes
            if(i > 87 && i < 110){
                Slot toAdd5 = new Slot(Color.WHITE);
                toAdd5.fillType(i%3);
                toAdd5.setCatchable(false);
                inBag.add(toAdd5);
            }
            //Initialization of the 22 yellow slots' attributes
            if(i > 109 && i < 132){
                Slot toAdd6 = new Slot(Color.YELLOW);
                toAdd6.fillType(i % 3);
                toAdd6.setCatchable(false);
                inBag.add(toAdd6);
            }
        }
    }

    /**
     * Retrieves the list of slots still in the bag.
     *
     * @return The list of slots still in the bag.
     */
    public ArrayList<Slot> getInBag() {
        return inBag;
    }

    /**
     * Retrieves a chosen slot from the bag.
     *
     * @param index The index of the slot to return from the bag.
     * @return The slot corresponding to the given index from the list of slots still in the bag.
     */
    public Slot getFromBag(int index) {
        return this.inBag.get(index);
    } //selezione generica

    /**
     * Retrieves a random slot from the bag.
     *
     * @return The slot corresponding toa casual index from the list of slots still in the bag.
     */
    public Slot getSingleSlot(){
        //The random index is calculated using the current size of the bag
        int randomIndex = new Random().nextInt(inBag.size());
        Slot returningSlot = inBag.get(randomIndex);
        inBag.remove(randomIndex);
        return returningSlot;
    }



}
