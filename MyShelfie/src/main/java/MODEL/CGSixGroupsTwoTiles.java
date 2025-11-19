package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

/**
 * Class that represents the common goal card achievable inserting six groups each containing at least two tiles of the same color.
 */
public class CGSixGroupsTwoTiles extends CommonGoalAbs{

    /**
     * Constructor for CGSixGroupsTwoTiles class.
     *
     * @param players The number of players in the match.
     */
    public CGSixGroupsTwoTiles(int players){
        super(players);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {

        boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
        int count=0;


        for(int i=0; i<PersonalShelf.N_ROWS; i++){
            for(int j=0; j<PersonalShelf.N_COLUMN; j++) {
                if (!visited[i][j]) {
                    Color checkingColor = player.getShelf().getSingleSlot(i, j).getColor();
                    if (!checkingColor.Equals(Color.GREY)) {
                        //Checking the upper slot
                        if (i + 1 < PersonalShelf.N_ROWS && player.getShelf().getSingleSlot(i + 1, j).getColor().equals(checkingColor)) {
                            count++;
                            visited[i + 1][j] = true;
                        }
                        //Checking the right slot
                        if (j + 1 < PersonalShelf.N_COLUMN && player.getShelf().getSingleSlot(i, j + 1).getColor().equals(checkingColor)) {
                            count++;
                            visited[i][j + 1] = true;
                        }
                    }
                }
                visited[i][j]=true;
            }
        }
        //If the groups are equal or more than 6 the player receives the points
       if(count >= 6){
           givePoints(player);
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Sei gruppi separati formati ciascuno da due tessere adiacenti dello stesso tipo (non necessariamente come mostrato in figura). Le tessere di un gruppo possono essere diverse da quelle di un altro gruppo.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,1).setColor(Color.YELLOW);
        example.getSingleSlot(1,0).setColor(Color.LBLUE);
        example.getSingleSlot(1,1).setColor(Color.LBLUE);
        example.getSingleSlot(2,0).setColor(Color.PINK);
        example.getSingleSlot(2,1).setColor(Color.PINK);
        example.getSingleSlot(3,0).setColor(Color.GREEN);
        example.getSingleSlot(3,1).setColor(Color.GREEN);
        example.getSingleSlot(4,0).setColor(Color.BLUE);
        example.getSingleSlot(4,1).setColor(Color.BLUE);
        example.getSingleSlot(5,0).setColor(Color.WHITE);
        example.getSingleSlot(5,1).setColor(Color.WHITE);

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
        return "Ottieni sei gruppi separati composti da due tessere adiacenti ";
    }
}
