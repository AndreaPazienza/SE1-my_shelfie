public class CGSameTypeSquare extends CommonGoalAbs {

    public CGSameTypeSquare(int players){
        super(players);
    }
    @Override
    public void control(Player player) {
        if (!commonGoalAchived()) {
            boolean valid;
            int squareCounter = 0;
            Color[][] submatrix = new Color[2][2];

            for (int i = 0; i < PersonalShelf.N_ROWS - 1; i++) {
                for (int j = 0; j < PersonalShelf.N_COLUMN - 1; j++) {
                    valid = true;
                    for (int k = 0; k < 2; k++) {
                        for (int w = 0; w < 2; w++) {
                            if (player.getShelf().getSingleSlot(i + k, j + w).getColor().equals(Color.GREY)) {
                                valid = false;
                            } else {
                                submatrix[k][w] = player.getShelf().getSingleSlot(i + k, j + w).getColor();
                            }
                        }
                    }
                    if (valid && submatrix[0][0].equals(submatrix[0][1]) && submatrix[0][0].equals(submatrix[1][0]) && submatrix[0][0].equals(submatrix[1][1]))
                        squareCounter++;
                }
            }
            if (squareCounter >= 2) {
                givePoints(player);
            }
        }
    }
}
