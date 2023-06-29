package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable inserting eight tiles of the same color in any slot of the shelf.
 */
public class CGEightTilesSameType extends CommonGoalAbs {

    /**
     * Constructor for CGEightTilesSameType class.
     *
     * @param players The number of players in the match.
     */
    public CGEightTilesSameType(int players){
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
            int GreenCounter = 0;
            int PinkCounter = 0;
            int BlueCounter = 0;
            int LBCounter = 0;
            int WhiteCounter = 0;
            int YellowCounter = 0;
            final int adder = 1;
            //Counts all the tiles for each color
            for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.GREEN)) GreenCounter = GreenCounter + adder;
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.PINK)) PinkCounter = PinkCounter + adder;
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.BLUE)) BlueCounter = BlueCounter + adder;
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.LBLUE)) LBCounter = LBCounter + adder;
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.WHITE)) WhiteCounter = WhiteCounter + adder;
                    if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.YELLOW)) YellowCounter = YellowCounter + adder;

                }
            }
            //If there is a counter equals or bigger than 8 the goal is achieved and the player receive the points
            if (GreenCounter >= 8 || PinkCounter >= 8 || BlueCounter >= 8 || LBCounter >= 8 || WhiteCounter >= 8 || YellowCounter >= 8) {
                givePoints(player);
           }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,4 ).setColor(Color.LBLUE);
        example.getSingleSlot(3,2).setColor(Color.LBLUE);
        example.getSingleSlot(2,1 ).setColor(Color.LBLUE);
        example.getSingleSlot(3,3 ).setColor(Color.LBLUE);
        example.getSingleSlot(4,2 ).setColor(Color.LBLUE);
        example.getSingleSlot(5,1 ).setColor(Color.LBLUE);
        example.getSingleSlot(1,4 ).setColor(Color.LBLUE);
        example.getSingleSlot(0,3 ).setColor(Color.LBLUE);
        example.getSingleSlot(0,4 ).setColor(Color.LBLUE);

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
        return "Ottieni 8 tessere dello stesso tipo in tutta la shelf, senza limite di posizioni. ";
    }

}
