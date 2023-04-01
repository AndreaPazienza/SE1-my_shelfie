public class CGDecreaseTiles extends CommonGoalAbs {

    public CGDecreaseTiles(int players){
        super(players);
    }

    public void control(Player player) {
        int i=1, j=0;
        Color firstDiagonal=player.getShelf().getSingleSlot(i, j).getColor();
        Color nextDiagonal;
        boolean checker=true;

        do{
            i++;
            j++;
            nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
            if(!firstDiagonal.equals(nextDiagonal) || firstDiagonal.equals(Color.GREY)){
                checker=false;
            }

        }while(checker && i<6);

        if(!checker){
            checker=true;
            i=5;
            j=0;
            Color reverseFirstDiagonal =player.getShelf().getSingleSlot(i, j).getColor();
            do{ i--;
                j++;
                nextDiagonal = player.getShelf().getSingleSlot(i, j).getColor();
                if(!firstDiagonal.equals(nextDiagonal) || firstDiagonal.equals(Color.GREY)){
                    checker=false;}

            }while(checker && i<6);
        }

        if(checker) givePoints(player);
    }

}
