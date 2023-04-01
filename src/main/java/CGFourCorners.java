public class CGFourCorners extends CommonGoalAbs {

    public CGFourCorners(int players){
        super(players);
    }
    public void control(Player player) {

      //if(!commonGoalAchived()) {

          Color topLeft = player.getShelf().getSingleSlot(0, 0).getColor();

          if (topLeft.equals(Color.GREY)) {return;}

          Color bottomLeft = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, 0).getColor();
          Color topRight = player.getShelf().getSingleSlot(0, PersonalShelf.N_COLUMN - 1).getColor();
          Color bottomRight = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS - 1, PersonalShelf.N_COLUMN - 1).getColor();
          if (topLeft.equals(bottomLeft) && topLeft.equals(bottomRight) && topLeft.equals(topRight)) { givePoints(player);}
    //  }
    }
}
