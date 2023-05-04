package MODEL;



//Five columns of increasing or decreasing height.
// Starting from the first column on the left or on the right, each next column must be made of exactly one more tile.
// Tiles can be of any type.
public class CGDecreaseTiles extends CommonGoalAbs {

    public CGDecreaseTiles(int players){
        super(players);
    }

    public void control(Player player) {

        if (!playerAchived[playing]) {
            int i = 1, j = 0;
            Color nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
            ;
            boolean checker = true;
            if (!nextDiagonal.equals(Color.GREY)) {
                do {
                    i++;
                    j++;
                    nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                    if (nextDiagonal.equals(Color.GREY)) {
                        checker = false;
                    }

                } while (checker && i < 5);
            } else checker = false;

            if (!checker) {
                checker = true;
                i = 5;
                j = 0;
                nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                if (!nextDiagonal.equals(Color.GREY)) {
                    do {
                        i--;
                        j++;
                        nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                        if (nextDiagonal.equals(Color.GREY)) {
                            checker = false;
                        }

                    } while (checker && i > 1);
                } else checker = false;
            }

            if (checker) givePoints(player);
        }
    }

}
