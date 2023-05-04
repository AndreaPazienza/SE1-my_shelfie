package MODEL;

import VIEW.ColorPrint;

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

    @Override
    public void show() {
        System.out.println("Tre colonne formate ciascuna da 6 tessere di uno, due o tre tipi differenti. Colonne diverse possono avere combinazioni diverse di tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.WHITE);
        example.getSingleSlot(1,0).setColor(Color.GREEN);
        example.getSingleSlot(2,0).setColor(Color.YELLOW);
        example.getSingleSlot(3,0).setColor(Color.WHITE);
        example.getSingleSlot(4,0).setColor(Color.GREEN);
        example.getSingleSlot(5,0).setColor(Color.YELLOW);

        example.getSingleSlot(0,4).setColor(Color.LBLUE);
        example.getSingleSlot(1,4).setColor(Color.GREEN);
        example.getSingleSlot(2,4).setColor(Color.YELLOW);
        example.getSingleSlot(3,4).setColor(Color.GREEN);
        example.getSingleSlot(4,4).setColor(Color.GREEN);
        example.getSingleSlot(5,4).setColor(Color.LBLUE);

        example.getSingleSlot(0,2).setColor(Color.WHITE);
        example.getSingleSlot(1,2).setColor(Color.WHITE);
        example.getSingleSlot(2,2).setColor(Color.WHITE);
        example.getSingleSlot(3,2).setColor(Color.WHITE);
        example.getSingleSlot(4,2).setColor(Color.WHITE);
        example.getSingleSlot(5,2).setColor(Color.WHITE);

        System.out.print("\t");
        for (int k = 0; k < PersonalShelf.N_COLUMN; k++) {
            System.out.print("\t " + k + " \t");
        }
        System.out.print("\n");
        System.out.print("\n");
        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            System.out.print(i + "\t");
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if ((!example.getSingleSlot(i, j).getColor().Equals(Color.BLACK) && !example.getSingleSlot(i, j).getColor().Equals(Color.GREY))) {
                    System.out.print("\t" + ColorPrint.convertColor(example.getSingleSlot(i, j).getColor()) + "[ ]" + ColorPrint.RESET + "\t");
                } else System.out.print("\t" + "   " + "\t");
            }
            System.out.print("\n");
            System.out.print("\n");
        }
        System.out.print("=================================================================================\n");
        System.out.print("\n");
        System.out.print("\n");
    }
}
