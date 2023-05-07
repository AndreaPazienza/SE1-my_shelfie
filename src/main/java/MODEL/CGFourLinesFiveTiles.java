package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;
//Four lines each formed by 5 tiles of maximum three different types. One line can show the same or a different combination of another line.

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
    @Override
    public void show() {
        System.out.println("righe formate ciascuna da 5 tessere di uno, due o tre tipi differenti. Righe diverse possono avere combinazioni diverse di tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.WHITE);
        example.getSingleSlot(0,1).setColor(Color.GREEN);
        example.getSingleSlot(0,2).setColor(Color.YELLOW);
        example.getSingleSlot(0,3).setColor(Color.WHITE);
        example.getSingleSlot(0,4).setColor(Color.GREEN);

        example.getSingleSlot(1,4).setColor(Color.LBLUE);
        example.getSingleSlot(1,3).setColor(Color.GREEN);
        example.getSingleSlot(1,2).setColor(Color.GREEN);
        example.getSingleSlot(1,1).setColor(Color.GREEN);
        example.getSingleSlot(1,0).setColor(Color.LBLUE);

        example.getSingleSlot(5,4).setColor(Color.WHITE);
        example.getSingleSlot(5,3).setColor(Color.WHITE);
        example.getSingleSlot(5,2).setColor(Color.PINK);
        example.getSingleSlot(5,1).setColor(Color.WHITE);
        example.getSingleSlot(5,0).setColor(Color.WHITE);

        example.getSingleSlot(3,4).setColor(Color.WHITE);
        example.getSingleSlot(3,3).setColor(Color.WHITE);
        example.getSingleSlot(3,2).setColor(Color.WHITE);
        example.getSingleSlot(3,1).setColor(Color.WHITE);
        example.getSingleSlot(3,0).setColor(Color.WHITE);

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t    " + k + "    \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!example.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !example.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[" + Image.colorToImage(example.getSingleSlot(i, j).getColor()) +"]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "         " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=======================================================================================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }
}
