public class CGThreeColumnsSixTiles extends CGOnColumn {

    public CGThreeColumnsSixTiles(int players){
        super(players);
    }
    public boolean controlColumn(Player player, Color[] column, int found) {


        if (found >= 3) {
            givePoints(player);
            return true;
        }

            boolean[] colorFound = new boolean[6];
            int differentColors=0, index=0;
            Color colorChecked;

        for (Color color : column) {
            colorChecked = color;
            index = colorChecked.ordinal();

            if (!colorFound[index]) {
                colorFound[index] = true;
                differentColors++;
            }
            if (differentColors > 3) {
                return false;
            }

        }


        return true;}
}
