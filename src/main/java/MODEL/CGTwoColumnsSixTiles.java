package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable filling two columns each formed by 6 different colors of tiles.
 */

public class CGTwoColumnsSixTiles extends CGOnColumn {

    /**
     * Constructor for CGTwoColumnSixTiles class.
     *
     * @param players The number of players in the match.
     */
    public CGTwoColumnsSixTiles(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param current The player whose shelf has to be checked.
     * @param column The colors of the column's slots.
     * @param found The number of column already found that respect the condition.
     * @return True if the column is formed by five slots of different colors or there were already found two of them, false otherwise.
     */
    @Override
    public boolean controlColumn(Player current, Color[] column, int found) {

        if (found >= 2 ) {
            givePoints(current);
            return true;
        }
        else {
            for(int i=0; i<column.length; i++){
                //Checking if the column is composed of six different slots not grey
                for(int j=i+1; j<column.length; j++ ){
                    if(column[i].equals(column[j]) || column[0].equals(Color.GREY)){
                        return false;
                    }
                }
            }
        }
        return true;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Due colonne formate ciascuna da 6 diversi tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.GREEN);
        example.getSingleSlot(1,0).setColor(Color.WHITE);
        example.getSingleSlot(2,0).setColor(Color.PINK);
        example.getSingleSlot(3,0).setColor(Color.LBLUE);
        example.getSingleSlot(4,0).setColor(Color.BLUE);
        example.getSingleSlot(5,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,4).setColor(Color.YELLOW);
        example.getSingleSlot(1,4).setColor(Color.WHITE);
        example.getSingleSlot(2,4).setColor(Color.BLUE);
        example.getSingleSlot(3,4).setColor(Color.LBLUE);
        example.getSingleSlot(4,4).setColor(Color.GREEN);
        example.getSingleSlot(5,4).setColor(Color.PINK);

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
        return "Devi creare due colonne formate tutte da tessere diverse ";
    }
}
