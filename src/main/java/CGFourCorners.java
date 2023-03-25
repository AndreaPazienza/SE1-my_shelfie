public class CGFourCorners extends CommonGoalAbs {

    public void control(PersonalShelf shelf) {
        Color bottomLeft = shelf.getSingleSlot(0,0).getColor();
        Color topLeft = shelf.getSingleSlot(0, PersonalShelf.N_ROWS - 1).getColor();
        Color bottomRight = shelf.getSingleSlot(PersonalShelf.N_COLUMN - 1, 0).getColor();
        Color topRight = shelf.getSingleSlot(PersonalShelf.N_COLUMN - 1, PersonalShelf.N_ROWS - 1).getColor();


        if (topLeft.Equals(bottomLeft) && topLeft.Equals(bottomRight) && topLeft.Equals(topRight)) {
            givePoints(playerPlying);
        }

    }
}
