public class CGFiveTilesDiagonal extends CommonGoalAbs{
    public CGFiveTilesDiagonal(int players){
        super(players);
    }
    @Override
    public void control(Player player) {

        if(!playerAchived[playing]) {
            Color topLeftBeginDiagonal = player.getShelf().getSingleSlot(0, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), topLeftBeginDiagonal, 0, 0) && !topLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints(player);
                return;
            }

            Color secondTopLeftBeginDiagonal = player.getShelf().getSingleSlot(1, 0).getColor();
            if (checkIncreasignDiagonal(player.getShelf(), secondTopLeftBeginDiagonal, 1, 0) && !topLeftBeginDiagonal.equals(Color.GREY)) {
                givePoints((player));
                return;}

            Color bottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-1, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), bottomLeftDiagonal, PersonalShelf.N_ROWS-1, 0) && !topLeftBeginDiagonal.equals(Color.GREY)){
                givePoints(player);
                return;
            }
            Color secondoBottomLeftDiagonal = player.getShelf().getSingleSlot(PersonalShelf.N_ROWS-2, 0).getColor();
            if(checkDecresingDiagonal(player.getShelf(), secondoBottomLeftDiagonal, PersonalShelf.N_ROWS-2, 0) && !topLeftBeginDiagonal.equals(Color.GREY)){
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
            Color nextDiagonal = shelf.getSingleSlot(row-i, column+1).getColor();
            if (!reference.equals(nextDiagonal)){
                return false;
            }

        }
    return true;
    }
}
