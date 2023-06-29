package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable filling our lines each formed by five tiles of maximum three different colors.
 */
public class CGFourLinesFiveTiles extends CGOnLines {

    /**
     * Constructor for CGFourLinesFiveTiles class.
     *
     * @param players The number of players in the match.
     */
    public CGFourLinesFiveTiles(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param current The player whose shelf has to be checked.
     * @param rows The colors of the row's slots.
     * @param found The number of rows already found that respect the condition.
     * @return True if the row is formed by five slots and there are no more than three colours in these rows and the others already checked, false otherwise.
     */
    @Override
    public boolean controlRows(Player current, Color[] rows, int found) {

        if (found >= 4) {
            givePoints(current);
            return true;
        } else {

            //Marks the colors already found
            boolean[] colorFound = new boolean[6];
            int differentColors = 0, index = 0;
            Color colorChecked;

            for (int i=0; i<rows.length; i++) {
                colorChecked = rows[i];
                if (colorChecked.equals(Color.GREY)) return false;


                index = colorChecked.ordinal();
                //Counting the different colors
                if (!colorFound[index]) {
                    colorFound[index] = true;
                    differentColors++;
                }
                if (differentColors > 3) {
                    return false;
                }

            }
            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.WHITE);
        example.getSingleSlot(0,1).setColor(Color.GREEN);
        example.getSingleSlot(0,2).setColor(Color.YELLOW);
        example.getSingleSlot(0,3).setColor(Color.WHITE);
        example.getSingleSlot(0,4).setColor(Color.GREEN);

        example.getSingleSlot(1,4).setColor(Color.LBLUE);
        example.getSingleSlot(1,3).setColor(Color.GREEN);
        example.getSingleSlot(1,2).setColor(Color.GREEN);
        example.getSingleSlot(1,1).setColor(Color.GREEN);
        example.getSingleSlot(1,0).setColor(Color.LBLUE);

        example.getSingleSlot(5,4).setColor(Color.WHITE);
        example.getSingleSlot(5,3).setColor(Color.WHITE);
        example.getSingleSlot(5,2).setColor(Color.PINK);
        example.getSingleSlot(5,1).setColor(Color.WHITE);
        example.getSingleSlot(5,0).setColor(Color.WHITE);

        example.getSingleSlot(3,4).setColor(Color.WHITE);
        example.getSingleSlot(3,3).setColor(Color.WHITE);
        example.getSingleSlot(3,2).setColor(Color.WHITE);
        example.getSingleSlot(3,1).setColor(Color.WHITE);
        example.getSingleSlot(3,0).setColor(Color.WHITE);

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
        return "Ottieni quattro righe da 5 tessere in cui, in ognuna, si presentano al più 3 colori.";
    }
}
