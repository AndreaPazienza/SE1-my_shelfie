package MODEL;

import VIEW.ColorPrint;

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

    @Override
    public void show() {
        System.out.println("Due colonne formate ciascuna da 6 diversi tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.GREEN);
        example.getSingleSlot(1,0).setColor(Color.WHITE);
        example.getSingleSlot(2,0).setColor(Color.PINK);
        example.getSingleSlot(3,0).setColor(Color.LBLUE);
        example.getSingleSlot(4,0).setColor(Color.BLUE);
        example.getSingleSlot(5,0).setColor(Color.YELLOW);
        example.getSingleSlot(0,4).setColor(Color.YELLOW);
        example.getSingleSlot(1,4).setColor(Color.WHITE);
        example.getSingleSlot(2,4).setColor(Color.BLUE);
        example.getSingleSlot(3,4).setColor(Color.LBLUE);
        example.getSingleSlot(4,4).setColor(Color.GREEN);
        example.getSingleSlot(5,4).setColor(Color.PINK);

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
