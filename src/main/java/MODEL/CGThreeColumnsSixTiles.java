package MODEL;

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

            for(int i=0; i < column.length; i++){
                Color colorChecked = column[i];
                if (colorChecked.equals(Color.GREY)) return false;
                index = colorChecked.ordinal();
            if ( !colorFound[index] ) {
                colorFound[index] = true;
                differentColors++;
            }
            if (differentColors > 3) {
                return false;
            }

        }


        return true;}
}
