package MODEL;
import java.io.Serializable;

/**
 * Class that represents the game dashboard.
 */
public class Dashboard implements Serializable {

    /**
     * The square matrix of slots that compose the dashboard.
     */
    private final Slot[][] inDashboard;

    /**
     * The side of the matrix that represents the dashboard.
     */
    private static final int side = 9;

    /**
     * Constructor for Dashboard class.
     */
    public Dashboard (int numberOfPlayers) {

        //Creation of the matrix and the boolean mask that marks the matrix's positions that aren't playable
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

        //Set corresponding slots to black for true (non-playable) and to gray for false (playable), set all the slots as not catchable
        for (int i = 0; i < side; i ++) {
            for(int j = 0; j < side; j ++) {
                if (notPlayable[i][j]) {
                    inDashboard[i][j] = new Slot (Color.BLACK);
                } else inDashboard[i][j] = new Slot (Color.GREY);

                inDashboard[i][j].setCatchable(false);
            }
        }
    }

    /**
     * Refill the empty playable slots of the dashboard with tiles randomly extracted from the bag.
     *
     * @param bag The bag to take from the random slots for the refill.
     */
    public void refill(Bag bag) {

        for (int i=0; i < side; i++) {
            for(int j=0; j < side; j++) {
                Slot cell = inDashboard[i][j];
                //Extraction of the casual slot from the bag and positioning on the empty space
                if (cell.getColor().equals(Color.GREY) && !bag.getInBag().isEmpty()) {
                    cell = bag.getSingleSlot();
                    cell.setCatchable(false);
                    inDashboard[i][j] = cell;
                }
            }
        }
    }

    /**
     * Sets the right value of the attribute catchable for all the slots of the dashboard.
     */
    public void catchAfterRefill(){

        for (int i=0; i<side; i++) {
            for(int j=0; j<side; j++) {
                Slot cell = inDashboard[i][j];
                //If a slot has less than 4 (not gray or black) slots adjacent to it then is catchable
                cell.setCatchable (!cell.getColor().equals(Color.GREY) && !cell.getColor().equals(Color.BLACK) && adjacencies(i,j) < 4);
            }
        }
    }

    /**
     * Retrieves if is needed a dashboard's refill or not.
     *
     * @return True if there are only non-single tile, False otherwise.
     */
    public boolean checkRefill() {

        boolean refill = true;

        //If there is a non-single slot (therefore with an adjacent non-gray or black slot) on the dashboard, then it is not necessary.
        for (int i = 0; i < side && refill; i ++) {
            for(int j = 0; j < side && refill; j ++) {
                //Adjacencies control in all the slots (not grey or black)
                if (!inDashboard[i][j].getColor().equals(Color.GREY) && !inDashboard[i][j].getColor().equals(Color.BLACK) && adjacencies(i,j) != 0) {
                    refill = false;
                }
            }
        }
        return refill;
    }

    /**
     * Retrieves the number of the adjacent slots (not grey or black) to the selected one according to its coordinates in the dashboard.
     *
     * @param x The index of the dashboard's slot's row to check.
     * @param y The index of the dashboard's slot's column to check.
     * @return The number of the adjacent slots (not grey or black).
     */
    public int adjacencies(int x, int y) {

        int numberOfAdjacencies = 0;

        //Check on the left slot (if it exists)
        if (x != 0 && !inDashboard[x-1][y].getColor().equals(Color.GREY) && !inDashboard[x-1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the right slot (if it exists)
        if (x != side-1 && !inDashboard[x+1][y].getColor().equals(Color.GREY) && !inDashboard[x+1][y].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the top slot (if it exists)
        if (y != 0 && !inDashboard[x][y-1].getColor().equals(Color.GREY) && !inDashboard[x][y-1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        //Check on the bottom slot (if it exists)
        if (y != side-1 && !inDashboard[x][y+1].getColor().equals(Color.GREY) && !inDashboard[x][y+1].getColor().equals(Color.BLACK))
            numberOfAdjacencies ++;

        return numberOfAdjacencies;
    }

    /*
    public Slot[][] getInDashboard() {
        return inDashboard;
    }
    */

    /**
     * Gets the slot in the selected position of the dashboard according to the coordinates.
     *
     * @param x The index of the dashboard's slot's row to get.
     * @param y The index of the dashboard's slot's column to get.
     * @return The slot of the dashboard to get.
     */
    public Slot getSingleSlot(int x, int y){
        return inDashboard[x][y];
    }

    /**
     * Sets the input slot in the selected position of the dashboard according to the coordinates.
     *
     * @param slot The slot to set.
     * @param x The index of the dashboard's slot's row to set.
     * @param y The index of the dashboard's slot's column to set.
     */
    public void setSingleSlot(Slot slot, int x, int y) {
        this.inDashboard[x][y] = slot;
    }

    /**
     * Retrieves the side of the matrix that represents the dashboard.
     *
     * @return The side of the matrix that represents the dashboard.
     */
    public static int getSide() {
        return side;
    }
}
