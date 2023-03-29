public class Bag {

    private Slot[] inBag;

    //Costrutttore di Bag come array ordinato di Slot (l'estrazione randomica è lasciata al metodo Refill di Dashboard)
    public Bag () {

        inBag = new Slot[132];

        for (int i = 0; i < inBag.length; i ++) {

            switch (i % 6) {
                case 0: inBag[i] = new Slot(Color.GREEN);
                case 1: inBag[i] = new Slot(Color.PINK);
                case 2: inBag[i] = new Slot(Color.BLUE);
                case 3: inBag[i] = new Slot(Color.LIGHTBLUE);
                case 4: inBag[i] = new Slot(Color.WHITE);
                case 5: inBag[i] = new Slot(Color.YELLOW);
            }

            switch (i / 6) {
                case 0: inBag[i].setType(Type.TYPE1);
                case 1: inBag[i].setType(Type.TYPE2);
                case 2: inBag[i].setType(Type.TYPE3);
                case 3: inBag[i].setType(Type.TYPE4);
                case 4: inBag[i].setType(Type.TYPE5);
                case 5: inBag[i].setType(Type.TYPE6);
            }

            inBag[i].setCatchable (false);
        }
    }

    public Slot[] getInBag() {
        return this.inBag;
    }

    //Restituisce lo Slot dell'array indicato dall'indice, in caso di Slot grigio restituisce il primo slot non grigio andando verso destra
    public Slot validExtraction(int index) {

        int i = 0;

        //Se lo Slot estratto è grigio, passa al successivo
        while (inBag[index].getColor().equals(Color.GREY)) {
                i ++;
                index = (index + i) % inBag.length;
        }

        return inBag[index];
    }


}
