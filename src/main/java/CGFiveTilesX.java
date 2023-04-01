public class CGFiveTilesX extends CommonGoalAbs {

    @Override
        public void control(Player player){

            if(!commonGoalAchived()) {

               boolean valid, done = true;
                int squareCounter = 0;
                Color[][] submatrix = new Color[3][3];

              for (int i=0; i < PersonalShelf.N_ROWS - 3 && done; i++) {
                  for (int j=0; j < PersonalShelf.N_COLUMN - 3 && done; j++) {

                        valid = true;

                    for (int k = 0; k < 3 && valid; k++) {
                        for (int w = 0; w < 3 && valid; w++) {
                            if (player.getShelf().getSingleSlot(i + k, j + w).getColor().equals(Color.GREY)) {
                                valid = false;
                            } else {
                                submatrix[k][w] = player.getShelf().getSingleSlot(i + k, j + w).getColor();
                            }
                        }
                    }
                    //Trova la X
                    Color beginMatrix = submatrix[0][0];
                    Color centralMatrix = submatrix[2][2];
                    Color topRightMatrix = submatrix[0][3];
                    Color bottomLeftMatrix = submatrix[3][0];
                    Color bottomRightMatrix = submatrix[3][3];

                    if (valid && beginMatrix.equals(centralMatrix) && beginMatrix.equals(topRightMatrix) && beginMatrix.equals(bottomRightMatrix) &&
                            beginMatrix.equals(bottomLeftMatrix)) {
                            givePoints(player);
                            done = false;}
                }
            }
        }
    }
}




