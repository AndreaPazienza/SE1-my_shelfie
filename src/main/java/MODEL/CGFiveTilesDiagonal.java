package MODEL;

import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;
//Five tiles of the same type forming a diagonal.


public class CGFiveTilesDiagonal extends CommonGoalAbs{
    public CGFiveTilesDiagonal(int players){
        super(players);
        ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/11.jpg");
        super.setImage(image);
    }

    /**
     * {@inheritDoc}
     *
     * @param player The player whose shelf has to be checked.
     */
    @Override
    public void control(Player player) {

        if(!playerAchived[playing]) {
            Color topLeftBeginDiagonal = player.getShelf().getSingleSlot(0, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), topLeftBeginDiagonal, 0, 0) && !topLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints(player);
                return;
            }

            Color secondTopLeftBeginDiagonal = player.getShelf().getSingleSlot(1, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), secondTopLeftBeginDiagonal, 1, 0) && !secondTopLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints((player));
                return;}

            Color bottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), bottomLeftDiagonal, PersonalShelf.N_ROWS-1, 0) && !bottomLeftDiagonal.equals(Color.GREY)){
                givePoints(player);
                return;
            }
            Color secondoBottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-2, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), secondoBottomLeftDiagonal, PersonalShelf.N_ROWS-2, 0) && !secondoBottomLeftDiagonal.equals(Color.GREY)){
                givePoints(player);
            }
        }

    }

    private boolean checkIncreasignDiagonal(PersonalShelf shelf, Color reference, int rows, int column) {

        for (int i = 1; i < PersonalShelf.N_COLUMN; i++) {
            Color nextDiagonal = shelf.getSingleSlot(rows+i, column+i).getColor();
            if(!reference.equals(nextDiagonal))
            {return false;}
        }

        return true;
    }

    private boolean checkDecresingDiagonal(PersonalShelf shelf, Color reference, int row, int column){

        for(int i=1; i<PersonalShelf.N_COLUMN; i++){
            Color nextDiagonal = shelf.getSingleSlot(row-1, column+1).getColor();
            if (!reference.equals(nextDiagonal)){
                return false;
            }

        }
    return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void show() {
        System.out.println("Cinque tessere dello stesso tipo che formano una diagonale.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,4).setColor(Color.GREEN);
        example.getSingleSlot(1,3).setColor(Color.GREEN);
        example.getSingleSlot(2,2).setColor(Color.GREEN);
        example.getSingleSlot(3,1).setColor(Color.GREEN);
        example.getSingleSlot(4,0).setColor(Color.GREEN);

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
