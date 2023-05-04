package MODEL;

//Two groups each containing 4 tiles of the same type in a 2x2 square. The tiles of one square can be different from those of the other square.
public class CGSameTypeSquare extends CommonGoalAbs {

    public CGSameTypeSquare(int players){
        super(players);
    }
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
}
