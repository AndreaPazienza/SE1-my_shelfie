package MODEL;

import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable inserting five tiles of the same type forming a diagonal line.
 */
public class CGFiveTilesDiagonal extends CommonGoalAbs{

    /**
     * Constructor for CGFiveTilesDiagonal class.
     *
     * @param players The number of players in the match.
     */
    public CGFiveTilesDiagonal(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {

        if(!playerAchived[playing]) {
            //Checking the upper main diagonal
            Color topLeftBeginDiagonal = player.getShelf().getSingleSlot(0, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), topLeftBeginDiagonal, 0, 0) && !topLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints(player);
                return;
            }
            //Checking the lower main diagonal
            Color secondTopLeftBeginDiagonal = player.getShelf().getSingleSlot(1, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), secondTopLeftBeginDiagonal, 1, 0) && !secondTopLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints((player));
                return;}
            //Checking the lower secondary diagonal
            Color bottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), bottomLeftDiagonal, PersonalShelf.N_ROWS-1, 0) && !bottomLeftDiagonal.equals(Color.GREY)){
                givePoints(player);
                return;
            }
            //Checking the upper secondary diagonal
            Color secondoBottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-2, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), secondoBottomLeftDiagonal, PersonalShelf.N_ROWS-2, 0) && !secondoBottomLeftDiagonal.equals(Color.GREY)){
                givePoints(player);
            }
        }

    }

    /**
     * Checks if the colors of the increasing diagonal's slot that starts from the input slot match each other.
     *
     * @param shelf The player's personal shelf to check.
     * @param reference The color to match.
     * @param rows The row index of the input slot of the shelf.
     * @param column The column index of the input slot of the shelf.
     * @return True if the color of each slot matches, false otherwise.
     */
    private boolean checkIncreasignDiagonal(PersonalShelf shelf, Color reference, int rows, int column) {

        for (int i = 1; i < PersonalShelf.N_COLUMN; i++) {
            Color nextDiagonal = shelf.getSingleSlot(rows+i, column+i).getColor();
            if(!reference.equals(nextDiagonal)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the colors of the decreasing diagonal's slots that starts from the input slot match each other.
     *
     * @param shelf The player's personal shelf to check.
     * @param reference The color to match.
     * @param row The row index of the input slot of the shelf.
     * @param column The column index of the input slot of the shelf.
     * @return True if the color of each slot matches, false otherwise.
     */
    private boolean checkDecresingDiagonal(PersonalShelf shelf, Color reference, int row, int column){

        for(int i=1; i<PersonalShelf.N_COLUMN; i++){
            Color nextDiagonal = shelf.getSingleSlot(row-1, column+1).getColor();
            if (!reference.equals(nextDiagonal)){
                return false;
            }

        }
    return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Cinque tessere dello stesso tipo che formano una diagonale.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,4).setColor(Color.GREEN);
        example.getSingleSlot(1,3).setColor(Color.GREEN);
        example.getSingleSlot(2,2).setColor(Color.GREEN);
        example.getSingleSlot(3,1).setColor(Color.GREEN);
        example.getSingleSlot(4,0).setColor(Color.GREEN);

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
        return "Ottieni tessere dello stesso tipo che formano una diagonale. ";
    }
}
