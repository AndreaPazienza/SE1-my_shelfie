package MODEL;

public class CGEightTilesSameType extends CommonGoalAbs {
    public CGEightTilesSameType(int players){
        super(players);
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
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.LIGHTBLUE)) LBCounter = LBCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.WHITE)) WhiteCounter = WhiteCounter + adder;
                if (player.getShelf().getSingleSlot(i, j).getColor().equals(Color.YELLOW)) YellowCounter = YellowCounter + adder;

            }
        }

        if (GreenCounter >= 8 || PinkCounter >= 8 || BlueCounter >= 8 || LBCounter >= 8 || WhiteCounter >= 8 || YellowCounter >= 8) {
            givePoints(player);
       }
    }

    }

}
