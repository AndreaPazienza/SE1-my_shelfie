package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;
//Two lines each formed by 5 different types of tiles. One line can show the same or a different combination of the other line.

public class CGTwoLinesFiveTiles extends CGOnLines {
    public CGTwoLinesFiveTiles(int players){

        super(players);
        //ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/6.jpg");
        //super.setImage(image);
    }

    public boolean controlRows(Player current, Color[] rows, int found) {

        if (found > 2) {
            return true;
        }

        if (found == 2 ) {
            givePoints(current);
            return true;
        }
        else {
            for(int i=0; i<rows.length-1; i++){
                for(int j=i+1; j<rows.length; j++ ){
                    if(rows[i].Equals(rows[j]) || rows[0].Equals(Color.GREY)){
                        return false;
                    }
                }
            }
        }
        return true;}

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Due righe formate ciascuna da 5 diversi tipi di tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.GREEN);
        example.getSingleSlot(0,1).setColor(Color.WHITE);
        example.getSingleSlot(0,2).setColor(Color.PINK);
        example.getSingleSlot(0,3).setColor(Color.LBLUE);
        example.getSingleSlot(0,4).setColor(Color.BLUE);

        example.getSingleSlot(5,4).setColor(Color.YELLOW);
        example.getSingleSlot(5,3).setColor(Color.WHITE);
        example.getSingleSlot(5,2).setColor(Color.BLUE);
        example.getSingleSlot(5,1).setColor(Color.LBLUE);
        example.getSingleSlot(5,0).setColor(Color.GREEN);

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
