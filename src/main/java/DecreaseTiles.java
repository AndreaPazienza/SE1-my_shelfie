public class DecreaseTiles extends CommonGoalAbs {


    public void control(PersonalShelf shelf) {
        int i=1, j=0;
        Color firstDiagonal=shelf.getSingleSlot(i, j).getColor();
        Color nextDiagonal;
        boolean checker=true;

        do{
            i++;
            j++;
            nextDiagonal = shelf.getSingleSlot(i, j).getColor();
            if(!firstDiagonal.equals(nextDiagonal) || firstDiagonal.equals(Color.GREY)){
                checker=false;
            }

        }while(checker && i<6);

        if(!checker){
            checker=true;
            i=5;
            j=0;
            Color reverseFirstDiagonal =shelf.getSingleSlot(i, j).getColor();
            do{ i--;
                j++;
                nextDiagonal = shelf.getSingleSlot(i, j).getColor();
                if(!firstDiagonal.equals(nextDiagonal) || firstDiagonal.equals(Color.GREY)){
                    checker=false;}

            }while(checker && i<6);
        }

        if(checker) givePoints(playerPlying);
    }

}
