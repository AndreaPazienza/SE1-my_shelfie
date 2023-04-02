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
}



