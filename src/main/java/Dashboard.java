import java.util.Random;

public class Dashboard {

    private Slot[][] inDashboard;
    private static final int side = 9;

    //Costruttore
    public Dashboard (int numberOfPlayers) {

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
        if (numberOfPlayers < 4) {
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
        if (numberOfPlayers < 3) {
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
                if (notPlayable[i][j]) inDashboard[i][j] = new Slot (Color.BLACK);
                    else inDashboard[i][j] = new Slot (Color.GREY);

                inDashboard[i][j].setCatchable(false);
            }
        }
    }

    //Ripopolamento della Dashboard
    public void refill(Bag bag) {

        int extractedIndex;
        Slot extractedSlot;

        //Per tutti gli slot in Dashboard che hanno colore grigio
        for (Slot[] row : this.inDashboard) {
            for(Slot slot : row) {
                if (slot.getColor().equals(Color.GREY)) {

                    //Randomizzazione dell'indice ed estrazione della prima Slot valida (non grigia) a partire da quell'indice
                    extractedIndex = new Random().nextInt(bag.getInBag().length);
                    extractedSlot = bag.validExtraction(extractedIndex);

                    //Modifica dello slot (di Dashboard) secondo colore e tipo di quello estratto e catchable settato falso (l'aggiornamento a true dove necessario è lasciato a updateTurn)
                    slot.setColor(extractedSlot.getColor());
                    slot.setType(extractedSlot.getType());
                    slot.setCatchable(false);

                    //Set a grigio dello slot estartto da Bag
                    bag.getInBag()[extractedIndex].setGrey();
                }
            }
        }
    }

    //Controllo della Dashboard per vedere se il Refill è necessario
    public boolean checkRefill() {

        boolean refill = true;

        //Se su Dashboard c'è uno slot non singolo (quindi con uno slot adiacente non grigio o nero) allora non è necessario
        for (int i = 0; i < side && refill; i ++) {
            for(int j = 0; j < side && refill; j ++) {
                //Controllo delle adiecenze su tutti gli Slot non grigi o neri (q
                if (!inDashboard[i][j].getColor().equals(Color.GREY) && !inDashboard[i][j].getColor().equals(Color.BLACK) && adjaciencies(i,j) == 0) {
                    refill = false;
                }
            }
        }
        return refill;
    }


    public int adjaciencies(int x, int y) {

        int numberOfAdjacencies = 0;

        //Controllo sullo Slot a sinistra (se esiste)
        if (x != 0 && !inDashboard[x-1][y].getColor().equals(Color.GREY) && !inDashboard[x-1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Controllo sullo Slot a destra (se esiste)
        if (x != side-1 && !inDashboard[x+1][y].getColor().equals(Color.GREY) && !inDashboard[x+1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Controllo sullo Slot superiore (se esiste)
        if (y != 0 && !inDashboard[x][y-1].getColor().equals(Color.GREY) && !inDashboard[x][y-1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Controllo sullo Slot inferiore (se esiste)
        if (y != side-1 && !inDashboard[x][y+1].getColor().equals(Color.GREY) && !inDashboard[x][y+1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        return numberOfAdjacencies;
    }

    public Slot getSingleSlot(int x, int y){
        return inDashboard[x][y];
    }

    public static int getSide() {
        return side;
    }
}
