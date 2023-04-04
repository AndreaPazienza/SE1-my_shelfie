import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Bag {

    private ArrayList<Slot> inBag = new ArrayList<>();

    //Costruttore di Bag come array ordinato di Slot (l'estrazione randomica è lasciata al metodo Refill di Dashboard)
    public Bag() {
        //Setting del colore
        for (int i = 0; i < 132; i++) {
            if(i < 22) {
                Slot toAdd1 = new Slot(Color.GREEN);
                toAdd1.fillType(i % 3);
                toAdd1.setCatchable(false);
                inBag.add(toAdd1);
            }
            if(i > 21 && i < 44){
                Slot toAdd2 = new Slot(Color.PINK);
                toAdd2.fillType(i%3);
                toAdd2.setCatchable(false);
                inBag.add(toAdd2);
            }
            if(i > 43 && i < 66){
                Slot toAdd3 = new Slot(Color.BLUE);
                toAdd3.fillType(i % 3);
                toAdd3.setCatchable(false);
                inBag.add(toAdd3);
            }
            if(i > 65 && i < 88){
                Slot toAdd4 = new Slot(Color.LIGHTBLUE);
                toAdd4.fillType(i % 3);
                toAdd4.setCatchable(false);
                inBag.add(toAdd4);
            }
            if(i > 87 && i < 110){
                Slot toAdd5 = new Slot(Color.WHITE);
                toAdd5.fillType(i%3);
                toAdd5.setCatchable(false);
                inBag.add(toAdd5);
            }
            if(i > 109 && i < 132){
                Slot toAdd6 = new Slot(Color.BLUE);
                toAdd6.fillType(i % 3);
                toAdd6.setCatchable(false);
                inBag.add(toAdd6);
            }

        }
    }

    public ArrayList<Slot> getInBag() {
        return inBag;
    }

    public Slot getFromBag(int index) {
        return this.inBag.get(index);
    }

    public Slot getSingleSlot(){
        int randomIndex = new Random().nextInt(inBag.size());
       Slot returningSlot = inBag.get(randomIndex);
       inBag.remove(randomIndex);
       return returningSlot;
    }

}
