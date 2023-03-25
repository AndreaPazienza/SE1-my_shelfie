public class CGFiveTilesDiagonal extends CommonGoalAbs{

    public void control(PersonalShelf shelf) {

        Color topLeftStartDiagonal = shelf.getSingleSlot(0, PersonalShelf.N_ROWS-1).getColor();
        Color bottomRightStartDiagonal = shelf.getSingleSlot(PersonalShelf.N_COLUMN-1, 0).getColor();;
        int sameCount=0, i=1;
        boolean checker = true;

        //Primo controllo sulla diagonale inferiore, devo poer forza aver messo le slot in questa per averli nella successiva.
        do{

           Color nextDiagonal = shelf.getSingleSlot(i, PersonalShelf.N_ROWS-1-i ).getColor();

           if(!bottomRightStartDiagonal.equals(nextDiagonal)){
               checker = false;
               //Avendo al massimo 5 elementi sulla diagonale o la condizione è vera o è falsa.
           }
           else {sameCount++;}

         i++;

        }while(checker && i < 5);
        //Aggiungo la condizione di i < 5 siccome se il checker rimane vero (e ho la corrispondenza devo comunque uscire dal ciclo
        if(sameCount == 5) givePoints(playerPlying);

        else{
            sameCount=0;
            checker=true;
            i=0;
            do{

                Color nextDiagonal = shelf.getSingleSlot(i, PersonalShelf.N_ROWS-1-i ).getColor();
                if(!topLeftStartDiagonal.equals(nextDiagonal)){
                    checker = false;
                 }
                else{ sameCount++;}

                i++;

            }while(checker && i < 5);
            if (sameCount==5) {givePoints(playerPlying);}

        }
    }
}
