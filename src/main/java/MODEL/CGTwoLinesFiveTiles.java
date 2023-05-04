package MODEL;

//Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.
public class CGTwoLinesFiveTiles extends CGOnLines {
    public CGTwoLinesFiveTiles(int players){
        super(players);
    }

    public boolean controlRows(Player current, Color[] rows, int found) {

        if (found >= 2 ) {
            givePoints(current);
            return true;
        }
        else {
            for(int i=0; i<rows.length; i++){
                for(int j=i+1; j<rows.length; j++ ){
                    if(rows[i].equals(rows[j]) || rows[0].equals(Color.GREY)){
                        return false;
                    }
                }
            }
        }
        return true;}


}
