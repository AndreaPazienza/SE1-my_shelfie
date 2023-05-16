package MODEL;

import java.io.Serializable;
import java.util.Observable;

public class Dashboard implements Serializable {

    private Slot[][] inDashboard;
    private static final int side = 9;

    //Constructor
    public Dashboard (int numberOfPlayers) {

        inDashboard = new Slot[side][side];

        boolean[][] notPlayable = new boolean[side][side];

        //Initialization of the first and ninth row of the mask
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4) {
                notPlayable[0][i] = true;
                notPlayable[8][8-i] = true;
            }
        }

        //Initialization of the second and eighth row of the mask
        for (int i  = 0; i < side; i ++) {
            if (i != 3 && i != 4 && i != 5) {
                notPlayable[1][i] = true;
            }
        }
        for (int j = 8; j >= 0; j--) {
            if (j != 3 && j != 4 && j != 5) {
                notPlayable[7][j] = true;
                }
            }

        //Initialization of the third and seventh row of the mask
        for (int i  = 0; i < side; i ++) {
            if (i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
                notPlayable[2][i] = true;
            }
        }
        for(int j = 8; j >= 0; j--){
            if(j != 2 && j != 3 && j != 4 && j != 5 && j != 6){
                    notPlayable[6][j] = true;
            }
        }

        //Initialization of the fourth and sixth row of the mask
        notPlayable[3][0] = true;
        notPlayable[5][8] = true;

        //Initialization of slots with 4 dots (if necessary)
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

        //Initialization of slots with 3 dots (if necessary)
        if (numberOfPlayers < 3) {
            notPlayable[0][3] = true;
            notPlayable[2][2] = true;
            notPlayable[2][6] = true;
            notPlayable[3][8] = true;
            notPlayable[5][0] = true;
            notPlayable[6][2] = true;
            notPlayable[6][6] = true;
            notPlayable[8][5] = true;
        }

        //Set corresponding boxes to black for true (non-playable), set corresponding boxes to gray for false (playable)
        for (int i = 0; i < side; i ++) {
            for(int j = 0; j < side; j ++) {
                if (notPlayable[i][j]) inDashboard[i][j] = new Slot (Color.BLACK);
                    else inDashboard[i][j] = new Slot (Color.GREY);

                inDashboard[i][j].setCatchable(false);
            }
        }
    }

    //Refill of MODEL.Dashboard
    public void refill(Bag bag) {

        //Per tutti gli slot in MODEL.Dashboard che hanno colore grigio
        for (int i=0; i < side; i++) {
            for(int j=0; j < side; j++) {
                Slot cell = inDashboard[i][j];
                if (cell.getColor().equals(Color.GREY)) {
                    cell = bag.getSingleSlot();
                    cell.setCatchable(false);
                    inDashboard[i][j] = cell;
                }
            }
        }
    }


    public void catchAfterRefill(){

        for (int i=0; i<side; i++) {
            for(int j=0; j<side; j++) {
                Slot cell = inDashboard[i][j];

                if (!cell.getColor().equals(Color.GREY) && !cell.getColor().equals(Color.BLACK) && adjaciencies(i,j) < 4 ) {
                    cell.setCatchable(true);
                } else cell.setCatchable(false);
            }
        }
    }


    //Control of MODEL.Dashboard to check if refill is needed
    public boolean checkRefill() {

        boolean refill = true;

        //If there is a non-single slot (therefore with an adjacent non-gray or black slot) on MODEL.Dashboard, then it is not necessary.
        for (int i = 0; i < side && refill; i ++) {
            for(int j = 0; j < side && refill; j ++) {
                //Adjacencies control in all the MODEL.Slot (not grey and black)
                if (!inDashboard[i][j].getColor().equals(Color.GREY) && !inDashboard[i][j].getColor().equals(Color.BLACK) && adjaciencies(i,j) != 0) {
                    refill = false;
                }
            }
        }
        return refill;
    }


    public int adjaciencies(int x, int y) {

        int numberOfAdjacencies = 0;

        //Check on the left MODEL.Slot (if it exists)
        if (x != 0 && !inDashboard[x-1][y].getColor().equals(Color.GREY) && !inDashboard[x-1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the right MODEL.Slot (if it exists)
        if (x != side-1 && !inDashboard[x+1][y].getColor().equals(Color.GREY) && !inDashboard[x+1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the top MODEL.Slot (if it exists)
        if (y != 0 && !inDashboard[x][y-1].getColor().equals(Color.GREY) && !inDashboard[x][y-1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the bottom MODEL.Slot (if it exists)
        if (y != side-1 && !inDashboard[x][y+1].getColor().equals(Color.GREY) && !inDashboard[x][y+1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        return numberOfAdjacencies;
    }

    public Slot[][] getInDashboard() {
        return inDashboard;
    }

    public Slot getSingleSlot(int x, int y){
        return inDashboard[x][y];
    }

    public void setSingleSlot(Slot slot, int x, int y) {
        this.inDashboard[x][y] = slot;
    }

    public static int getSide() {
        return side;
    }
}
