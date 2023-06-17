package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

import javax.swing.*;

//Eight tiles of the same type. There’s no restriction about the position of these tiles.
public class CGEightTilesSameType extends CommonGoalAbs {
    public CGEightTilesSameType(int players){
        super(players);
        //ImageIcon image = new ImageIcon("src/main/GraphicResources/common goal cards/9.jpg");
        //super.setImage(image);
    }
    public void control(Player player) {

  if(!playerAchived[playing]) {
        int GreenCounter = 0;
        int PinkCounter = 0;
        int BlueCounter = 0;
        int LBCounter = 0;
        int WhiteCounter = 0;
        int YellowCounter = 0;
        final int adder = 1;

        for (int i = 0; i < PersonalShelf.N_ROWS; i++) {
            for (int j = 0; j < PersonalShelf.N_COLUMN; j++) {
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.GREEN)) GreenCounter = GreenCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.PINK)) PinkCounter = PinkCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.BLUE)) BlueCounter = BlueCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.LBLUE)) LBCounter = LBCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.WHITE)) WhiteCounter = WhiteCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.YELLOW)) YellowCounter = YellowCounter + adder;

            }
        }

        if (GreenCounter >= 8 || PinkCounter >= 8 || BlueCounter >= 8 || LBCounter >= 8 || WhiteCounter >= 8 || YellowCounter >= 8) {
            givePoints(player);
       }
    }

    }

    @Override
    public void show() {
        System.out.println("Otto tessere dello stesso tipo. Non ci sono restrizioni sulla posizione di queste tessere.");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,4 ).setColor(Color.LBLUE);
        example.getSingleSlot(3,2).setColor(Color.LBLUE);
        example.getSingleSlot(2,1 ).setColor(Color.LBLUE);
        example.getSingleSlot(3,3 ).setColor(Color.LBLUE);
        example.getSingleSlot(4,2 ).setColor(Color.LBLUE);
        example.getSingleSlot(5,1 ).setColor(Color.LBLUE);
        example.getSingleSlot(1,4 ).setColor(Color.LBLUE);
        example.getSingleSlot(0,3 ).setColor(Color.LBLUE);
        example.getSingleSlot(0,4 ).setColor(Color.LBLUE);

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
