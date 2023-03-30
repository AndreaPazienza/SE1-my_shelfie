public class CGFiveTilesDiagonal extends CommonGoalAbs{
    @Override
    public void control(PersonalShelf shelf) {
        if(!commonGoalAchived()) {
            Color topLeftBeginDiagonal = shelf.getSingleSlot(0, 0).getColor();
            if (checkIncreasignDiagonal(shelf, topLeftBeginDiagonal, 0, 0)) {
                givePoints(playerPlying);
                return;
            }

            Color secondTopLeftBeginDiagonal = shelf.getSingleSlot(1, 0).getColor();
            if (checkIncreasignDiagonal(shelf, secondTopLeftBeginDiagonal, PersonalShelf.N_ROWS - 1, 0)) {
                givePoints((playerPlying));
                return;}

            Color bottomLeftDiagonal = shelf.getSingleSlot(PersonalShelf.N_ROWS-1, 0).getColor();
            if(checkDecresingDiagonal(shelf, bottomLeftDiagonal, PersonalShelf.N_ROWS-1, 0)){
                givePoints(playerPlying);
                return;
            }
            Color secondoBottomLeftDiagonal = shelf.getSingleSlot(PersonalShelf.N_ROWS-2, 0).getColor();
            if(checkDecresingDiagonal(shelf, secondoBottomLeftDiagonal, PersonalShelf.N_ROWS-2, 0)){
                givePoints(playerPlying);
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
            Color nextDiagonal = shelf.getSingleSlot(row-i, row+1).getColor();
            if (!reference.equals(nextDiagonal)){
                return false;
            }

        }
    return true;
    }
}
