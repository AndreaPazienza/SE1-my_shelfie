package MODEL;
import VIEW.ColorPrint;
import VIEW.Image;

//Four tiles of the same type in the four corners of the bookshelf.


public class CGFourCorners extends CommonGoalAbs {

    public CGFourCorners(int players){
        super(players);
    }
    public void control(Player player) {

        if(!playerAchived[playing]) {

          Color topLeft = player.getShelf().getSingleSlot(0, 0).getColor();

          if (topLeft.equals(Color.GREY)) {return;}

          Color bottomLeft = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, 0).getColor();
          Color topRight = player.getShelf().getSingleSlot(0, PersonalShelf.N_COLUMN - 1).getColor();
          Color bottomRight = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, PersonalShelf.N_COLUMN - 1).getColor();
          if (topLeft.equals(bottomLeft) && topLeft.equals(bottomRight) && topLeft.equals(topRight)) { givePoints(player);}
        }
    }

    @Override
    public void show() {
        System.out.println("Quattro tessere dello stesso tipo ai quattro angoli della shelf");
        System.out.println("Ecco un esempio di shelf che soddisfa l'obiettivo:");
        PersonalShelf example = new PersonalShelf();
        example.getSingleSlot(0,0).setColor(Color.GREEN);
        example.getSingleSlot(PersonalShelf.N_ROWS-1,0).setColor(Color.GREEN);
        example.getSingleSlot(PersonalShelf.N_ROWS-1,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);
        example.getSingleSlot(0,PersonalShelf.N_COLUMN-1).setColor(Color.GREEN);

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
