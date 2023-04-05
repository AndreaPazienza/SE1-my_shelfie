package MODEL;

public class CGTwoColumnsSixTiles extends CGOnColumn {

    public CGTwoColumnsSixTiles(int players){
        super(players);
    }
    public boolean controlColumn(Player current, Color[] column, int found) {

        if (found >= 2 ) {
            givePoints(current);
            return true;
        }
        else {
            for(int i=0; i<column.length; i++){
                for(int j=i+1; j<column.length; j++ ){
                    if(column[i].equals(column[j]) || column[0].equals(Color.GREY)){
                        return false;
                    }
                }
            }
        }
        return true;}
}
