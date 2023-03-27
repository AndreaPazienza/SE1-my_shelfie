public class CGTwoLinesFiveTiles extends CGOnLines {
    public boolean controlRows(Color[] rows) {

        int i = 1;

        for (Color color : rows) {
            for (Color value : rows) {
                if (color.equals(value)) {return false;}
            }
        }
        return true;}

}
