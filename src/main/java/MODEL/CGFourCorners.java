package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable inserting four tiles of the same color in the four corners of the bookshelf.
 */
public class CGFourCorners extends CommonGoalAbs {

    /**
     * Constructor for CGFourCorners class.
     *
     * @param players The number of players in the match.
     */
    public CGFourCorners(int players){
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

          Color topLeft = player.getShelf().getSingleSlot(0, 0).getColor();

          if (topLeft.equals(Color.GREY)) {return;}

          Color bottomLeft = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, 0).getColor();
          Color topRight = player.getShelf().getSingleSlot(0, PersonalShelf.N_COLUMN - 1).getColor();
          Color bottomRight = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, PersonalShelf.N_COLUMN - 1).getColor();
          //If the colors of the four corners match the player receives the points
          if (topLeft.equals(bottomLeft) && topLeft.equals(bottomRight) && topLeft.equals(topRight)) { givePoints(player);}
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Quattro tessere dello stesso tipo ai quattro angoli della shelf");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.GREEN);
        example.getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        example.getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        example.getSingleSlot(0,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);

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
        return "Ottieni tessere dello stesso tipo negli angoli della tua Shelf! ";
    }

}
