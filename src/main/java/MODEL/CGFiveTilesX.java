package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;
//Five tiles of the same type forming an X.


public class CGFiveTilesX extends CommonGoalAbs {
    public CGFiveTilesX(int players) {
        super(players);
    }

    @Override
    public void control(Player player) {

        if (!playerAchived[playing]) {

            boolean done = true;
            Color[][] submatrix = new Color[3][3];

            for (int i = 0; i < PersonalShelf.N_ROWS - 2 && done; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN - 2 && done; j++) {
                    for (int k = 0; k < 3; k++) {
                        for (int w = 0; w < 3; w++) {
                            submatrix[k][w] = player.getShelf().getSingleSlot(i + k, j + w).getColor();
                        }
                    }
                    //Trova la X
                    Color beginMatrix = submatrix[0][0];
                    Color centralMatrix = submatrix[1][1];
                    Color topRightMatrix = submatrix[0][2];
                    Color bottomLeftMatrix = submatrix[2][0];
                    Color bottomRightMatrix = submatrix[2][2];

                    if (beginMatrix.equals(centralMatrix) && beginMatrix.equals(topRightMatrix) && beginMatrix.equals(bottomRightMatrix) &&
                            beginMatrix.equals(bottomLeftMatrix) && !beginMatrix.equals(Color.GREY)) {
                        givePoints(player);
                        done = false;
                    }
                }
            }
        }
    }

    @Override
    public void show() {
        System.out.println("Cinque tessere dello stesso tipo che formano una X");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(5,4 ).setColor(Color.YELLOW);
        example.getSingleSlot(3,2).setColor(Color.YELLOW);
        example.getSingleSlot(4,3 ).setColor(Color.YELLOW);
        example.getSingleSlot(3,4 ).setColor(Color.YELLOW);
        example.getSingleSlot(5,2 ).setColor(Color.YELLOW);

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

    @Override
    public String description() {
        return "Ottieni 5 tessere dello stesso tipo in modo che formino una X. ";
    }
}



