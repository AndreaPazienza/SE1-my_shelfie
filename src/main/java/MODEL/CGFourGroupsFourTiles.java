package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable inserting four groups each containing at least four tiles of the same color not necessarily in the depicted shape.
 */
public class CGFourGroupsFourTiles extends CommonGoalAbs {

    /**
     * Constructor for CGFourGroupsFourTiles class.
     *
     * @param players The number of players in the match.
     */
    public CGFourGroupsFourTiles(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {
        if(!commonGoalAchived()) {
            boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
            int countAdjacent = 0, actualAdjacent = 0;
            //Checking if each slot belongs to a group of same colored slots and counting the groups
            for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                    countAdjacent = checkAdjacentSlot(player.getShelf(), visited, i, j);
                    if (countAdjacent >= 4) {
                        actualAdjacent += countAdjacent / 4;
                    }

                }
            }
            //If the count of the groups is equals or bigger than 4 the player receives the points
            if (actualAdjacent >= 4) {
                givePoints(player);
            }
        }
    }

    /**
     * Retrieves the number of slot, from the slots adjacent, whose color matches with the input slot.
     *
     * @param shelf TThe player's personal shelf to check.
     * @param visited The boolean mask of slots already visited.
     * @param x The row index of the slot to count the adjacency of.
     * @param y The column index of the slot to count the adjacency of.
     * @return The number of slots with the same color of the input slot.
     */
    private int checkAdjacentSlot(PersonalShelf shelf, boolean[][] visited, int x, int y){

        visited[x][y] = true; //Mark on the tile that has been visited
        int count = 1;

        Color color = shelf.getSingleSlot(x,y).getColor(); //Save the color of the tile to check

        //Check on the adjacent tiles: if they have the same color and are not marked, the count is incremented

        //Check on the bottom slot (if it exists)
        if(x > 0 && shelf.getSingleSlot(x-1,y) != null && !shelf.getSingleSlot(x-1, y).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x-1,y).getColor()&&!visited[x-1][y]) { //controllo cella sopra e sotto della shelf
            count += checkAdjacentSlot(shelf, visited, x-1, y);
        }
        //Check on the top slot (if it exists)
        if(x < PersonalShelf.N_ROWS-1 && shelf.getSingleSlot(x+1,y) != null && !shelf.getSingleSlot(x+1, y).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x+1, y).getColor()&&!visited[x+1][y]){
            count += checkAdjacentSlot(shelf, visited, x+1, y);
        }
        //Check on the left slot (if it exists)
        if(y > 0 && shelf.getSingleSlot(x,y-1) != null && !shelf.getSingleSlot(x, y-1).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x, y-1).getColor()&&!visited[x][y-1]){ //controllo cella a sx e a dx della shelf
            count += checkAdjacentSlot(shelf, visited, x, y-1);
        }
        //Check on the right slot (if it exists)
        if(y < PersonalShelf.N_COLUMN-1 && shelf.getSingleSlot(x,y+1) != null && !shelf.getSingleSlot(x, y+1).getColor().Equals(Color.GREY) && color == shelf.getSingleSlot(x, y+1).getColor()&&!visited[x][y+1]){
            count += checkAdjacentSlot(shelf, visited, x, y+1);
        }
        return count;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Quattro gruppi separati formati ciascuno da quattro tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,1).setColor(Color.YELLOW);
        example.getSingleSlot(0,2).setColor(Color.YELLOW);
        example.getSingleSlot(0,3).setColor(Color.YELLOW);
        example.getSingleSlot(5,0).setColor(Color.WHITE);
        example.getSingleSlot(5,1).setColor(Color.WHITE);
        example.getSingleSlot(4,0).setColor(Color.WHITE);
        example.getSingleSlot(4,1).setColor(Color.WHITE);
        example.getSingleSlot(3,2).setColor(Color.GREEN);
        example.getSingleSlot(3,3).setColor(Color.GREEN);
        example.getSingleSlot(3,4).setColor(Color.GREEN);
        example.getSingleSlot(4,2).setColor(Color.GREEN);
        example.getSingleSlot(2,1).setColor(Color.PINK);
        example.getSingleSlot(2,2).setColor(Color.PINK);
        example.getSingleSlot(2,3).setColor(Color.PINK);
        example.getSingleSlot(2,0).setColor(Color.PINK);

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!example.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !example.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[" + Image.colorToImage(example.getSingleSlot(i, j).getColor()) +"]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }

    /**
     * {@inheritDoc}
     *
     * @return The textual description of the common goal.
     */
    @Override
    public String description() {
        return "Ottieni quattro gruppi sepratati composti da 4 tessere dello stesso tipo ";
    }
}
