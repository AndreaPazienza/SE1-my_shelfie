public class Bag {

    private Slot[] inBag;

    //Costrutttore di Bag come array ordinato di Slot (l'estrazione randomica è lasciata al metodo Refill di Dashboard)
    public Bag () {

        int i = 0;

        inBag = new Slot[132];

        for (i = 0; i <132; i ++) {
            inBag[i] = new  Slot();
            inBag[i].setColore (i % 6);
            inBag[i].setCatchable (true);
        }
    }

    //Get e Set degli attributi privati
    public Slot[] getInBag() {
        return inBag;
    }

    public void setInBag(Slot[] inBag) {
        this.inBag = inBag;
    }
}
