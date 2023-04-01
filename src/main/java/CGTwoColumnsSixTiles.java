public class CGTwoColumnsSixTiles extends CGOnColumn {

    public CGTwoColumnsSixTiles(int players){
        super(players);
    }
    public boolean controlColumn(Color[] column) {

        int i = 1;

        for (Color color : column) {
            for (Color value : column) {
                if (color.equals(value)) {return false;}
            }
        }
        return true;}
}
