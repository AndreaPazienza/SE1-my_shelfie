public class CGFourLinesFiveTiles extends CGOnLines {

    public CGFourLinesFiveTiles(int players){
        super(players);
    }
    public boolean controlRows(Color[] rows) {

        boolean[] colorFound = new boolean[6];
        int differentColors = 0, index = 0;
        Color colorChecked;

        for (Color row : rows) {
            colorChecked = row;
            index = colorChecked.ordinal();

            if (!colorFound[index]) {
                colorFound[index] = true;
                differentColors++;
            }
            if (differentColors > 3) {
                return false;
            }

        }
        return true;
    }
}
