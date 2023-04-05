package MODEL;

public class CGFourLinesFiveTiles extends CGOnLines {

    public CGFourLinesFiveTiles(int players){
        super(players);
    }
    public boolean controlRows(Player current, Color[] rows, int found) {

        //potrebbe presenatare un bug, siccome devo avere 4 righe con al più tre tipi
        if (found >= 4) {
            givePoints(current);
            return true;
        } else {

            boolean[] colorFound = new boolean[6];
            int differentColors = 0, index = 0;
            Color colorChecked;

            for (int i=0; i<rows.length; i++) {
                colorChecked = rows[i];
                if (colorChecked.equals(Color.GREY)) return false;
                //dovendo essere tutta la riga non serve che

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
}
