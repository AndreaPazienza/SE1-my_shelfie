import java.util.Random;

public class Dashboard {

    private Slot[][] inDashboard;
    private static final int side = 9;

    //Costruttore
    public Dashboard (int n) {

        inDashboard = new Slot[side][side];

        boolean[][] notPlayable = new boolean[side][side];

        //Inizializzazione prima e nona riga della maschera
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4) {
                notPlayable[0][i] = true;
                notPlayable[8][8-i] = true;
            }
        }

        //Inizializzazione seconda ottava riga della maschera
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4 && i != 5) {
                notPlayable[1][i] = true;
                notPlayable[7][7-i] = true;
            }
        }

        //Inizializzazione terza e settima riga della maschera
        for (int i  = 0; i < side; i ++) {
            if (i != 2 && i != 3 && i != 4 && i != 5) {
                notPlayable[2][i] = true;
                notPlayable[6][6-i] = true;
            }
        }

        //Inizializzazione quarta e sesta riga della maschera
        notPlayable[3][0] = true;
        notPlayable[5][8] = true;

        //Inizializzazione delle caselle con 4 pallini (se necessario)
        if (n < 4) {
            notPlayable[0][4] = true;
            notPlayable[1][5] = true;
            notPlayable[3][1] = true;
            notPlayable[4][0] = true;
            notPlayable[4][8] = true;
            notPlayable[5][7] = true;
            notPlayable[7][3] = true;
            notPlayable[8][4] = true;
        }

        //Inizializzazione delle caselle con 3 pallini (se necessario)
        if (n < 3) {
            notPlayable[0][3] = true;
            notPlayable[2][2] = true;
            notPlayable[2][6] = true;
            notPlayable[3][7] = true;
            notPlayable[5][0] = true;
            notPlayable[6][2] = true;
            notPlayable[6][6] = true;
            notPlayable[8][5] = true;
        }

        //Set a nero delle caselle corrispondenti a true (non giocabili), set a grigio delle caselle corrispondenti a false (giocabili)
        for (int i = 0; i < side; i ++) {
            for(int j = 0; j < side; j ++) {
                if (notPlayable[i][j] == true) inDashboard[i][j] = new Slot (Color.BLACK);
                    else inDashboard[i][j] = new Slot (Color.GREY);

                inDashboard[i][j].setCatchable(false);
            }
        }
    }

    //Ripopolamento della Dashboard
    public void refill(Bag bag) {

        int selected = 0;

        for (Slot[] row : this.inDashboard) {
            for(Slot slot : row) {
                if (slot.getColor() == Color.GREY) {
                    do {
                        selected = new Random().nextInt(132);

                        slot.setColor(bag.getInBag(selected).getColor());
                        bag.getInBag(selected).setGrey();

                    } while (bag.getInBag(selected).getColor() == Color.GREY);
                }
            }
        }
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
