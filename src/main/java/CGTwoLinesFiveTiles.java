public class CGTwoLinesFiveTiles extends CGOnLines {
    public CGTwoLinesFiveTiles(int players){
        super(players);
    }
    public boolean controlRows(Color[] rows) {

        int i = 1;

        for (Color color : rows) {
            for (Color value : rows) {
                if (color.equals(value)) {return false;}
            }
        }
        return true;}

}
