package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;
//Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.

public class CGSameTypeSquare extends CommonGoalAbs {

    public CGSameTypeSquare(int players) {
        super(players);
        ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/1.jpg");
        super.setImage(image);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {
        if (!commonGoalAchived()) {
            boolean valid;
            boolean[][] visited = new boolean[PersonalShelf.N_ROWS][PersonalShelf.N_COLUMN];
            int squareCounter = 0;
            Color[][] submatrix = new Color[2][2];

            for (int i = 0; i < PersonalShelf.N_ROWS - 1; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN - 1; j++) {
                    valid = true;
                    visited[i][j] = true;
                    for (int k = 0; k < 2; k++) {
                        for (int w = 0; w < 2; w++) {
                            if (player.getShelf().getSingleSlot(i + k, j + w).getColor().equals(Color.GREY) || (visited[i + k][j + w] && (k != 0 || w != 0))) {
                                valid = false;
                            } else {
                                submatrix[k][w] = player.getShelf().getSingleSlot(i + k, j + w).getColor();
                            }
                        }
                    }
                    if (valid && submatrix[0][0].equals(submatrix[0][1]) && submatrix[0][0].equals(submatrix[1][0]) && submatrix[0][0].equals(submatrix[1][1])) {
                        squareCounter++;
                        visited[i + 1][j] = true;
                        visited[i][j + 1] = true;
                        visited[i + 1][j + 1] = true;
                    }
                }
            }
            if (squareCounter >= 2) {
                givePoints(player);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Due gruppi separati di 4 tessere dello stesso tipo che formano un quadrato 2x2. Le tessere dei due gruppi devono essere dello stesso tipo");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0, 0).setColor(Color.YELLOW);
        example.getSingleSlot(0, 1).setColor(Color.YELLOW);
        example.getSingleSlot(1, 0).setColor(Color.YELLOW);
        example.getSingleSlot(1, 1).setColor(Color.YELLOW);
        example.getSingleSlot(4, 0).setColor(Color.YELLOW);
        example.getSingleSlot(5, 0).setColor(Color.YELLOW);
        example.getSingleSlot(4, 1).setColor(Color.YELLOW);
        example.getSingleSlot(5, 1).setColor(Color.YELLOW);

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
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[" + Image.colorToImage(example.getSingleSlot(i, j).getColor()) + "]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }
}
