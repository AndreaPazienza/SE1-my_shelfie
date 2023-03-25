public class Dashboard {

    private Slot[][] inDashboard;
    private static final int side = 9;

    //Costruttore
    public Dashboard (int n) {

        inDashboard = new Slot[side][side];

        //Inizializzazione prima e nona riga
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4) {
                inDashboard[0][i] = new Slot(Color.BLACK);
                inDashboard[8][8-i] = new Slot(Color.BLACK);
            }
        }

        //Inizializzazione seconda ottava riga
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4 && i != 5) {
                inDashboard[1][i] = new Slot(Color.BLACK);
                inDashboard[7][7-i] = new Slot(Color.BLACK);
            }
        }

        //Inizializzazione terza e settima riga
        for (int i  = 0; i < side; i ++) {
            if (i != 2 && i != 3 && i != 4 && i != 5) {
                inDashboard[2][i] = new Slot(Color.BLACK);
                inDashboard[6][6-i] = new Slot(Color.BLACK);
            }
        }

        //Inizializzazione quarta e sesta riga
        inDashboard[3][0] = new Slot(Color.BLACK);
        inDashboard[5][8] = new Slot(Color.BLACK);

        //Set a nero caselle con 4 (per n minore di 4)
        if (n < 4) {
            inDashboard[0][4] = new Slot(Color.BLACK);
            inDashboard[1][5] = new Slot(Color.BLACK);
            inDashboard[3][1] = new Slot(Color.BLACK);
            inDashboard[4][0] = new Slot(Color.BLACK);
            inDashboard[4][8] = new Slot(Color.BLACK);
            inDashboard[5][7] = new Slot(Color.BLACK);
            inDashboard[7][3] = new Slot(Color.BLACK);
            inDashboard[8][4] = new Slot(Color.BLACK);
        }

        //Set a nero caselle con 3 (per n minore di 3)
        if (n < 3) {
            inDashboard[0][3] = new Slot(Color.BLACK);
            inDashboard[2][2] = new Slot(Color.BLACK);
            inDashboard[2][6] = new Slot(Color.BLACK);
            inDashboard[3][7] = new Slot(Color.BLACK);
            inDashboard[5][0] = new Slot(Color.BLACK);
            inDashboard[6][2] = new Slot(Color.BLACK);
            inDashboard[6][6] = new Slot(Color.BLACK);
            inDashboard[8][5] = new Slot(Color.BLACK);
        }

        //Set a grigio ditutte le caselle che non sono settate a nero e Set di catchable a false di tutte le caselle (per il popolamento si chiamerà Refill ad inizio game)
        for (Slot[] row : inDashboard) {
            for(Slot slot : row) {
                if (slot.getColor() != Color.BLACK) {
                    slot = new Slot(Color.GREY);
                }
                slot.setCatchable(false);
            }
        }
    }

    //Ripopolamento della Dashboard
    public void refill(Bag bag) {
        //Da definire
    }

    //Controllo della Dashboard per vedere se il Refill è necessario
    public boolean checkRefill() {

        //Se su Dashboard c'è uno slot (effettivo, quindi non settato black) non prendibile allora il Refill non è necessario
        for (Slot[] row : this.inDashboard) {
            for(Slot slot : row) {
                if (slot.getColor() != Color.BLACK && slot.isCatchable() == false) {
                    return false;
                }
            }
        }

        return true;
    }

}
