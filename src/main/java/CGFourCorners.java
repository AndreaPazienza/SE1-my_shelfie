public class CGFourCorners extends CommonGoalAbs {

    public void control(PersonalShelf shelf) {

        Color topLeft = shelf.getSingleSlot(0,0).getColor();


        if(topLeft.equals(Color.GREY)){return;}

        Color bottomLeft = shelf.getSingleSlot(0, PersonalShelf.N_ROWS - 1).getColor();
        Color topRight = shelf.getSingleSlot(PersonalShelf.N_COLUMN - 1, 0).getColor();
        Color bottomRight = shelf.getSingleSlot(PersonalShelf.N_COLUMN - 1, PersonalShelf.N_ROWS - 1).getColor();
        if (topLeft.equals(bottomLeft) && topLeft.equals(bottomRight) && topLeft.equals(topRight)) {
            givePoints(playerPlying);
        }

    }
}
