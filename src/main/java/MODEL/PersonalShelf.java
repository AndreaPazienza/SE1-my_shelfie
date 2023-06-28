package MODEL;

import java.io.Serializable;

/**
 * Class that represents the personal shelf of a player.
 */
public class PersonalShelf implements Serializable {

    /**
     * The matrix of slots that compose the personal shelf.
     */
    private final Slot[][] shelf;

    /**
     * The value that marks if the personal shelf is full.
     */
    private boolean itsFull;
    //private int maxChoices;

    /**
     * The number of rows of a personal shelf.
     */
    public static final int N_ROWS = 6;

    /**
     * The number of column of a personal shelf.
     */
    public static final int N_COLUMN = 5;

    /**
     * Retrieves he number of rows of a personal shelf.
     *
     * @return True if the shelf is true, false otherwise.
     */
    public boolean isItsFull() {
        return itsFull;
    }

    /**
     * Retrieves the points earned by positioning adjacent slots with the same color.
     *
     * @return The number of points earned by the player.
     */
    public int calculatePoints(){
        int tPoints = 0;
        int adiacentSlot = 0;
        boolean[][] visited = new boolean[N_ROWS][N_COLUMN]; //The boolean mask that marks the matrix's positions that are already visited.
        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; j < N_COLUMN; j++){
                if(!this.shelf[i][j].getColor().equals(Color.GREY) && !visited[i][j]){
                    adiacentSlot = checkAdjacentSlot(visited, i, j);
                    if(adiacentSlot >= 3) {
                        int points = calculatePointsForAdiacentSlot(adiacentSlot);
                        tPoints += points;
                    }
                }
            }
        }
        return tPoints;
    }

    /**
     * Retrieves the slot in the selected position of the shelf according to the coordinates.
     *
     * @param x The index of the shelf's slot's row to get.
     * @param y The index of the shelf's slot's column to get.
     * @return The slot of the shelf to get.
     */
    public Slot getSingleSlot(int x, int y){
        return shelf[x][y];
    }

    /**
     * Retrieves the number of the adjacent slots with the same color of the selected one according to its coordinates in the shelf.
     *
     * @param visited The boolean mask that marks the matrix's positions that are already visited.
     * @param x The index of the shelf's slot's row to check.
     * @param y The index of the shelf's slot's column to check.
     * @return The number of the adjacent slots with the same color.
     */
    public int checkAdjacentSlot(boolean[][] visited, int x, int y){
        visited[x][y] = true; //Mark on the tile that has been visited
        int count = 1;
        Color color = this.shelf[x][y].getColor(); //Save the color of the tile to check

        //Check on the adjacent tiles: if they have the same color and are not marked, the count is incremented

        //Check on the bottom slot (if it exists)
        if(x > 0 && this.shelf[x-1][y] != null && color != Color.GREY && color == this.shelf[x-1][y].getColor()&&!visited[x-1][y]) {
            count += checkAdjacentSlot(visited, x-1, y);
        }
        //Check on the top slot (if it exists)
        if(x < N_ROWS-1 && this.shelf[x+1][y] != null && color != Color.GREY && color == this.shelf[x+1][y].getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(visited, x+1, y);
        }
        //Check on the left slot (if it exists)
        if(y > 0 && this.shelf[x][y-1] != null && color != Color.GREY && color == this.shelf[x][y-1].getColor()&&!visited[x][y-1]){
            count += checkAdjacentSlot(visited, x, y-1);
        }
        //Check on the right slot (if it exists)
        if(y < N_COLUMN-1 && this.shelf[x][y+1] != null && color != Color.GREY && color == this.shelf[x][y+1].getColor()&&!visited[x][y+1]){
            count += checkAdjacentSlot(visited, x, y+1);
        }
        return count;
    }

    /**
     * Converts the number of adjacent slots with the same color with the corresponding number of points.
     *
     * @param adiacentSlot The number of adjacent slots.
     * @return The number of points earned according to the input number.
     */
    private int calculatePointsForAdiacentSlot(int adiacentSlot ){
        switch(adiacentSlot){
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 8;
        }
    }

    /**
     * Inserts the slot selected (and ordered) by the player from the dashboard into a column of the personal shelf.
     *
     * @param slots The slots to insert in the shelf.
     * @param column The column to insert into the slots.
     */
    public void insert(Slot[] slots, int column){
        int actualLength = 0;
        for(int i = 0;i < slots.length;i++){
            if(!(slots[i].getColor().Equals(Color.GREY))){
                actualLength++;
            }
        }
        //Finds the first empty slot in the selected column
        int i = N_ROWS-1;
        while (!(shelf[i][column].getColor().Equals(Color.GREY))) {
                i--;}
        //Actual insertion
        for (int j = 0; j < actualLength; j++) {
            this.shelf[i][column].setColor(slots[j].getColor());
            this.shelf[i][column].setType(slots[j].getType());
            i--;
        }
        /*notify*/
        //notifyObservers(this.shelf);
    }

    /**
     * Checks if the last line of the shelf is full.
     *
     * @return True if there are no grey (empty) slots in the upper line of the shelf, False otherwise.
     */
    public boolean checkLastLine(){
        for(int i = 0; i < N_COLUMN; i++){
            if(this.shelf[0][i].getColor().equals(Color.GREY)) {
                return false;
            }
        }
       return true;
    }

    /**
     * Constructor for PersonalShelf class.
     */
    public PersonalShelf(){
        this.shelf = new Slot[N_ROWS][N_COLUMN];
        for(int i = 0; i < N_ROWS; i++){
            for(int j = 0; j < N_COLUMN; j++){
             this.shelf[i][j]  = new Slot(Color.GREY);
            }
        }
        this.itsFull = false;
    }

    /*
    public void setMaxChoices(){
        int max = 0;
        for(int i = 0; i < N_COLUMN; i++) {
            int count = 0;
            boolean exit = false;

            for(int j = 0;j < N_ROWS && exit==false; j++){
                if (this.shelf[j][i].getColor().equals(Color.GREY)){
                    count++;
                }
                else{
                    exit = true;
                }
            }
            if (count>maxChoices){
                this.maxChoices=count;
            }
        }
    }
    */

    //Constructor that permit us to go faster to the endgame (only used to test)
   public PersonalShelf(int i){
        this.shelf = new Slot[N_ROWS][N_COLUMN];
        for(int k = 0; k < N_ROWS; k++){
            for(int j = 0; j < N_COLUMN; j++){
                this.shelf[k][j]  = new Slot(Color.GREEN);
            }
        }
        this.shelf[0][0].setColor(Color.GREY);
        this.shelf[1][0].setColor(Color.GREY);
        this.shelf[2][0].setColor(Color.GREY);
        this.itsFull = false;
    }

   /*
    public int getMaxChoices(){
        return this.maxChoices;
    }
    */
}
